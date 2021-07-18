package devanmejia.prodctshopimages.service.storage;

import devanmejia.prodctshopimages.models.StorageFile;
import devanmejia.prodctshopimages.repository.StorageFileRepository;
import devanmejia.prodctshopimages.service.google_drive.GoogleDriveStorage;
import devanmejia.prodctshopimages.transfer.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Autowired
    private GoogleDriveStorage googleDriveStorage;
    @Autowired
    private StorageFileRepository storageFileRepository;

    @Override
    public String downloadFileByName(String name) {
        Optional<StorageFile> storageFileOptional = storageFileRepository.getStorageFilesByName(name);
        if (storageFileOptional.isPresent()){
            return googleDriveStorage.downloadFile(storageFileOptional.get().getId());
        }
        else {
            throw new IllegalArgumentException(String.format("Can not find file with name %s.", name));
        }
    }

    @Override
    public void deleteFileByName(String name) {
        Optional<StorageFile> storageFileOptional = storageFileRepository.deleteStorageFileByName(name);
        if (storageFileOptional.isPresent()){
            StorageFile storageFile = storageFileOptional.get();
            try{
                googleDriveStorage.deleteFile(storageFile.getId());
            }catch (IOException e){
                storageFileRepository.save(storageFile);
                throw new IllegalArgumentException("Can not delete file. " + e.getMessage());
            }
        }
    }

    @Override
    public StorageFile uploadFile(FileDTO fileDTO) {
        Optional<StorageFile> storageFileOptional = storageFileRepository.deleteStorageFileByName(fileDTO.getName());
        if (storageFileOptional.isPresent()){
            StorageFile storageFile = storageFileOptional.get();
            try{
                googleDriveStorage.deleteFile(storageFile.getId());
            } catch (IOException e){
                storageFileRepository.save(storageFile);
                throw new IllegalArgumentException("Can not delete file. " + e.getMessage());
            }
        }
        String id = googleDriveStorage.uploadFile(fileDTO.getContent());
        return storageFileRepository.save(new StorageFile(id, fileDTO.getName()));
    }

}

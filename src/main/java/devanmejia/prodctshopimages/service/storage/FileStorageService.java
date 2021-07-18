package devanmejia.prodctshopimages.service.storage;

import devanmejia.prodctshopimages.models.StorageFile;
import devanmejia.prodctshopimages.transfer.FileDTO;
import org.springframework.stereotype.Service;

@Service
public interface FileStorageService {
    String downloadFileByName(String name);
    void deleteFileByName(String name);
    StorageFile uploadFile(FileDTO fileDTO);

}

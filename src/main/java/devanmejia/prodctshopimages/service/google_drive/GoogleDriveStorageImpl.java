package devanmejia.prodctshopimages.service.google_drive;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import devanmejia.prodctshopimages.service.file_system.BufferedStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class GoogleDriveStorageImpl implements GoogleDriveStorage {
    @Autowired
    private Drive googleDriveService;
    @Autowired
    private BufferedStorage bufferedStorage;

    @Override
    public String uploadFile(byte[] bytes) {
        try{
            String path = String.format("%s.png", UUID.randomUUID().toString());
            File file = new File();
            file.setName(path);

            java.io.File bufferedFile = bufferedStorage.createBufferedFile(bytes);
            FileContent content = new FileContent("image/png", bufferedFile);

            File uploadedFile = googleDriveService.files().create(file, content).setFields("id").execute();
            bufferedStorage.deleteBufferedFile(bufferedFile);
            return uploadedFile.getId();
        } catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String downloadFile(String id) {
        try{
            return googleDriveService.files().get(id)
                    .setFields("id, name, thumbnailLink").execute().getThumbnailLink();
        } catch (IOException e){
            throw new IllegalArgumentException("Can not download file. " + e.getMessage());
        }

    }

    @Override
    public void deleteFile(String id) throws IOException{
        googleDriveService.files().delete(id).execute();
    }

}

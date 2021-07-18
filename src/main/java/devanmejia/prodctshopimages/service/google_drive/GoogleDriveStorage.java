package devanmejia.prodctshopimages.service.google_drive;

import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public interface GoogleDriveStorage {
    String uploadFile(byte[] bytes);
    String downloadFile(String id);
    void deleteFile(String id) throws IOException;
}

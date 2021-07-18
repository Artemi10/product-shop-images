package devanmejia.prodctshopimages.service.file_system;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public interface BufferedStorage {
    File createBufferedFile(byte[] content) throws IOException;
    void deleteBufferedFile(File file) throws IOException;
}

package devanmejia.prodctshopimages.service.file_system;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class BufferedFileSystemStorage implements BufferedStorage {

    @Override
    public File createBufferedFile(byte[] content) throws IOException {
        String path = String.format("%s.png", UUID.randomUUID().toString());
        System.err.println(path);
        Path filePath = Paths.get("/buffer/" + path);
        System.err.println(filePath.toFile());
        try (BufferedOutputStream fileWriter = new BufferedOutputStream(new FileOutputStream(filePath.toFile()));
             BufferedInputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(content))) {
            IOUtils.copy(inputStream, fileWriter);
        }
        return filePath.toFile();
    }

    @Override
    public void deleteBufferedFile(File file) throws IOException {
        Files.delete(file.toPath());
    }
}

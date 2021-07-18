package devanmejia.prodctshopimages.config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.GeneralSecurityException;


@Component
public class GoogleDriveConfig {
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    @Bean
    public Drive getDriveService() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Credential credentials =  getCredentials(HTTP_TRANSPORT);
        return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credentials).build();
    }

    private Credential getCredentials(NetHttpTransport httpTransport) throws IOException {
        InputStream jsonFileStream = this.getClass().getClassLoader()
                .getResourceAsStream("static/credentials/devanmejia-shop.json");
        GoogleCredential readJsonFile = GoogleCredential.fromStream(jsonFileStream, httpTransport, JSON_FACTORY).createScoped(DriveScopes.all());
        return new GoogleCredential.Builder().setTransport(readJsonFile.getTransport())
                .setJsonFactory(readJsonFile.getJsonFactory())
                .setServiceAccountId(readJsonFile.getServiceAccountId())
                .setServiceAccountScopes(readJsonFile.getServiceAccountScopes())
                .setServiceAccountPrivateKey(readJsonFile.getServiceAccountPrivateKey()).build();
    }

}

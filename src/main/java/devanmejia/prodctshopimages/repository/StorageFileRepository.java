package devanmejia.prodctshopimages.repository;

import devanmejia.prodctshopimages.models.StorageFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageFileRepository extends MongoRepository<StorageFile, String> {
    Optional<StorageFile> getStorageFilesByName(String name);
    Optional<StorageFile> deleteStorageFileByName(String name);
}

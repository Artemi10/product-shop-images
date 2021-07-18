package devanmejia.prodctshopimages.controller;

import devanmejia.prodctshopimages.service.storage.FileStorageService;
import devanmejia.prodctshopimages.transfer.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/images/storage")
public class FileStorageController {
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/file")
    public void uploadFile(@RequestBody FileDTO fileDTO){
        fileStorageService.uploadFile(fileDTO);
    }

    @GetMapping("/file/{name}")
    public String getFileLink(@PathVariable String name){
        return fileStorageService.downloadFileByName(name);
    }

    @DeleteMapping("/file/{name}")
    public void deleteFile(@PathVariable String name){
        fileStorageService.deleteFileByName(name);
    }
}

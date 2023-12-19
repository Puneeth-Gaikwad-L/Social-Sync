package com.example.socialsync.AWS.Controller;

import com.example.socialsync.AWS.Service.FileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileStorageController {

    @Autowired
    FileStoreService fileStoreService;
    @PostMapping("/s3/upload")
    public ResponseEntity uploadFilesToS3(@RequestPart("file")MultipartFile data, @RequestParam("U")String userName){
        String response = fileStoreService.uploadFileToS3(data, userName);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/s3/{filename}")
    public ResponseEntity downloadFileFromS3(@PathVariable String fileName){
        byte[] file = fileStoreService.downloadFIleFromS3(fileName);
        return new ResponseEntity<>(file, HttpStatus.ACCEPTED);
    }
}

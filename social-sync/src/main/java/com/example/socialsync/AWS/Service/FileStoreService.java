package com.example.socialsync.AWS.Service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStoreService {

    public String uploadFileToS3(MultipartFile data);

    public byte[] downloadFIleFromS3(String fileName);
}

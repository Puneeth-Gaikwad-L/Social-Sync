package com.example.socialsync.AWS.Service.impl;

import com.example.socialsync.AWS.AWSCloudUtil;
import com.example.socialsync.AWS.Service.FileStoreService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileStoreServiceImpl implements FileStoreService {
    @Value("${aws.access.key}")
    private String AWS_ACCESS_KEY;
    @Value("${aws.secret.key}")
    private String AWS_SECRET_KEY;
    @Value("${aws.s3.bucket}")
    private String AWS_BUCKET;

    @Override
    public String uploadFileToS3(MultipartFile data){
        try {
            AWSCloudUtil util = new AWSCloudUtil();
            util.uploadFileToS3(data.getOriginalFilename(), data.getBytes(), AWS_ACCESS_KEY, AWS_SECRET_KEY, AWS_BUCKET);
            return String.format("File %s uploaded successfully,", data.getOriginalFilename());
        }catch (IOException e){
            e.printStackTrace();
            return String.format("File %s upload failed,", data.getOriginalFilename());
        }
    }

    @Override
    public byte[] downloadFIleFromS3(String fileName){
        try {
            AWSCloudUtil util = new AWSCloudUtil();
            return util.downloadFileFromS3(fileName,AWS_ACCESS_KEY, AWS_SECRET_KEY, AWS_BUCKET).readAllBytes();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}

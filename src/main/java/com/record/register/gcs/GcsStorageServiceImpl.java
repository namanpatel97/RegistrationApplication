package com.record.register.gcs;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class GcsStorageServiceImpl implements GcsStorageService{

    private static final Logger log = LoggerFactory.getLogger(GcsStorageServiceImpl.class);
    private final Storage storage;

    @Value("${gcs.bucket.name}")
    private String bucketName;

    public GcsStorageServiceImpl(Storage storage){
        this.storage = storage;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            BlobId blobId = BlobId.of(bucketName, "profile/" + fileName);

            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(file.getContentType())
                    .build();

            storage.create(blobInfo, file.getBytes());

            return "https://storage.googleapis.com/" +
                    bucketName + "/profile/" + fileName;

        } catch (Exception ex) {
            throw new RuntimeException("Failed to upload file");
        }

    }

    @Override
    public void deleteFile(String filePath) {

        if(filePath == null || filePath.isEmpty()){
            log.info("No file path available");
            return;
        }

        try {
            String objectName = extractObjectName(filePath);

            boolean deleted = storage.delete(bucketName, objectName);

            if (!deleted) {
                throw new RuntimeException("File not found in GCS: " + objectName);
            }

        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete file from GCS", ex);
        }

    }

    private String extractObjectName(String filePath) {

        // Case 1: already object key
        if (!filePath.startsWith("http")) {
            return filePath;
        }

        // Case 2: full URL → extract after bucket name
        String baseUrl = "https://storage.googleapis.com/" + bucketName + "/";

        return filePath.replace(baseUrl, "");
    }


}

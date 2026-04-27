package com.record.register.gcs;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class GcsStorageServiceImpl implements GcsStorageService{

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
}

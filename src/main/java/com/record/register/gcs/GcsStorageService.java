package com.record.register.gcs;

import org.springframework.web.multipart.MultipartFile;

public interface GcsStorageService {

    String uploadFile(MultipartFile file);

    void deleteFile(String filePath);
}

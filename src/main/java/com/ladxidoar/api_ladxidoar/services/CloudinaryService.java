package com.ladxidoar.api_ladxidoar.services;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    String uploadFile(MultipartFile file);

    void deleteFile(String imageUrl);

}

package com.ladxidoar.api_ladxidoar.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class CloudinaryServiceImpl implements CloudinaryService{

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadFile(MultipartFile file){
        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) uploadResult.get("url");
            // Convertir a HTTPS antes de devolver
            return url.replace("http://", "https://");
        } catch (IOException e) {
            throw new RuntimeException("Error al subir el archivo a Cloudinary", e);
        }
    }

    @Override
    public void deleteFile(String imageUrl) {
        try {
            // Extraer el public_id de la URL
            String publicId = extractPublicIdFromUrl(imageUrl);
            if (publicId != null) {
                cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al eliminar el archivo de Cloudinary", e);
        }
    }

    private String extractPublicIdFromUrl(String imageUrl) {
        // Implementa lógica para extraer el public_id de la URL
        // Ejemplo: si la URL es "http://res.cloudinary.com/demo/image/upload/v12345/sample.jpg"
        // El public_id sería "sample"
        return imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.lastIndexOf("."));
    }

}

package com.example.demo.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.WrongFomatException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryServiceImpl {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(
            Cloudinary cloudinary
    ) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file, String folder) {
        try {
            if (Objects.requireNonNull(file.getContentType()).contains("image")) {
                Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                        "folder", folder
                ));
                return this.getUrlImage(uploadResult.get("public_id").toString());
            }else{
                throw new WrongFomatException("file is not an image");
            }
        }catch (IOException e){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "File not found");
        }
    }

    public List<String> uploadImages(List<MultipartFile> files, String folder) {
        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                if (Objects.requireNonNull(file.getContentType()).contains("image")) {
                    Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                            "folder", folder
                    ));
                    urls.add(this.getUrlImage(uploadResult.get("public_id").toString()));
                } else {
                    throw new WrongFomatException("File is not an image");
                }
            } catch (IOException e) {
                throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "File not found");
            }
        }

        return urls;
    }

    public String getUrlImage(String publicId) {
        System.out.println(cloudinary.url().generate(publicId) + "get url image");
        return cloudinary.url().generate(publicId);
    }


}

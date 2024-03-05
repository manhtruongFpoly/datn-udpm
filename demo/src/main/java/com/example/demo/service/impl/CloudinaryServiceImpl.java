package com.example.demo.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.WrongFomatException;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.entity.ProductColor;
import com.example.demo.model.entity.ProductEntity;
import com.example.demo.model.entity.ProductSizeEntity;
import com.example.demo.repository.ProductRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Service
public class CloudinaryServiceImpl {

    private final Cloudinary cloudinary;

    private final ModelMapper modelMapper;

    private final ProductRepository productRepository;

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

    public List<String> uploadImages(ProductDto productDto, String folder) {
        List<String> urls = new ArrayList<>();

        for (MultipartFile file : productDto.getFiles()) {
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

        ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
        productEntity.setImgList(String.join(",", urls));
        productRepository.save(productEntity);

        List<ProductColor> productColors = Stream.of(productDto.getListColors().split(",")).map(str -> new ProductColor(null, Long.valueOf(str), productEntity.getId())).collect(Collectors.toList());

        List<ProductSizeEntity> productSizeEntities = Stream.of(productDto.getListSizes().split(",")).map(str -> new ProductSizeEntity(null, Long.valueOf(str), productEntity.getId())).collect(Collectors.toList());

        return urls;
    }

    public String getUrlImage(String publicId) {
        System.out.println(cloudinary.url().generate(publicId) + "get url image");
        return cloudinary.url().generate(publicId);
    }


}

package com.example.demo.dto;

import com.example.demo.entity.Image;
import lombok.Data;

@Data
public class ImageDto {
    private String imageBytes;

    public static ImageDto fromImage(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setImageBytes(new String(image.getImageBytes()));
        return imageDto;
    }
}

package com.cos.photogramstart.web.dto.image;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class ImageUploadDto {

    @NotBlank
    private MultipartFile file;

    @NotBlank
    private String caption;

    public ImageUploadDto(MultipartFile file, String caption) {
        this.file = file;
        this.caption = caption;
    }

    public Image toEntity(String postImageUrl, User user) {
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }

}

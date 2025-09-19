package com.project.handly.DTOs.Platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.handly.Entities.ChannelPlatforms;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Setter
@Getter
public class PlatformDTO {

    @NotNull(message = "the arabic name can't be null")
    @Size(min=3 ,max=15 ,message = "The arabic name should be bigger than 3 letters and less than 15 letters")
    private String nameAr;


    @NotNull(message = "the english name can't be null")
    @Size(min=3 ,max=15 ,message = "The arabic name should be bigger than 3 letters and less than 15 letters")
    private String nameEr;

    @NotBlank(message = "the logo url can't be blank")
    @URL(message = "the logo url must be a valid URL")
    private String logoUrl;

    @JsonProperty("channel_platforms")
    private List<ChannelPlatforms> channelPlatforms;
}

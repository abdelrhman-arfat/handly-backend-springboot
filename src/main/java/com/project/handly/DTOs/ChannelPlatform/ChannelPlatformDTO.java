package com.project.handly.DTOs.ChannelPlatform;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChannelPlatformDTO {

    @NotBlank(message = "channel id mustn't has wight spaces")
    private Long channelId;

    @NotBlank(message = "platform id mustn't has wight spaces")
    private Long platformId;


    @NotBlank(message = "api key mustn't has spaces")
    @Size(min = 10 , message = "api key must be bigger than 10 letters")
    private String apiKey ;

    @Size(min = 5 , message = "api secret must be bigger than 5 letters")
    private String apiSecret;

}

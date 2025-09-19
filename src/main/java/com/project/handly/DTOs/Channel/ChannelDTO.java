package com.project.handly.DTOs.Channel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelDTO {

    @NotNull(message = "the channel name can't be null")
    @Size(min = 2,max = 30)
    private String name;




}

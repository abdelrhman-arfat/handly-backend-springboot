package com.project.handly.Controllers;

import com.project.handly.Repositories.ChannelPlatformRepo;
import com.project.handly.Services.ChannelPlatformService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/channel/platforms")
public class ChannelPlatformController {
    private final ChannelPlatformService channelPlatformService;
    public ChannelPlatformController(ChannelPlatformService channelPlatformService) {
        this.channelPlatformService = channelPlatformService;
    }
}

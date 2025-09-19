package com.project.handly.Controllers;

import com.project.handly.Services.ChannelPlatformService;
import com.project.handly.Services.ChannelsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

    private final ChannelsService channelsService;
    public ChannelController(ChannelsService channelsService) {
        this.channelsService = channelsService;
    }
}

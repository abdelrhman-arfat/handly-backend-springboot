package com.project.handly.Services;

import com.project.handly.Repositories.ChannelPlatformRepo;
import org.springframework.stereotype.Service;

@Service
public class ChannelPlatformService {

    private final ChannelPlatformRepo channelPlatformRepo;
    public ChannelPlatformService(ChannelPlatformRepo channelPlatformRepo) {
        this.channelPlatformRepo = channelPlatformRepo;
    }

}

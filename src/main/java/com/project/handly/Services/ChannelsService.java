package com.project.handly.Services;

import com.project.handly.Repositories.ChannelRepo;
import org.springframework.stereotype.Service;

@Service
public class ChannelsService {
    private final ChannelRepo channelRepo;
    public ChannelsService(ChannelRepo channelRepo) {
        this.channelRepo = channelRepo;
    }
}

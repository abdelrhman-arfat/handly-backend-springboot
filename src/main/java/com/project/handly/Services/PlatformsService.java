package com.project.handly.Services;

import com.project.handly.Entities.Platforms;
import com.project.handly.Repositories.PlatformsRepo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformsService {

    private final PlatformsRepo platformsRepo;

    public PlatformsService(PlatformsRepo platformsRepo) {
        this.platformsRepo = platformsRepo;
    }

    @Cacheable(value = "platforms")
    public List<Platforms> all()
    {
        return platformsRepo.findAll();
    }

}

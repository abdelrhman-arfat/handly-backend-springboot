package com.project.handly.Controllers;

import com.project.handly.Entities.Platforms;
import com.project.handly.Services.PlatformsService;
import com.project.handly.Utils.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/platforms")
public class PlatformController {
    private final PlatformsService platformsService;
    public PlatformController(PlatformsService platformsService) {
        this.platformsService = platformsService;
    }

    @GetMapping
    public ResponseEntity<Object> allPlatforms()
    {
        try{
            List<Platforms> platformsList = platformsService.all();
            return ResponseHandler.success(
                    "fetch all platform successfully",
                    platformsList,
                    HttpStatus.OK
            );
        }catch (Exception ex){
            return ResponseHandler.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

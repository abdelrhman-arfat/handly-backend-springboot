package com.project.handly.Repositories;

import com.project.handly.Entities.ChannelPlatforms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelPlatformRepo  extends JpaRepository<ChannelPlatforms , Long> { }

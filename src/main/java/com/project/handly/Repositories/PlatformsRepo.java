package com.project.handly.Repositories;

import com.project.handly.Entities.Platforms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformsRepo  extends JpaRepository<Platforms, String> { }

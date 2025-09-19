package com.project.handly.Repositories;

import com.project.handly.Entities.Channels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepo extends JpaRepository<Channels, Long> {}

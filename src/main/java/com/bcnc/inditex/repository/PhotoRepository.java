package com.bcnc.inditex.repository;

import com.bcnc.inditex.model.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}

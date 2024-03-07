package com.bcnc.inditex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bcnc.inditex.model.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}

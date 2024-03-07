package com.bcnc.inditex.service;

import com.bcnc.inditex.model.AlbumDTO;
import com.bcnc.inditex.model.PhotoDTO;
import com.bcnc.inditex.model.entity.Album;

import java.util.List;

public interface AlbumService {

    List<Album> getAll();

    List<Album> convertDtoToEntity(List<AlbumDTO> albumDTOList, List<PhotoDTO> photoDTOList);

    void saveAll(List<Album> albumList);

    Album save(Album album);

    List<AlbumDTO> getAlbumExternal();

}

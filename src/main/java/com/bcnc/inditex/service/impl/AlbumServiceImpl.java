package com.bcnc.inditex.service.impl;


import com.bcnc.inditex.model.AlbumDTO;
import com.bcnc.inditex.model.PhotoDTO;
import com.bcnc.inditex.model.entity.Album;
import com.bcnc.inditex.model.entity.Photo;
import com.bcnc.inditex.repository.AlbumRepository;
import com.bcnc.inditex.service.AlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private static final String URL_API_REST_ALBUM = "https://jsonplaceholder.typicode.com/albums";
    private static final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private AlbumRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Album> getAll() {
        log.info(">>>Recuperando todos los Album.");
        List<Album> albumList = repository.findAll();
        log.info("<<<Numero de Album recuperados de BBDD("+albumList.size()+")");
        return albumList;
    }

    @Override
    @Transactional
    public List<Album> convertDtoToEntity(List<AlbumDTO> albumDTOList, List<PhotoDTO> photoDTOList) {

        log.info(">>>Convertir DTO to Entity.");

        HashMap<Long, List<PhotoDTO>> mapPhotosDTOByAlbum;
        //Agrupar las fotos por Album
        if (photoDTOList == null || photoDTOList.isEmpty()) {
            mapPhotosDTOByAlbum = new HashMap<>();
        }else{
            mapPhotosDTOByAlbum = (HashMap<Long, List<PhotoDTO>>) photoDTOList.stream().collect(Collectors.groupingBy(PhotoDTO::getAlbumId));
        }

        //Asociar a cada album a la lista de fotos
        List<Album> albumList = new ArrayList<>();
        if (albumDTOList != null) {
            albumDTOList.forEach(albumDTO ->
            {
                Album album = new Album(albumDTO.getId(), albumDTO.getUserId(), albumDTO.getTitle());

                //Convertir objeto PhotoDTO en Photo
                if (!mapPhotosDTOByAlbum.isEmpty()) {
                    List<PhotoDTO> photoDTOByAlbumList = mapPhotosDTOByAlbum.get(albumDTO.getId());
                    List<Photo> photoList = new ArrayList<>();
                    if (photoDTOByAlbumList != null) {
                        photoDTOByAlbumList.forEach(photoDTO -> {
                            Photo photo = new Photo(photoDTO.getId(), photoDTO.getTitle(), photoDTO.getUrl(), photoDTO.getThumbnailUrl(), album);
                            photoList.add(photo);
                        });
                    }
                    //Añadir las fotos al Album
                    album.setPhotoList(photoList);
                }
                albumList.add(album);
            });
        }
        log.info("<<< Fin convertir DTO to Entity.");
        return albumList;

    }

    @Override
    @Transactional
    public void saveAll(List<Album> albumList) {
        log.info(">>>Guardando todos los Album.");
        albumList.forEach(this::save);
        log.info(">>>Fin guardando todos los Album.");
    }

    @Override
    @Transactional
    public Album save(Album album) {
        return repository.save(album);
    }

    @Override
    public List<AlbumDTO> getAlbumExternal() {
        log.info(">>>Llamada API Externa Album.");
        ResponseEntity<List<AlbumDTO>> responseEntityAlbum =
                restTemplate.exchange(
                        URL_API_REST_ALBUM,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<AlbumDTO>>() {}
                );
        List<AlbumDTO> albumDTOList= responseEntityAlbum.getBody();
        log.info("<<<Albums recuperados en API Externa=" + albumDTOList.size());

        return albumDTOList;

    }

}

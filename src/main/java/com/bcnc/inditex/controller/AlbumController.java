package com.bcnc.inditex.controller;

import com.bcnc.inditex.error.ResourceNotFountException;
import com.bcnc.inditex.model.AlbumDTO;
import com.bcnc.inditex.model.PhotoDTO;
import com.bcnc.inditex.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bcnc.inditex.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bcnc.inditex.model.entity.Album;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/album")
public class AlbumController {
    private static final Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private PhotoService photoService;

    @GetMapping(path = "/getAPIExternalAndSaveBBDD")
    public ResponseEntity<List<Album>> getAPIExternalAndSaveBBDD(){
        log.info("--->Inicio llamada API getAPIExternalAndSaveBBDD().");
        //Obtener albums externos
        List<AlbumDTO> albumDTOList = albumService.getAlbumExternal();
        if (albumDTOList.isEmpty()){
            throw new ResourceNotFountException("No album external found");
        }

        //Obtener fotos externas
        List<PhotoDTO> photoDTOList = photoService.getPhotoExternal();

        //Convertir objeto DTO en Entity y asociar a cada albun la lista de fotos asociadas
        List<Album> albumList = albumService.convertDtoToEntity(albumDTOList, photoDTOList);

        //Guarda en BBDD Album y fotos
        albumService.saveAll(albumList);
        log.info("<---Fin llamada API getAPIExternalAndSaveBBDD().");

        return ResponseEntity.ok(albumList);
    }

    @GetMapping(path = "/getAPIExternal")
    public ResponseEntity<List<Album>> getAPIExternal(){
        log.info("--->Inicio llamada API getAPIExternal().");
        //Obtener albums externos
        List<AlbumDTO> albumDTOList = albumService.getAlbumExternal();
        if (albumDTOList.isEmpty()){
            throw new ResourceNotFountException("No album external found");
        }

        //Obtener fotos externas
        List<PhotoDTO> photoDTOList = photoService.getPhotoExternal();

        //Convertir objeto DTO en Entity y asociar a cada albun la lista de fotos asociadas
        List<Album> albumList = albumService.convertDtoToEntity(albumDTOList, photoDTOList);
        log.info("<---Fin llamada API getAPIExternal().");

        return ResponseEntity.ok(albumList);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Album>> getAll(){
        log.info("--->Inicio llamada API getAll().");
        //Obtener albums externos
        List<Album> albumList = albumService.getAll();

        if (albumList.isEmpty()){
            throw new ResourceNotFountException("No album in data base found");
        }
        log.info("<---Fin llamada API getAll().");

        return ResponseEntity.ok(albumList);
    }

}

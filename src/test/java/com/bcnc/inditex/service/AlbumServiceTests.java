package com.bcnc.inditex.service;

import com.bcnc.inditex.model.AlbumDTO;
import com.bcnc.inditex.model.PhotoDTO;
import com.bcnc.inditex.model.entity.Album;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AlbumServiceTests {
    @Autowired
    AlbumService albumService;

    @Test
    @Order(1)
    public void getNoAlbumInBBDDTest(){

        List<Album> albumList = albumService.getAll();
        assertEquals(albumList.size(),0);
    }

    @Test
    @Order(2)
    public void getAlbumExternalTest(){
        List<AlbumDTO> albumDTOList = albumService.getAlbumExternal();
        assertFalse(albumDTOList.isEmpty());
    }

    @Test
    @Order(3)
    public void convertAlbumDtoToEntityWithPhotoListEmptyTest(){
        List<AlbumDTO> albumDTOList = albumService.getAlbumExternal();
        List<PhotoDTO> photoDTOList = new ArrayList<>();
        List<Album> albumList = albumService.convertDtoToEntity(albumDTOList, photoDTOList);

        assertEquals(albumDTOList.size(), albumList.size());

    }

    @Test
    @Order(4)
    public void convertAlbumDtoToEntityWithPhotoListEmptyAndAlbumListEmptyTest(){
        List<AlbumDTO> albumDTOList = new ArrayList<>();
        List<PhotoDTO> photoDTOList = new ArrayList<>();
        List<Album> albumList = albumService.convertDtoToEntity(albumDTOList, photoDTOList);

        assertEquals(albumDTOList.size(), albumList.size());

    }

    @Test
    @Order(5)
    public void convertAlbumDtoToEntityWithPhotoListNullAndAlbumListNullTest(){
        List<AlbumDTO> albumDTOList = null;
        List<PhotoDTO> photoDTOList = null;
        List<Album> albumList = albumService.convertDtoToEntity(albumDTOList, photoDTOList);

        assertTrue(albumList.isEmpty());

    }

    @Test
    @Order(6)
    public void saveAllAlbumWithPhotoListNullTest(){
        List<AlbumDTO> albumDTOList = albumService.getAlbumExternal();
        List<Album> albumList = albumService.convertDtoToEntity(albumDTOList, null);
        albumService.saveAll(albumList);
        List<Album> albumBBDDList = albumService.getAll();

        assertEquals(albumList.size(),albumBBDDList.size());

    }
}

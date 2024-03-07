package com.bcnc.inditex.service;

import com.bcnc.inditex.model.AlbumDTO;
import com.bcnc.inditex.model.PhotoDTO;

import com.bcnc.inditex.model.entity.Album;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PhotoServiceTests {
    @Autowired
    PhotoService photoService;

    @Autowired
    AlbumService albumService;

    @Test
    public void getPhotoExternalTest(){
        List<PhotoDTO> photoDTOList = photoService.getPhotoExternal();
        assertFalse(photoDTOList.isEmpty());
    }

    @Test
    public void saveAllAlbumWithPhotoListTest(){
        List<AlbumDTO> albumDTOList = albumService.getAlbumExternal();
        List<PhotoDTO> photoDTOList = photoService.getPhotoExternal();
        List<Album> albumList = albumService.convertDtoToEntity(albumDTOList, photoDTOList);
        albumService.saveAll(albumList);
        List<Album> albumBBDDList = albumService.getAll();

        assertEquals(albumList.size(),albumBBDDList.size());

    }

}

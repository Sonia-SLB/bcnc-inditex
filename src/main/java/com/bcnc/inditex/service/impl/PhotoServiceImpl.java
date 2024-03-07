package com.bcnc.inditex.service.impl;

import com.bcnc.inditex.model.PhotoDTO;
import com.bcnc.inditex.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    private static final String URL_API_REST_PHOTOS = "https://jsonplaceholder.typicode.com/photos";
    private static final Logger log = LoggerFactory.getLogger(PhotoServiceImpl.class);
    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<PhotoDTO> getPhotoExternal() {
        log.info(">>>Llamada API Externa Photos.");
        ResponseEntity<List<PhotoDTO>> responseEntityPhoto =
                restTemplate.exchange(
                        URL_API_REST_PHOTOS,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<PhotoDTO>>() {}
                );
        List<PhotoDTO> photoDTOList = responseEntityPhoto.getBody();
        log.info("<<<Fotos recuperadas en API Externa=" + photoDTOList.size());
        return photoDTOList;
    }

}

package com.bcnc.inditex.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AlbumControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void getNotFoundAllAlbumTest() throws Exception {
        this.mvc.perform(get("/album/getAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(2)
    public void getAlbumNoBBDDTest() throws Exception {
        this.mvc.perform(get("/album/getAPIExternal")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void getAlbumInBBDDTest() throws Exception {
        this.mvc.perform(get("/album/getAPIExternalAndSaveBBDD")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void getAllAlbumTest() throws Exception {

        this.mvc.perform(get("/album/getAPIExternalAndSaveBBDD")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        this.mvc.perform(get("/album/getAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @Order(5)
    public void getErrorServerControllerTest() throws Exception {
        this.mvc.perform(get("/album/getAllError")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }
}

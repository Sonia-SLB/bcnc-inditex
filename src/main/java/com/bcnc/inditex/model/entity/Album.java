package com.bcnc.inditex.model.entity;

import com.bcnc.inditex.configuration.JacocoAnnotationGenerated;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="ALBUM", uniqueConstraints = {@UniqueConstraint(columnNames="title")})
@JacocoAnnotationGenerated
public class Album {

    @Id
    @Column(name="ID")
    private Long id;

    @Column(name="USER_ID", nullable = false)
    private Long userId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "album", cascade = CascadeType.ALL)
    private List<Photo> photoList;

    public Album() {
    }

    public Album(Long id, Long userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }
}

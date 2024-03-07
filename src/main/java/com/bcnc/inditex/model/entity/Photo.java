package com.bcnc.inditex.model.entity;

import com.bcnc.inditex.configuration.JacocoAnnotationGenerated;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name="PHOTO", uniqueConstraints = {@UniqueConstraint(columnNames="title")})
@JacocoAnnotationGenerated
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "album_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Album album;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "URL", nullable = false)
    private String url;

    @Column(name = "THUMBNAIURL", nullable = false, updatable = false)
    private String thumbnailUrl;

    public Photo() {
    }

    public Photo(Long id, String title, String url, String thumbnailUrl, Album album){
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.album = album;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}

package com.victorbicego.restapi.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Song {

    @Id
    @GeneratedValue
    private Long songId;

    @NotNull
    private String songName;

    @Min(value = 0)
    private float songDuration;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    private Artist songArtist;

    public Song() {
    }

    public Song(String songName, float songDuration, Artist songArtist) {
        this.songName = songName;
        this.songDuration = songDuration;
        this.songArtist = songArtist;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public float getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(float songDuration) {
        this.songDuration = songDuration;
    }

    public Artist getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(Artist songArtist) {
        this.songArtist = songArtist;
    }
}

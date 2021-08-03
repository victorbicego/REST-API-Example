package com.victorbicego.restapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Artist {

    @Id
    @GeneratedValue
    private Long artistId;

    @NotNull
    private String artistName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Song> artistSongs;

    public Artist() {
    }

    public Artist(String artistName, List<Song> artistSongs) {
        this.artistName = artistName;
        this.artistSongs = artistSongs;
    }

    public Artist(String artistName) {
        this.artistName = artistName;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public List<Song> getArtistSongs() {
        return artistSongs;
    }

    public void setArtistSongs(List<Song> artistSongs) {
        this.artistSongs = artistSongs;
    }
}

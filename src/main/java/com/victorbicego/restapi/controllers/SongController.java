package com.victorbicego.restapi.controllers;

import com.victorbicego.restapi.models.Song;
import com.victorbicego.restapi.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @PostMapping("/newsong")
    public ResponseEntity<?> saveSong(@RequestBody @Valid Song songBody) {
        return new ResponseEntity<>(songRepository.save(songBody), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllSongs() {
        return new ResponseEntity<>(songRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findSongByIdInQuery(@RequestParam Long id) {
        Optional<Song> song = songRepository.findById(id);
        if (song.isPresent()) {
            return ResponseEntity.ok(song.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSongByIdInPath(@PathVariable Long id) {
        Optional<Song> song = songRepository.findById(id);
        if (song.isPresent()) {
            songRepository.delete(song.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSong(@RequestParam Long id, @RequestBody @Valid Song songBody) {
        Optional<Song> song = songRepository.findById(id);
        if (song.isPresent()) {
            Song updateSong = song.get();
            updateSong.setSongName(songBody.getSongName());
            updateSong.setSongDuration(songBody.getSongDuration());
            updateSong.setSongArtist(songBody.getSongArtist());
            return new ResponseEntity<>(songRepository.save(updateSong), HttpStatus.OK);
        }
        return new ResponseEntity<>("Song not found", HttpStatus.NOT_FOUND);
    }
}

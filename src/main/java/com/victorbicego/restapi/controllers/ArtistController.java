package com.victorbicego.restapi.controllers;

import com.victorbicego.restapi.models.Artist;
import com.victorbicego.restapi.models.Song;
import com.victorbicego.restapi.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @PostMapping("/newartist")
    public ResponseEntity<?> saveArtist(@RequestBody @Valid Artist artistBody) {
        return new ResponseEntity<>(artistRepository.save(artistBody), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllArtists() {
        return new ResponseEntity<>(artistRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findArtistByIdInQuery(@RequestParam Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            return ResponseEntity.ok(artist.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArtistByIdInPath(@PathVariable Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            artistRepository.delete(artist.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateArtist(@RequestParam Long id, @RequestBody @Valid Artist artistBody) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            Artist updateArtist = artist.get();
            updateArtist.setArtistName(artistBody.getArtistName());
            updateArtist.setArtistSongs(artistBody.getArtistSongs());
            return new ResponseEntity<>(artistRepository.save(updateArtist), HttpStatus.OK);
        }
        return new ResponseEntity<>("Artist not found", HttpStatus.NOT_FOUND);
    }
}

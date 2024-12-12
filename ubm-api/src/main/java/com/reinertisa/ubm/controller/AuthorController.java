package com.reinertisa.ubm.controller;


import com.reinertisa.ubm.model.AuthorDto;
import com.reinertisa.ubm.model.AuthorRequest;
import com.reinertisa.ubm.service.AuthorServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/authors")
@CrossOrigin(origins = "*")
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {

        List<AuthorDto> authors = authorService.getAllAuthors();
        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorService.createAuthor(authorRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}

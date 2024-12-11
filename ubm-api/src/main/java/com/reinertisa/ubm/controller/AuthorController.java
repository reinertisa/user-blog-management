package com.reinertisa.ubm.controller;


import com.reinertisa.ubm.model.Author;
import com.reinertisa.ubm.service.AuthorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {

        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(author));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}

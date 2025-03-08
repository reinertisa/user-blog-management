package com.reinertisa.ubm.resource;


import com.reinertisa.ubm.domain.Response;
import com.reinertisa.ubm.dto.Author;
import com.reinertisa.ubm.dto.AuthorNameOptions;
import com.reinertisa.ubm.dtorequest.AuthorRequest;
import com.reinertisa.ubm.service.AuthorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.reinertisa.ubm.utils.RequestUtils.getResponse;

@RestController
@RequestMapping("/api/v1/authors")
@CrossOrigin(origins = "*")
public class AuthorResource {

    private final AuthorService authorService;

    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public ResponseEntity<Response> getAllAuthors(HttpServletRequest request) {
        try {
            List<Author> authors = authorService.getAllAuthors();
            Map<String, List<Author>> data = new HashMap<>();
            data.put("authors", authors);
            return ResponseEntity
                    .ok()
                    .body(getResponse(request, data, "All author list", HttpStatus.OK));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @GetMapping("/options")
    public ResponseEntity<Response> getAllAuthorNames(HttpServletRequest request) {
        try {
            List<AuthorNameOptions> options = authorService.getAllAuthorNames();
            Map<String, List<AuthorNameOptions>> data = new HashMap<>();
            data.put("authorNameOptions", options);
            return ResponseEntity.ok().body(getResponse(request, data, "Author name options", HttpStatus.OK));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @PostMapping("")
    public ResponseEntity<Response> createAuthor(@RequestBody @Valid AuthorRequest authorRequest, HttpServletRequest request) {
        try {
            Author author = authorService.createAuthor(authorRequest);
            Map<String, Author> data = new HashMap<>();
            data.put("author", author);
            return ResponseEntity
                    .created(getUri())
                    .body(getResponse(request, data, "Author created.", HttpStatus.CREATED));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    private URI getUri() {
        return URI.create("");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteAuthor(@PathVariable("id") Long id, HttpServletRequest request) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().body(getResponse(request, Collections.emptyMap(),"Author deleted.", HttpStatus.NO_CONTENT));
    }
}

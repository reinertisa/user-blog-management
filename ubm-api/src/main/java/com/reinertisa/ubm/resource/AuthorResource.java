package com.reinertisa.ubm.resource;


import com.reinertisa.ubm.domain.Response;
import com.reinertisa.ubm.dtorequest.dto.AuthorDto;
import com.reinertisa.ubm.dtorequest.AuthorNameOptions;
import com.reinertisa.ubm.dtorequest.request.AuthorRequest;
import com.reinertisa.ubm.service.AuthorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.reinertisa.ubm.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;

@RestController
@RequestMapping("/api/v1/authors")
@CrossOrigin(origins = "*")
public class AuthorResource {

    private final AuthorService authorService;

    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authorService.getAllAuthors());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @GetMapping("/options")
    public ResponseEntity<List<AuthorNameOptions>> getAllAuthorNames() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authorService.getAllAuthorNames());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @PostMapping("")
    public ResponseEntity<Response> createAuthor(@RequestBody @Valid AuthorRequest authorRequest, HttpServletRequest request) {
        try {
            AuthorDto authorDto = authorService.createAuthor(authorRequest);
            Map<String, AuthorDto> data = new HashMap<>();
            data.put("author", authorDto);
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
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}

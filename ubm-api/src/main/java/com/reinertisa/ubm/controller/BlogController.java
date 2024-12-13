package com.reinertisa.ubm.controller;


import com.reinertisa.ubm.exception.ResourceNotFoundException;
import com.reinertisa.ubm.model.BlogDto;
import com.reinertisa.ubm.model.BlogRequest;
import com.reinertisa.ubm.service.BlogServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/blogs")
@CrossOrigin(origins = "*")
public class BlogController {

    private final BlogServiceImpl blogService;

    @GetMapping
    public ResponseEntity<List<BlogDto>> getAllBlogs() {
        List<BlogDto> blogs = blogService.getAllBlogs();
        return ResponseEntity.status(HttpStatus.OK).body(blogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogById(id));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        } catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@RequestBody BlogRequest blogRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(blogService.createBlog(blogRequest));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}


package com.reinertisa.ubm.service;

import com.reinertisa.ubm.exception.ResourceNotFoundException;
import com.reinertisa.ubm.model.BlogDto;
import com.reinertisa.ubm.model.BlogRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface BlogService {

    List<BlogDto> getAllBlogs();

    BlogDto getBlogById(Long id) throws ResourceNotFoundException;

    BlogDto createBlog(@Valid BlogRequest blogRequest) throws ResourceNotFoundException;

    void deleteBlog(Long id);
}

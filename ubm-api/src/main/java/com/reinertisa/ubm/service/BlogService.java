package com.reinertisa.ubm.service;

import com.reinertisa.ubm.dto.Blog;
import com.reinertisa.ubm.exception.ResourceNotFoundException;
import com.reinertisa.ubm.dtorequest.BlogRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlogs();

    Blog getBlogById(Long id) throws ResourceNotFoundException;

    Blog createBlog(@Valid BlogRequest blogRequest) throws ResourceNotFoundException;

    void deleteBlog(Long id);
}

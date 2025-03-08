package com.reinertisa.ubm.service.impl;

import com.reinertisa.ubm.dto.Blog;
import com.reinertisa.ubm.dtorequest.BlogRequest;
import com.reinertisa.ubm.exception.ResourceNotFoundException;
import com.reinertisa.ubm.mapper.BlogMapper;
import com.reinertisa.ubm.entity.*;
import com.reinertisa.ubm.repository.AuthorRepository;
import com.reinertisa.ubm.repository.BlogRepository;
import com.reinertisa.ubm.service.BlogService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final AuthorRepository authorRepository;
    private final BlogMapper blogMapper;

    public BlogServiceImpl(BlogRepository blogRepository, AuthorRepository authorRepository, BlogMapper blogMapper) {
        this.blogRepository = blogRepository;
        this.authorRepository = authorRepository;
        this.blogMapper = blogMapper;
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogMapper.toDtoListFromEntityList(blogRepository.findAll());
    }

    @Override
    public Blog getBlogById(Long id) throws ResourceNotFoundException{
        Objects.requireNonNull(id, "Id should not be null");
        BlogEntity blogEntity = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BlogEntity not found for this ID: " + id));

        return blogMapper.toDtoFromEntity(blogEntity);
    }

    @Override @Transactional
    public Blog createBlog(@Valid BlogRequest blogRequest) throws ResourceNotFoundException {

        AuthorEntity authorEntity = authorRepository.findById(blogRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("AuthorEntity not found by email"));

        BlogEntity blogEntity = blogMapper.toEntityFromRequest(blogRequest);
        authorEntity.addBlog(blogEntity);
        blogEntity.setAuthor(authorEntity);
        blogRepository.save(blogEntity);
        return blogMapper.toDtoFromEntity(blogEntity);
    }

    @Override @Transactional
    public void deleteBlog(Long id) {
        blogRepository.findById(id)
                .ifPresent(blogRepository::delete);
    }
}

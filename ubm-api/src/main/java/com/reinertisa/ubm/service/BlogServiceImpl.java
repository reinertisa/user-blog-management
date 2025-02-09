package com.reinertisa.ubm.service;

import com.reinertisa.ubm.exception.ResourceNotFoundException;
import com.reinertisa.ubm.model.*;
import com.reinertisa.ubm.repository.AuthorRepository;
import com.reinertisa.ubm.repository.BlogRepository;
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
    public List<BlogDto> getAllBlogs() {
        return blogMapper.toDtoListFromEntityList(blogRepository.findAll());
    }

    @Override
    public BlogDto getBlogById(Long id) throws ResourceNotFoundException{
        Objects.requireNonNull(id, "Id should not be null");
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found for this ID: " + id));

        return blogMapper.toDtoFromEntity(blog);
    }

    @Override @Transactional
    public BlogDto createBlog(@Valid BlogRequest blogRequest) throws ResourceNotFoundException {

        Author author = authorRepository.findById(blogRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found by email"));

        Blog blog = blogMapper.toEntityFromRequest(blogRequest);
        author.addBlog(blog);
        blog.setAuthor(author);
        blogRepository.save(blog);
        return blogMapper.toDtoFromEntity(blog);
    }

    @Override @Transactional
    public void deleteBlog(Long id) {
        blogRepository.findById(id)
                .ifPresent(blogRepository::delete);
    }
}

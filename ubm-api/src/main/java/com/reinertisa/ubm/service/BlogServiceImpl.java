package com.reinertisa.ubm.service;

import com.reinertisa.ubm.exception.ResourceNotFoundException;
import com.reinertisa.ubm.model.Author;
import com.reinertisa.ubm.model.Blog;
import com.reinertisa.ubm.model.BlogDto;
import com.reinertisa.ubm.model.BlogRequest;
import com.reinertisa.ubm.repository.AuthorRepository;
import com.reinertisa.ubm.repository.BlogRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Objects;

@Data
@RequiredArgsConstructor
@Service
@CrossOrigin(origins = "*")
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<BlogDto> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream().map(blog -> new BlogDto(
                        blog.getId(),
                        blog.getTitle(),
                        blog.getContent(),
                        blog.getAuthor().getFirstName(),
                        blog.getAuthor().getEmail()
                ))
                .toList();
    }

    @Override
    public BlogDto getBlogById(Long id) throws ResourceNotFoundException{
        Objects.requireNonNull(id, "Id should not be null");
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found for this ID: " + id));

        return new BlogDto(
                blog.getId(),
                blog.getTitle(),
                blog.getContent(),
                blog.getAuthor().getFirstName(),
                blog.getAuthor().getEmail()
        );

    }

    @Override @Transactional
    public BlogDto createBlog(@Valid BlogRequest blogRequest) throws ResourceNotFoundException {

        Author author = authorRepository.findAuthorByEmail(blogRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found by email"));
        Blog blog = new Blog();
        blog.setTitle(blogRequest.getTitle());
        blog.setContent(blogRequest.getContent());
        blog.setAuthor(author);
        blogRepository.save(blog);

        return new BlogDto(
                blog.getId(),
                blog.getTitle(),
                blog.getContent(),
                blog.getAuthor().getFirstName(),
                blog.getAuthor().getEmail()
        );
    }

    @Override @Transactional
    public void deleteBlog(Long id) {
        blogRepository.findById(id)
                .ifPresent(blogRepository::delete);
    }
}

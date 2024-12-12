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

import java.util.List;

@Data
@RequiredArgsConstructor
@Service
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
        blogRepository.findById(id).ifPresent(blogRepository::delete);
    }
}

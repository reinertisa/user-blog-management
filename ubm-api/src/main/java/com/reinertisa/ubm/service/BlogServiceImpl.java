package com.reinertisa.ubm.service;

import com.reinertisa.ubm.model.Blog;
import com.reinertisa.ubm.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override @Transactional
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override @Transactional
    public void deleteBlog(Long id) {
        blogRepository.findById(id).ifPresent(blogRepository::delete);
    }
}

package com.reinertisa.ubm.service;

import com.reinertisa.ubm.model.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlogs();

    Blog createBlog(Blog blog);

    void deleteBlog(Long id);
}

package com.reinertisa.ubm.dtorequest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class BlogRequest {

    private Long blogId;

    @NotNull(message = "This field is required.")
    private long authorId;

    @NotBlank(message = "This field is required.")
    private String title;

    @NotBlank(message = "This field is required.")
    private String content;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

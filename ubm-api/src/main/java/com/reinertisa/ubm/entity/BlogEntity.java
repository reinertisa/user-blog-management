package com.reinertisa.ubm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name = "blogs")
public class BlogEntity extends Auditable implements Comparable<BlogEntity> {

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @JsonBackReference
    @ManyToOne(targetEntity = AuthorEntity.class,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(
            name = "author_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "Blogs_FK1")
    )
    private AuthorEntity author;


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

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity authorEntity) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BlogEntity{" +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                '}';
    }

    @Override
    public int compareTo(BlogEntity o) {
        return this.getTitle().compareTo(o.getTitle());
    }
}
package com.reinertisa.ubm.model;

import lombok.*;

@Data
@AllArgsConstructor
public class BlogDto {

    private Long id;
    private String title;
    private String content;
    private String authorName;
    private String authorEmail;
}

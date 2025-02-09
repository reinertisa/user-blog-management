package com.reinertisa.ubm.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public interface BlogMapper {

    @Mapping(source = "id", target = "blogId")
    @Mapping(source = "author.firstName", target = "authorName")
    @Mapping(source = "author.email", target = "authorEmail")
    BlogDto toDtoFromEntity(Blog blog);

    @Mapping(source = "blogId", target = "id")
    Blog toEntityFromRequest(BlogRequest blogRequest);

    List<BlogDto> toDtoListFromEntityList(List<Blog> blogs);

}

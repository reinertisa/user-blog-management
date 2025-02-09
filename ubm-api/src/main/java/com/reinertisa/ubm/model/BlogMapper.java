package com.reinertisa.ubm.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public interface BlogMapper {

    @Mappings({
            @Mapping(source = "id", target = "blogId"),
            @Mapping(source = ".", target = "authorName", qualifiedByName = "combineNames"),
            @Mapping(source = "author.email", target = "authorEmail")
    })
    BlogDto toDtoFromEntity(Blog blog);

    @Named("combineNames")
    default String combineNames(Blog blog) {
        return blog.getAuthor().getFirstName() + " " + blog.getAuthor().getLastName();
    }

    @Mapping(source = "blogId", target = "id")
    Blog toEntityFromRequest(BlogRequest blogRequest);

    List<BlogDto> toDtoListFromEntityList(List<Blog> blogs);
}

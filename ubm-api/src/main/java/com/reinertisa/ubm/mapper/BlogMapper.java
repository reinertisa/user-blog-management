package com.reinertisa.ubm.mapper;

import com.reinertisa.ubm.entity.BlogEntity;
import com.reinertisa.ubm.dto.Blog;
import com.reinertisa.ubm.dtorequest.BlogRequest;
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
    Blog toDtoFromEntity(BlogEntity blogEntity);

    @Named("combineNames")
    default String combineNames(BlogEntity blogEntity) {
        return blogEntity.getAuthor().getFirstName() + " " + blogEntity.getAuthor().getLastName();
    }

    @Mapping(source = "blogId", target = "id")
    BlogEntity toEntityFromRequest(BlogRequest blogRequest);

    List<Blog> toDtoListFromEntityList(List<BlogEntity> blogEntities);
}

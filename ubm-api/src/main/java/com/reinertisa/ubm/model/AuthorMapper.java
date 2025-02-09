package com.reinertisa.ubm.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, BlogMapper.class})
public interface AuthorMapper {

    @Mapping(source = "address", target = "addressDto")
    @Mapping(source = "blogs", target = "blogsDto")
    AuthorDto toDtoFromEntity(Author author);

    @Mapping(source = "addressRequest", target="address")
    Author toEntityFromRequest(AuthorRequest authorRequest);

    List<AuthorDto> toDtoListFromEntityList(List<Author> authors);
}

package com.reinertisa.ubm.mapper;

import com.reinertisa.ubm.entity.AuthorEntity;
import com.reinertisa.ubm.dtorequest.dto.AuthorDto;
import com.reinertisa.ubm.dtorequest.request.AuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, BlogMapper.class})
public interface AuthorMapper {

    @Mapping(source = "address", target = "addressDto")
    @Mapping(source = "blogs", target = "blogsDto")
    AuthorDto toDtoFromEntity(AuthorEntity authorEntity);

    @Mapping(source = "addressRequest", target="address")
    AuthorEntity toEntityFromRequest(AuthorRequest authorRequest);

    List<AuthorDto> toDtoListFromEntityList(List<AuthorEntity> authorEntities);
}

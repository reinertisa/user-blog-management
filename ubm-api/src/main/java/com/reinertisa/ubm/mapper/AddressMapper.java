package com.reinertisa.ubm.mapper;


import com.reinertisa.ubm.entity.AddressEntity;
import com.reinertisa.ubm.dtorequest.dto.AddressDto;
import com.reinertisa.ubm.dtorequest.request.AddressRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {


    @Mapping(source = "id", target="addressId")
    AddressDto toDtoFromEntity(AddressEntity addressEntity);

    @Mapping(source = "addressId", target = "id")
    AddressEntity toEntityFromRequest(AddressRequest addressRequest);

    List<AddressDto> toDtoListFromEntityList(List<AddressEntity> addressEntities);
}

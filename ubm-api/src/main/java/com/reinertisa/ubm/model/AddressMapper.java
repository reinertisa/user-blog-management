package com.reinertisa.ubm.model;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {


    @Mapping(source = "id", target="addressId")
    AddressDto toDtoFromEntity(Address address);

    @Mapping(source = "addressId", target = "id")
    Address toEntityFromRequest(AddressRequest addressRequest);

    List<AddressDto> toDtoListFromEntityList(List<Address> addresses);
}

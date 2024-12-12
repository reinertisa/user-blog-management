package com.reinertisa.ubm.service;

import com.reinertisa.ubm.model.AddressDto;
import com.reinertisa.ubm.model.AddressRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface AddressService {

    List<AddressDto> getAllAddresses();

    AddressDto createAddress(@Valid AddressRequest addressRequest);

    void deleteAddress(Long id);

}

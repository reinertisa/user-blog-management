package com.reinertisa.ubm.service;

import com.reinertisa.ubm.dtorequest.dto.AddressDto;
import com.reinertisa.ubm.dtorequest.request.AddressRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface AddressService {

    List<AddressDto> getAllAddresses();

    AddressDto createAddress(@Valid AddressRequest addressRequest);

    void deleteAddress(Long id);

}

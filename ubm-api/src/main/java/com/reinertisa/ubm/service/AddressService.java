package com.reinertisa.ubm.service;

import com.reinertisa.ubm.dto.Address;
import com.reinertisa.ubm.dtorequest.AddressRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddresses();

    Address createAddress(@Valid AddressRequest addressRequest);

    void deleteAddress(Long id);

}

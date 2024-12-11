package com.reinertisa.ubm.service;

import com.reinertisa.ubm.model.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddresses();

    Address createAddress(Address address);

    void deleteAddress(Long id);

}

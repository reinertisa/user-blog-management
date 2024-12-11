package com.reinertisa.ubm.service;

import com.reinertisa.ubm.model.Address;
import com.reinertisa.ubm.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address createAddress(Address address) {
        return null;
    }

    @Override
    public void deleteAddress(Long id) {

    }
}

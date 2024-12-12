package com.reinertisa.ubm.service;

import com.reinertisa.ubm.model.Address;
import com.reinertisa.ubm.model.AddressDto;
import com.reinertisa.ubm.model.AddressRequest;
import com.reinertisa.ubm.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(address -> new AddressDto(
                address.getId(),
                address.getCity(),
                address.getState(),
                address.getCountry()
        )).toList();
    }

    @Override
    public AddressDto createAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setCountry(addressRequest.getCountry());

        return new AddressDto(
                address.getId(),
                address.getCity(),
                address.getState(),
                address.getCountry()
        );
    }

    @Override
    public void deleteAddress(Long id) {

    }
}

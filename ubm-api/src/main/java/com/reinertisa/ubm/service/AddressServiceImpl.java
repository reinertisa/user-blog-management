package com.reinertisa.ubm.service;

import com.reinertisa.ubm.model.Address;
import com.reinertisa.ubm.model.AddressDto;
import com.reinertisa.ubm.model.AddressMapper;
import com.reinertisa.ubm.model.AddressRequest;
import com.reinertisa.ubm.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public List<AddressDto> getAllAddresses() {
        return addressMapper.toDtoListFromEntityList(addressRepository.findAll());
    }

    @Override
    @Transactional
    public AddressDto createAddress(AddressRequest addressRequest) {
        Address address = addressMapper.toEntityFromRequest(addressRequest);
        addressRepository.save(address);
        return addressMapper.toDtoFromEntity(address);
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {

    }
}

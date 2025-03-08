package com.reinertisa.ubm.service.impl;

import com.reinertisa.ubm.entity.AddressEntity;
import com.reinertisa.ubm.dto.Address;
import com.reinertisa.ubm.mapper.AddressMapper;
import com.reinertisa.ubm.dtorequest.AddressRequest;
import com.reinertisa.ubm.repository.AddressRepository;
import com.reinertisa.ubm.service.AddressService;
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
    public List<Address> getAllAddresses() {
        return addressMapper.toDtoListFromEntityList(addressRepository.findAll());
    }

    @Override
    @Transactional
    public Address createAddress(AddressRequest addressRequest) {
        AddressEntity addressEntity = addressMapper.toEntityFromRequest(addressRequest);
        addressRepository.save(addressEntity);
        return addressMapper.toDtoFromEntity(addressEntity);
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {

    }
}

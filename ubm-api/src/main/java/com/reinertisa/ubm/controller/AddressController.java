package com.reinertisa.ubm.controller;

import com.reinertisa.ubm.model.AddressDto;
import com.reinertisa.ubm.model.AddressRequest;
import com.reinertisa.ubm.service.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private final AddressServiceImpl addressService;

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<AddressDto> addresses = addressService.getAllAddresses();
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressRequest addressReques) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.createAddress(addressReques));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


}

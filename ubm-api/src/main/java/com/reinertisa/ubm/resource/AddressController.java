package com.reinertisa.ubm.resource;

import com.reinertisa.ubm.dtorequest.dto.AddressDto;
import com.reinertisa.ubm.dtorequest.request.AddressRequest;
import com.reinertisa.ubm.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/v1/addresses")
@CrossOrigin(origins = "*")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        try {
            List<AddressDto> addresses = addressService.getAllAddresses();
            return ResponseEntity.status(HttpStatus.OK).body(addresses);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressRequest addressRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.createAddress(addressRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}

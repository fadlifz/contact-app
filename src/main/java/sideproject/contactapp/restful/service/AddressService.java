package sideproject.contactapp.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import sideproject.contactapp.restful.controller.ErrorController;
import sideproject.contactapp.restful.entity.Address;
import sideproject.contactapp.restful.entity.Contact;
import sideproject.contactapp.restful.entity.User;
import sideproject.contactapp.restful.model.AddressResponse;
import sideproject.contactapp.restful.model.CreateAddressRequest;
import sideproject.contactapp.restful.model.UpdateAddressRequest;
import sideproject.contactapp.restful.model.WebResponse;
import sideproject.contactapp.restful.repository.AddressRepository;
import sideproject.contactapp.restful.repository.ContactRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

        @Autowired
        private ContactRepository contactRepository;

        @Autowired
        private AddressRepository addressRepository;

        @Autowired
        private ValidationService validationService;

        private ErrorController errorController;

        @Transactional
        public AddressResponse create(User user, CreateAddressRequest request) {
                validationService.validate(request);

                Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Contact is not found"));

                Address address = new Address();
                address.setId(UUID.randomUUID().toString());
                address.setContact(contact);
                address.setStreet(request.getStreet());
                address.setCity(request.getCity());
                address.setProvince(request.getProvince());
                address.setCountry(request.getCountry());
                address.setPostalCode(request.getPostalCode());

                addressRepository.save(address);

                return toAddressResponse(address);
        }

        private AddressResponse toAddressResponse(Address address) {
                return AddressResponse.builder()
                                .id(address.getId())
                                .street(address.getStreet())
                                .city(address.getCity())
                                .province(address.getProvince())
                                .country(address.getCountry())
                                .postalCode(address.getPostalCode())
                                .build();
        }

        @Transactional(readOnly = true)
        public AddressResponse get(User user, String contactId, String addressId) {
                Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Contact is not found"));

                Address address = addressRepository.findFirstByContactAndId(contact, addressId)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Address is not found"));

                return toAddressResponse(address);
        }

        @Transactional
        public AddressResponse update(User user, UpdateAddressRequest request) {
                validationService.validate(request);

                Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Contact is not found"));

                Address address = addressRepository.findFirstByContactAndId(contact, request.getAddressId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Address is not found"));

                address.setStreet(request.getStreet());
                address.setCity(request.getCity());
                address.setProvince(request.getProvince());
                address.setCountry(request.getCountry());
                address.setPostalCode(request.getPostalCode());
                addressRepository.save(address);

                return toAddressResponse(address);
        }

        @ExceptionHandler(ResponseStatusException.class)
        public ResponseEntity<WebResponse<String>> apiException(ResponseStatusException exception) {
                return ResponseEntity.status(exception.getStatusCode())
                                .body(WebResponse.<String>builder().errors(exception.getReason()).build());
        }

        @Transactional
        public void remove(User user, String contactId, String addressId) {
                Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Contact is not found"));

                Address address = addressRepository.findFirstByContactAndId(contact,
                                addressId)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Address is not found"));

                addressRepository.delete(address);
        }

        @Transactional(readOnly = true)
        public List<AddressResponse> list(User user, String contactId) {
                Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Contact is not found"));

                List<Address> addresses = addressRepository.findAllByContact(contact);
                return addresses.stream().map(this::toAddressResponse).toList();
        }
}
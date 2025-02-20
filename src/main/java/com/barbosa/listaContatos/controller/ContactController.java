package com.barbosa.listaContatos.controller;

import com.barbosa.listaContatos.dto.ContactDTO;
import com.barbosa.listaContatos.exception.ContactNotFoundException;
import com.barbosa.listaContatos.exception.NoContactRegisteredException;
import com.barbosa.listaContatos.exception.RepositoryException;
import com.barbosa.listaContatos.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;

    }

    @PostMapping(value = "/new")
    public ResponseEntity<String> createContact(@Valid @RequestBody ContactDTO contactDTO) {
        try {
            contactService.createNewContact(contactDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("Contact created!");

        } catch (RepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }

    }

    @GetMapping
    public ResponseEntity<?> getAllContacts() {
        try {
            List<ContactDTO> contactsDTO = contactService.getAllContacts();

            return ResponseEntity.ok(contactsDTO);

        } catch (NoContactRegisteredException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());

        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getContactById(@PathVariable(name = "id") Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

            }

            return ResponseEntity.ok(contactService.getContactById(id));

        } catch (RepositoryException | ContactNotFoundException e) {
            HttpStatus errorStatus = e instanceof ContactNotFoundException ? HttpStatus.NO_CONTENT : HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(errorStatus).body(e.getMessage());

        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateContact(@PathVariable(name = "id") Long id, @RequestBody ContactDTO contactDTO) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

            }

            return ResponseEntity.ok(contactService.updateContact(id, contactDTO));

        } catch (RepositoryException | ContactNotFoundException e) {
            HttpStatus errorStatus = e instanceof ContactNotFoundException ? HttpStatus.NO_CONTENT : HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(errorStatus).body(e.getMessage());

        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable(name = "id") Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

            }

            contactService.deleteContact(id);

            return ResponseEntity.ok().body("Contact deleted with success!");

        } catch (RepositoryException | ContactNotFoundException e) {
            HttpStatus errorStatus = e instanceof ContactNotFoundException ? HttpStatus.NO_CONTENT : HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(errorStatus).body(e.getMessage());

        }

    }

}

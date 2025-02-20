package com.barbosa.listaContatos.service;

import com.barbosa.listaContatos.dto.ContactDTO;
import com.barbosa.listaContatos.entity.Contact;
import com.barbosa.listaContatos.exception.ContactNotFoundException;
import com.barbosa.listaContatos.exception.NoContactRegisteredException;
import com.barbosa.listaContatos.exception.RepositoryException;
import com.barbosa.listaContatos.mapper.ContactMapper;
import com.barbosa.listaContatos.repository.ContactRepository;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;

    }

    public List<ContactDTO> getAllContacts() throws NoContactRegisteredException {
        List<Contact> contacts = contactRepository.findAll();

        if (contacts.isEmpty()) {
            throw new NoContactRegisteredException("No contacts registered!");

        }

        return contacts
                .stream()
                .map(ContactMapper::toDTO)
                .collect(Collectors.toList());

    }

    public ContactDTO getContactById(Long id) throws RepositoryException, ContactNotFoundException {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found, try again!"));

        return ContactMapper.toDTO(contact);

    }

    public void createNewContact(ContactDTO contactDTO) throws RepositoryException {
        try {
            Contact contactEntity = ContactMapper.toEntity(contactDTO);
            contactRepository.save(contactEntity);

        } catch(DataAccessException e) {
            log.error("Failed to save new contact:", e);

            throw new RepositoryException("Failed to save new contact, try again later!");

        }

    }

    public ContactDTO updateContact(Long id, @NotNull ContactDTO contactDTO) throws RepositoryException, ContactNotFoundException {
        Contact contactToUpdate = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found, try again!"));

        if (contactDTO.getName() != null) contactToUpdate.setName(contactDTO.getName());
        if (contactDTO.getEmail() != null) contactToUpdate.setEmail(contactDTO.getEmail());
        if (contactDTO.getPhone() != null) contactToUpdate.setPhone(contactDTO.getPhone());

        try {
            Contact contact = contactRepository.save(contactToUpdate);

            return ContactMapper.toDTO(contact);

        } catch (DataAccessException e) {
            throw new RepositoryException("Failed to update contact, try again later!");

        }

    }

    public void deleteContact(Long id) throws RepositoryException, ContactNotFoundException {
        try {
            Contact contact = contactRepository.findById(id)
                    .orElseThrow(() -> new ContactNotFoundException("Contact not found, try again!"));

            contactRepository.delete(contact);

        } catch (DataAccessException | RepositoryException e) {
            log.error("Failed to delete contact:", e);

            throw new RepositoryException("Failed to delete the contact, try again later!");

        }

    }

}

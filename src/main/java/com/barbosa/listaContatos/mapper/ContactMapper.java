package com.barbosa.listaContatos.mapper;

import com.barbosa.listaContatos.dto.ContactDTO;
import com.barbosa.listaContatos.entity.Contact;

public class ContactMapper {
    public static ContactDTO toDTO(Contact contact) {
        return new ContactDTO(contact.getId(), contact.getName(), contact.getEmail(), contact.getPhone());

    }

    public static Contact toEntity(ContactDTO contactDTO) {
        return new Contact(contactDTO.getId(), contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhone());

    }

}

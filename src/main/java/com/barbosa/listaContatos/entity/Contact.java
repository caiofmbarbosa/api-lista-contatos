package com.barbosa.listaContatos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory!")
    @Size(min = 3, max = 80)
    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @NotBlank(message = "Email is mandatory!")
    @Email(message = "Email is invalid!")
    @Size(min = 3, max = 80)
    @Column(name = "email", nullable = false, length = 80)
    private String email;

    @NotBlank(message = "Phone is mandatory!")
    @Size(min = 9, max = 11)
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

}

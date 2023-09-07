package com.ipc2.proyectoipc2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String username;
    private String password;
    private String email;
}

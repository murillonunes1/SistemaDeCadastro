package com.cadastrodeusuarios.usuarios.expcetions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UsuarioNaoEncontradoException extends ResponseStatusException {
    public UsuarioNaoEncontradoException() {
        super(HttpStatus.BAD_REQUEST, "Usuário não encontrado por id");
    }
}

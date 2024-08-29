package com.cadastrodeusuarios.usuarios.controller;

import com.cadastrodeusuarios.usuarios.entity.UsuarioEntity;
import com.cadastrodeusuarios.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> salvarUsuario(@RequestBody UsuarioEntity usuarioEntity){
        System.out.print(usuarioEntity.getDepartamento().getDepartamento());
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioEntity));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos(){
        return ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable(name = "id") Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.ok("usuario removido com sucesso");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizarPorId(@PathVariable(name = "id") Long id, @RequestBody UsuarioEntity usuarioEntity) {
        return ResponseEntity.ok(usuarioService.editarUsuarioPorId(id, usuarioEntity));
    }
}

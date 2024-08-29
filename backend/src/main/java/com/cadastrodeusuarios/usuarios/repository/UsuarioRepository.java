package com.cadastrodeusuarios.usuarios.repository;

import com.cadastrodeusuarios.usuarios.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {
}

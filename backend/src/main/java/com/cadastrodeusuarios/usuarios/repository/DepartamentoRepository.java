package com.cadastrodeusuarios.usuarios.repository;

import com.cadastrodeusuarios.usuarios.entity.Departamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento, Long> {
    Optional<Departamento> findByDepartamento(String departamento);
}

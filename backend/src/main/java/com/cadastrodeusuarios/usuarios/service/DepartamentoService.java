package com.cadastrodeusuarios.usuarios.service;

import com.cadastrodeusuarios.usuarios.entity.Departamento;
import com.cadastrodeusuarios.usuarios.repository.DepartamentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Departamento salvar(Departamento departamento) {
        log.info("Salvar Departamento");
           Optional<Departamento> departamento1 = departamentoRepository.findByDepartamento(departamento.getDepartamento());
        return departamento1.orElseGet(() -> departamentoRepository.save(departamento));
    }
}

package com.cadastrodeusuarios.usuarios.service;

import com.cadastrodeusuarios.usuarios.entity.Departamento;
import com.cadastrodeusuarios.usuarios.entity.UsuarioEntity;
import com.cadastrodeusuarios.usuarios.expcetions.UsuarioNaoEncontradoException;
import com.cadastrodeusuarios.usuarios.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DepartamentoService departamentoService;

    public UsuarioEntity salvarUsuario(UsuarioEntity usuarioEntity){
        log.info("Salvar usuario");
        Departamento departamento = departamentoService.salvar(usuarioEntity.getDepartamento());
        usuarioEntity.setDepartamento(departamento);
        return usuarioRepository.save(usuarioEntity);
    }

    public List<UsuarioEntity> buscarTodos(){
        log.info("Listar todos usuarios");
        return (List<UsuarioEntity>) usuarioRepository.findAll();
    }

    public UsuarioEntity buscarPorId(Long id){
        log.info("Buscar usuario por id {} ", + id);
        Optional<UsuarioEntity> usuarioEntity1 = usuarioRepository.findById(id);
        if (usuarioEntity1.isPresent()) return usuarioEntity1.get();
        throw new UsuarioNaoEncontradoException();
    }

    public void deletarUsuario(Long id){
        log.info("Deletar usuario por id {} ", + id);
        UsuarioEntity usuarioEntity = buscarPorId(id);
        usuarioRepository.delete(usuarioEntity);
    }

    public UsuarioEntity editarUsuarioPorId(Long id, UsuarioEntity usuarioEntityUpdate) {
        log.info("Editar usuario por id {} ", + id);
        UsuarioEntity usuarioEntity = buscarPorId(id);
        if (!usuarioEntity.getDepartamento().equals(usuarioEntityUpdate.getDepartamento())) {
            // Salva o departamento se ele não existir no banco de dados
            Departamento departamento = departamentoService.salvar(usuarioEntityUpdate.getDepartamento());

            // Atualiza o departamento do usuário apenas se ele realmente mudou
            usuarioEntityUpdate.setDepartamento(departamento);
        }

        usuarioEntityUpdate.setId(usuarioEntity.getId());
        return usuarioRepository.save(usuarioEntityUpdate);
    }


}

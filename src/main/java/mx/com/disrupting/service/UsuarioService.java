/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.disrupting.service;

import java.util.Optional;
import mx.com.disrupting.dto.UsuarioDTO;
import mx.com.disrupting.entity.Usuario;
import mx.com.disrupting.repository.IUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ND
 */
@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO getUserBy(Integer id) {
        Optional<Usuario> findById = usuarioRepository.findById(id);
        return convertEntityToDTO(findById.get());
    }

    @Override
    public void saveUser(UsuarioDTO usuarioDTO) {

        usuarioRepository.save(convertDTOToEntity(usuarioDTO));
    }

    private UsuarioDTO convertEntityToDTO(Usuario usuario) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    private Usuario convertDTOToEntity(UsuarioDTO usuarioDTO) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

}

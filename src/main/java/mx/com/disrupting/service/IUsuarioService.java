/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.com.disrupting.service;

import mx.com.disrupting.dto.UsuarioDTO;

/**
 *
 * @author ND
 */
public interface IUsuarioService {
    
    public UsuarioDTO getUserBy(Integer id);
    
    public void saveUser(UsuarioDTO usuarioDTO);
}

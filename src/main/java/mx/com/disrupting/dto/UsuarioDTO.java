/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.disrupting.dto;

import lombok.Data;

/**
 *
 * @author ND
 */
@Data
public class UsuarioDTO {

    private Integer id;
    private String nombre;
    private String correo;
    private String alias;
    private Boolean estatus;
}

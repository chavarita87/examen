package mx.com.disrupting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import mx.com.disrupting.dto.UsuarioDTO;
import mx.com.disrupting.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping(path = "/usuario/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> guardar(@RequestBody UsuarioDTO usuarioDTO)
            throws SQLException {

        usuarioService.saveUser(usuarioDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/usuario/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> busca(@RequestParam(name = "id", required = true) Integer id)
            throws SQLException {

        UsuarioDTO usuarioDTO = usuarioService.getUserBy(id);

        if (usuarioDTO == null) {
            return ResponseEntity.notFound().build();// http status no found
        }

        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

}

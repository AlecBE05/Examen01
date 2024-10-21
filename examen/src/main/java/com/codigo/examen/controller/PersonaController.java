package com.codigo.examen.controller;

import com.codigo.examen.entity.PersonaEntity;
import com.codigo.examen.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona/v1")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/crear")
    public ResponseEntity<PersonaEntity> crearPersona(
            @RequestBody PersonaEntity persona){
        PersonaEntity nuevaPersona = personaService.crearPersona(persona);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }

    @GetMapping("/buscarTodos")
    public List<PersonaEntity> buscarTodos(){
        return personaService.buscarTodos();
    }

    @GetMapping("/buscar/{dni}")
    public ResponseEntity<PersonaEntity> obtenerPersona(@PathVariable String dni){
        PersonaEntity producto = personaService.buscarPersonaXnumDocumento(dni);
        return new ResponseEntity<>(producto,HttpStatus.OK);
    }

    @PutMapping("/actualizar/{dni}")
    public ResponseEntity<PersonaEntity> actualizarPersona (@PathVariable String dni, @RequestBody PersonaEntity persona) {
        PersonaEntity personaActualizada = personaService.actualizarPersona(dni,persona);
        return new ResponseEntity<>(personaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{dni}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable String dni) {
        personaService.eliminarPersona(dni);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

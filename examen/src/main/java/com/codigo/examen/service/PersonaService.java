package com.codigo.examen.service;

import com.codigo.examen.entity.PersonaEntity;

import java.util.List;

public interface PersonaService {

    PersonaEntity crearPersona(PersonaEntity persona);

    List<PersonaEntity> buscarTodos();

    PersonaEntity buscarPersonaXnumDocumento(String dni);

    //PersonaEntity buscarPorId(Long id);

    PersonaEntity actualizarPersona(String dni, PersonaEntity persona);

    void eliminarPersona(String dni);

}

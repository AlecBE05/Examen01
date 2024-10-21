package com.codigo.examen.service.impl;

import com.codigo.examen.entity.PedidoEntity;
import com.codigo.examen.entity.PersonaEntity;
import com.codigo.examen.enums.EstadoPedido;
import com.codigo.examen.enums.EstadoPersona;
import com.codigo.examen.repository.PedidoRepository;
import com.codigo.examen.repository.PersonaRepository;
import com.codigo.examen.service.PersonaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final PedidoRepository pedidoRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository, PedidoRepository pedidoRepository) {
        this.personaRepository = personaRepository;
        this.pedidoRepository = pedidoRepository;
    }

    //Crea la persona, a su vez su direccion y su pedido
    @Override
    public PersonaEntity crearPersona(PersonaEntity persona) {
        for (PedidoEntity pedido : persona.getPedidos()) {
            pedido.setEstado(EstadoPedido.PENDIENTE); // Establecer estado inicial
            pedido.setPersona(persona); // Asociar pedido a la persona
        }
        persona.setEstado(EstadoPersona.ACITVO);
        return personaRepository.save(persona);
    }

    //Busca todas la PERSONAS con el estado ACTIVO
    @Override
    public List<PersonaEntity> buscarTodos() {
        return personaRepository.findByEstado(EstadoPersona.ACITVO);
    }


    //Busca las personas por DNI
    @Override
    public PersonaEntity buscarPersonaXnumDocumento(String dni) {
        return personaRepository.findByEstadoQuery(dni);
    }

    //Actualiza una persona identificandolo por su DNI unico
    @Override
    public PersonaEntity actualizarPersona(String dni, PersonaEntity persona) {
       /* PersonaEntity personaExistente = personaRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error producto no encontrado"));*/
        PersonaEntity personaExistente = buscarPersonaXnumDocumento(dni);
        personaExistente.setNombre(persona.getNombre());
        personaExistente.setApellido(persona.getApellido());
        personaExistente.setDni(persona.getDni());
        personaExistente.setDireccionEntity(persona.getDireccionEntity());

        gestionarPedidos(personaExistente, persona.getPedidos());
        return personaRepository.save(personaExistente);
    }

    //metodo complementario para actualizar los pedidos a traves de persona
    private void gestionarPedidos(PersonaEntity personaExistente, List<PedidoEntity> pedidosActualizados ){
        List<PedidoEntity> pedidosExistentes = personaExistente.getPedidos();
        pedidosExistentes.clear();

        for(PedidoEntity pedido : pedidosActualizados){
            if(pedido.getId() != null){
                PedidoEntity pedidoEncontrado = pedidoRepository.findById(pedido.getId())
                        .orElseThrow(() -> new NoSuchElementException("Error pedido no ubicad"));
                pedidoEncontrado.setDescripcion(pedido.getDescripcion());
                pedidosExistentes.add(pedidoEncontrado);
            }else {
                pedido.setPersona(personaExistente);
                pedidosExistentes.add(pedido);
            }
        }
    }

    //Elimina persona por estado
    @Override
    public void eliminarPersona(String dni) {
        PersonaEntity persona = buscarPersonaXnumDocumento(dni);
        persona.setEstado(EstadoPersona.INACTIVO);
        personaRepository.save(persona);
    }
}

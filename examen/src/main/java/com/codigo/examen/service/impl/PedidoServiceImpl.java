package com.codigo.examen.service.impl;


import com.codigo.examen.entity.PedidoEntity;
import com.codigo.examen.entity.PersonaEntity;
import com.codigo.examen.enums.EstadoPedido;
import com.codigo.examen.repository.PedidoRepository;
import com.codigo.examen.repository.PersonaRepository;
import com.codigo.examen.service.PedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PersonaRepository personaRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, PersonaRepository personaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.personaRepository = personaRepository;
    }


    //Crea un pedido
    @Override
    public PedidoEntity crearPedido(Long personaId, PedidoEntity pedido) {
        PersonaEntity clienteExistente = personaRepository.findById(personaId)
                .orElseThrow(() -> new NoSuchElementException("Error persona no existe"));
        pedido.setPersona(clienteExistente);
        pedido.setEstado(EstadoPedido.PENDIENTE);
        return pedidoRepository.save(pedido);
    }

    //Busca pedidos por su ESTADO
    @Override
    public List<PedidoEntity> buscarPedidoPorParametro(EstadoPedido estado) {
        return pedidoRepository.findByEstado(estado);
    }

    //Busca pedido por su ID
    @Override
    public PedidoEntity buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("Pedido no encontrado"));
    }

    //Busca todos los pedidos registrados
    @Override
    public List<PedidoEntity> buscarTodos() {
        return pedidoRepository.findAll();
    }

    //Actualiza los pedidos
    @Override
    public PedidoEntity actualizarPedido(Long id, Long idPersona, PedidoEntity pedido) {
        PedidoEntity pedidoExistente = buscarPedidoPorId(id);
        PersonaEntity personaExistente = personaRepository.findById(idPersona).orElseThrow(() -> new RuntimeException("Erorr cliente a relacionar no existe"));
        pedidoExistente.setDescripcion(pedido.getDescripcion());
        pedidoExistente.setPersona(personaExistente);
        pedidoExistente.setEstado(pedido.getEstado());
        return pedidoRepository.save(pedidoExistente);
    }


    //Eliminar Pedido, mendiante estados
    @Override
    public void eliminarPedido(Long id) {
        PedidoEntity pedidoExistente = buscarPedidoPorId(id);
        pedidoExistente.setEstado(EstadoPedido.ELIMINADO);
        pedidoRepository.save(pedidoExistente);
    }
}

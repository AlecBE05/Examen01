package com.codigo.examen.service;

import com.codigo.examen.entity.PedidoEntity;
import com.codigo.examen.enums.EstadoPedido;

import java.util.List;

public interface PedidoService {

    PedidoEntity crearPedido(Long personaId, PedidoEntity pedido);

    List<PedidoEntity> buscarPedidoPorParametro(EstadoPedido estado);

    PedidoEntity buscarPedidoPorId(Long id);

    List<PedidoEntity> buscarTodos();

    PedidoEntity actualizarPedido(Long id, Long idPersona ,PedidoEntity pedido);

    void eliminarPedido(Long id);
}

package com.codigo.examen.repository;

import com.codigo.examen.entity.PedidoEntity;
import com.codigo.examen.enums.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity,Long> {

    List<PedidoEntity> findByEstado(EstadoPedido estado);
}

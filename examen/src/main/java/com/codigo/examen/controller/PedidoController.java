package com.codigo.examen.controller;

import com.codigo.examen.entity.PedidoEntity;
import com.codigo.examen.enums.EstadoPedido;
import com.codigo.examen.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido/v1")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/crear/{personaId}")
    public ResponseEntity<PedidoEntity> crearPedido(
            @PathVariable Long personaId,
            @RequestBody PedidoEntity pedido){
        PedidoEntity nuevoPedido = pedidoService.crearPedido(personaId,pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @GetMapping("/buscarTodos")
    public List<PedidoEntity> obtenerTodosLosPedidos() {
        return pedidoService.buscarTodos();
    }

    @GetMapping("/buscar/estado/{estado}")
    public ResponseEntity<List<PedidoEntity>> obtenerPedidoPorEstado(@PathVariable EstadoPedido estado){
        List<PedidoEntity> pedido = pedidoService.buscarPedidoPorParametro(estado);
        return new ResponseEntity<>(pedido,HttpStatus.OK);
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<PedidoEntity> obtenerPedidoPorId(@PathVariable Long id){
        PedidoEntity pedido = pedidoService.buscarPedidoPorId(id);
        return new ResponseEntity<>(pedido,HttpStatus.OK);
    }

    @PutMapping("/{id}/cliente/{idCLiente}")
    public ResponseEntity<PedidoEntity> actualizarPedido(@PathVariable Long id,
                                                         @PathVariable Long idCLiente,
                                                         @RequestBody PedidoEntity pedido) {
        PedidoEntity pedidoActualizado = pedidoService.actualizarPedido(id,idCLiente ,pedido);
        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

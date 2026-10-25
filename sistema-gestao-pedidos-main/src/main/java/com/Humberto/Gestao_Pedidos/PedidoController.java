package com.Humberto.Gestao_Pedidos;

import io . swagger . v3 . oas . annotations . Operation ;
import io . swagger . v3 . oas . annotations . responses . ApiResponse ;
import io . swagger . v3 . oas . annotations . tags . Tag ;
import jakarta . validation . Valid ;
import org . springframework . http . HttpStatus ;
import org . springframework . http . ResponseEntity ;
import org . springframework . web . bind . annotation .*;

import java . util . List ;
import java . util . Optional ;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Gerenciamento do ciclo de vida dos pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @GetMapping
    @Operation(summary = " Listar todos os pedidos ", description = "Retorna uma lista de todos os pedidos cadastrados . " )
    public List<Pedido> ListarPedidos(){
        return pedidoService.listarTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = " Criar um novo pedido " , description = " Cadastra um novo pedido e retorna os dados gerados com ID . ")
    @ApiResponse(responseCode = "201", description = " Pedido criado com sucesso " )
    public Pedido criarPedido(@Valid @RequestBody Pedido pedido){
        return pedidoService.criarPedido(pedido);
    }

    @GetMapping("/{id}")
    @Operation(summary = " Buscar pedido por ID ", description = " Retorna os detalhes de um pedido especifico . " )
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id){
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        return pedido.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = " Deletar um pedido " , description = "Deleta permanentemente um pedido do sistema pelo ID .")
    public ResponseEntity < Void > deletarPedido ( @PathVariable Long id ) {
        if(pedidoService.deletarPedido(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = " Atualizar um pedido ", description = " Atualiza cliente e valor total de um pedido existente . ")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @Valid @RequestBody Pedido dados ){
        Optional < Pedido > pedidoAtualizado = pedidoService . atualizar (id, dados);
        return pedidoAtualizado . map ( ResponseEntity :: ok ) . orElseGet (() -> ResponseEntity . notFound () . build () ) ;
    }
}

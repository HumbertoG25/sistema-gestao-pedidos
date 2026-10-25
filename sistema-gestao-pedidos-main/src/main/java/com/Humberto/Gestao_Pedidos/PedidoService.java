package com.Humberto.Gestao_Pedidos;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido criarPedido(Pedido pedido) {
       return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public boolean deletarPedido(long id){
        if(pedidoRepository.existsById(id)){
            pedidoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Pedido> atualizar(long id, Pedido dadosNovos){
        return pedidoRepository.findById(id).map(pedidoExistente -> {
            pedidoExistente.setCliente(dadosNovos.getCliente());
            pedidoExistente.setValorTotal(dadosNovos.getValorTotal());
            return pedidoRepository.save(pedidoExistente);
        });
    }
}

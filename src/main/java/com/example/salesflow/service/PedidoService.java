package com.example.salesflow.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.salesflow.controller.dto.cadastro.PedidoCadastroDTO;
import com.example.salesflow.controller.mappers.PedidoMapper;
import com.example.salesflow.model.Pedido;
import com.example.salesflow.model.Usuario;
import com.example.salesflow.repository.PedidoRepository;
import com.example.salesflow.repository.PedidoSpecs;
import static com.example.salesflow.repository.PedidoSpecs.departamentoEqual;
import static com.example.salesflow.repository.PedidoSpecs.loginUsuarioEqual;
import static com.example.salesflow.repository.PedidoSpecs.numPedidoEqual;
import static com.example.salesflow.repository.PedidoSpecs.palavraLike;
import static com.example.salesflow.repository.PedidoSpecs.statusEqual;
import com.example.salesflow.security.SecurityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
    
    private final PedidoRepository repository;
    private final PedidoMapper mapper;

    private final SecurityService securityService;

    public Pedido salvar(PedidoCadastroDTO dto){

        Pedido pedido = mapper.toEntity(dto);
        pedido.setStatus("EM ANÁLISE");

        Usuario usuario = securityService.obterUsuarioLogado();
        pedido.setUsuario(usuario);

        return repository.save(pedido);
    }

    public Page<Pedido> pesquisa(
                Long numPedido, String palavraChave, String loginUsuario, String departamento,
                LocalDate dataInicio, LocalDate dataFinal, String status, Integer pagina, Integer tamanhoPagina){
        
        Specification<Pedido> specs = null;

        if(numPedido != null && !(numPedido.toString()).isEmpty()){
            specs = (specs == null) ? numPedidoEqual(numPedido) : specs.and(numPedidoEqual(numPedido));
        }
        if(palavraChave != null && !palavraChave.isEmpty()){
            specs = (specs == null) ? palavraLike(palavraChave) : specs.and(palavraLike(palavraChave));
        }
        if(loginUsuario != null && !loginUsuario.isEmpty()){
            specs = (specs == null) ? loginUsuarioEqual(loginUsuario) : specs.and(loginUsuarioEqual(loginUsuario));
        }
        if(departamento != null && !departamento.isEmpty()){
            specs = (specs == null) ? departamentoEqual(departamento) : specs.and(departamentoEqual(departamento));
        }
        if(dataInicio != null || dataFinal != null){
            specs = (specs == null) ? PedidoSpecs.intervaloSolicitacaoIsBetween(dataInicio, dataFinal) : 
                                        specs.and(PedidoSpecs.intervaloSolicitacaoIsBetween(dataInicio, dataFinal));
        }
        if(status != null && !status.isEmpty()){
            specs = (specs == null) ? statusEqual(status) : specs.and(statusEqual(status));
        }

        Pageable pageable = PageRequest.of(pagina, tamanhoPagina);

        return repository.findAll(specs, pageable);
    }

    public Pedido buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void atualizarStatus(Long id, String status){
        System.out.println("SERVICE");
        Pedido pedido = repository.findById(id).orElse(null); 
        pedido.setStatus(status);
        repository.save(pedido);
    }
}

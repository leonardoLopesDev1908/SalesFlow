package com.example.salesflow.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

        Usuario usuario = securityService.obterUsuarioLogado();
        pedido.setUsuario(usuario);

        return repository.save(pedido);
    }


    public Page<Pedido> pesquisa(
                Long numPedido, String palavraChave, String loginUsuario, String departamento,
                LocalDate dataInicio, LocalDate dataFinal, Integer pagina, Integer tamanhoPagina){

        Specification<Pedido> specs = null;

        if(numPedido != null && !(numPedido.toString()).isEmpty()){
            specs = (specs == null) ? numPedidoEqual(numPedido) : specs.and(numPedidoEqual(numPedido));
        }
        if(palavraChave != null && !(palavraChave.toString()).isEmpty()){
            specs = (specs == null) ? palavraLike(palavraChave) : specs.and(palavraLike(palavraChave));
        }
        if(loginUsuario != null && !(loginUsuario.toString()).isEmpty()){
            specs = (specs == null) ? loginUsuarioEqual(loginUsuario) : specs.and(loginUsuarioEqual(loginUsuario));
        }
        if(departamento != null && !(departamento.toString()).isEmpty()){
            specs = (specs == null) ? departamentoEqual(departamento) : specs.and(departamentoEqual(departamento));
        }
        if(dataInicio != null || dataFinal != null){
            specs = (specs == null) ? PedidoSpecs.intervaloSolicitacaoIsBetween(dataInicio, dataFinal) : 
                                        specs.and(PedidoSpecs.intervaloSolicitacaoIsBetween(dataInicio, dataFinal));
        }
        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return repository.findAll(specs, pageRequest);
    }
}

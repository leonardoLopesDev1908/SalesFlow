package com.example.salesflow.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.salesflow.controller.dto.cadastro.ItemNotaFiscalCadastroDTO;
import com.example.salesflow.controller.dto.cadastro.NotaFiscalCadastroDTO;
import com.example.salesflow.controller.mappers.ItemNotasFiscalMapper;
import com.example.salesflow.controller.mappers.NotaFiscalMapper;
import com.example.salesflow.model.Cliente;
import com.example.salesflow.model.Fornecedor;
import com.example.salesflow.model.ItemNotaFiscal;
import com.example.salesflow.model.NotaFiscal;
import com.example.salesflow.model.Produto;
import com.example.salesflow.model.TransacaoType;
import com.example.salesflow.repository.ClienteRepository;
import com.example.salesflow.repository.FornecedorRepository;
import com.example.salesflow.repository.NotaFiscalSpecs;
import static com.example.salesflow.repository.NotaFiscalSpecs.clienteCpfEqual;
import static com.example.salesflow.repository.NotaFiscalSpecs.fornecedorCnpjEqual;
import static com.example.salesflow.repository.NotaFiscalSpecs.tipoTransacaoEqual;
import com.example.salesflow.repository.NotasRepository;
import com.example.salesflow.repository.ProdutoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotasService {
    
    private final NotasRepository notaFiscalRepository;

    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorService fornecedorService;

    private final NotaFiscalMapper notaFiscalMapper;
    private final ItemNotasFiscalMapper itemMapper;

    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;


    @Transactional
    public NotaFiscal salvar(NotaFiscalCadastroDTO dto){
        NotaFiscal notaFiscal = notaFiscalMapper.toEntity(dto);
        
        if(notaFiscal.getTipoTransacao().equals(TransacaoType.VENDA)){
            Cliente cliente = clienteRepository.findByCpf(dto.clienteCpf());
            notaFiscal.setCliente(cliente);
            notaFiscal.setFornecedor(null);
        } else if (notaFiscal.getTipoTransacao().equals(TransacaoType.COMPRA)){
            Fornecedor fornecedor = fornecedorRepository.findByCnpj(dto.fornecedorCnpj());
            notaFiscal.setFornecedor(fornecedor);
            notaFiscal.setCliente(null);
        }

        BigDecimal valorTotal = BigDecimal.ZERO;

        for(ItemNotaFiscalCadastroDTO itemDTO : dto.itens()){
            Produto produto = produtoService.obterPorId(itemDTO.produtoId()).orElse(null);
        
            ItemNotaFiscal itemNotaFiscal = itemMapper.toEntity(itemDTO);   
            itemNotaFiscal.setProduto(produto);

            notaFiscal.addItem(itemNotaFiscal);
            
            BigDecimal subTotal = itemDTO.precoUnitario().multiply(BigDecimal.valueOf(itemDTO.quantidade()));
            valorTotal = valorTotal.add(subTotal);
            
            atualizarEstoque(produto, itemDTO.quantidade(), notaFiscal.getTipoTransacao());
        }

        notaFiscal.setValorTotal(valorTotal);

        return notaFiscalRepository.save(notaFiscal);
    }

    public NotaFiscal buscaPorId(Long id){
        return notaFiscalRepository.findById(id).orElse(null);
    }

    public List<NotaFiscal> buscaPorCpf(String cpf){
        Cliente cliente = clienteService.obterPorCpf(cpf);
    
        return notaFiscalRepository.findByCliente(cliente);
    }

    public List<NotaFiscal> buscaPorCnpj(String cnpj){
        Fornecedor fornecedor = fornecedorService.obterPorCnpj(cnpj);

        return notaFiscalRepository.findByFornecedor(fornecedor);
    }

    public Page<NotaFiscal> pesquisa(
                TransacaoType tipoTransacao, String clienteCpf, String fornecedorCnpj,
                LocalDate dataInicio, LocalDate dataFinal, Integer pagina, Integer tamanhoPagina){

        Specification<NotaFiscal> specs = null;

        if (tipoTransacao != null){
            specs = (specs == null) ? tipoTransacaoEqual(tipoTransacao) : specs.and(tipoTransacaoEqual(tipoTransacao));
        }
        if(clienteCpf != null && !clienteCpf.isEmpty()){
            specs = (specs == null) ? clienteCpfEqual(clienteCpf) : specs.and(clienteCpfEqual(clienteCpf));
        }
        else if(fornecedorCnpj != null && !fornecedorCnpj.isEmpty()){
            specs = (specs == null) ? fornecedorCnpjEqual(fornecedorCnpj) : specs.and(fornecedorCnpjEqual(fornecedorCnpj));
        }   
        if (dataInicio != null || dataFinal != null) {
            specs = specs.and(NotaFiscalSpecs.intervaloEmissaoIsBetween(dataInicio, dataFinal));
        }
        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return notaFiscalRepository.findAll(specs, pageRequest);
    }


    private void atualizarEstoque(Produto produto, Integer quantidade, TransacaoType transacao){
        if (transacao.equals(TransacaoType.COMPRA)){
            produtoRepository.aumentarEstoque(produto.getId(), quantidade);
        } else if(transacao.equals(TransacaoType.VENDA)){
            produtoRepository.diminuirEstoque(produto.getId(), quantidade);
        }
    }

}

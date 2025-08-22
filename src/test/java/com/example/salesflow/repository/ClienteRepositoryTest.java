package com.example.salesflow.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.salesflow.model.Cliente;

@SpringBootTest
class ClienteRepositoryTest {
    
    @Autowired
    private ClienteRepository repository;

    @Test
    void salvarTest(){
        Cliente cliente = new Cliente();
        cliente.setNome("Claudio Lopes");
        cliente.setCpf("875.458.960-68");
        cliente.setEmail("claudiolopes@salesflow.com");
        cliente.setEndereco("Rua Abc, 123. Defgh, RS");
        cliente.setTelefone("(012) 34567-8910");

        repository.save(cliente);
    }

}

# SalesFlow
<img width="1919" height="934" alt="Captura de tela 2025-08-22 195718" src="https://github.com/user-attachments/assets/dbffddaf-f493-426b-8568-44dff024421e" />

**SalesFlow** é um sistema ERP completo desenvolvido com Spring Boot, projetado para otimizar o gerenciamento de vendas, compras e operações fiscais de uma empresa. Ele permite controlar notas fiscais, cadastrar e consultar produtos, clientes e fornecedores, além de gerar relatórios estratégicos sobre desempenho comercial.

## 🔍 Funcionalidades

- **Notas Fiscais**
  - Emissão completa de notas de entrada e saída
  - Associações inteligentes com produtos, clientes e fornecedores
  - Geração automática de XML e PDF com layout profissional
  - Envio automático da nota fiscal em PDF para o email do cliente

- **Cadastros**
  - CRUD completo de clientes, produtos, fornecedores, notas fiscais e pedidos
  - Filtros avançados e buscas por nome, CNPJ, produto, etc.
  - Interface intuitiva para gestão de dados

- **Pesquisa**
  - Sistema de busca com Specifications do Spring Data JPA
  - Filtros dinâmicos para localização rápida de informações

Pesquisa de Produtos com Specs
<img width="1745" height="835" alt="Captura de tela 2025-08-20 130341" src="https://github.com/user-attachments/assets/1888474c-ed09-42b9-8756-7e9613f9f625" />

Solicitação de pedidos (simulação de departamento solicitando materiais de trabalho)
<img width="1915" height="937" alt="Captura de tela 2025-08-22 184252" src="https://github.com/user-attachments/assets/3440b93b-a618-4a78-9a64-3747e30243f3" />

Gerenciamento de pedido para usuário com Authority 'GERENTE'.
  - Possibilidade de analisar cada pedido e NEGAR ou REJEITAR
<img width="1915" height="934" alt="Captura de tela 2025-08-22 184309" src="https://github.com/user-attachments/assets/3575b600-de49-4e14-87a0-ca8aee799eb2" />

- **Relatórios Inteligentes**
  - Quais clientes mais compram
  - Quais produtos são mais vendidos
  - Quais fornecedores mais fornecem

## 🛠️ Tecnologias utilizadas

- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **Thymeleaf (painel administrativo)**
- **PostgreSQL**
- **Lombok**

## 📦 Estrutura do Projeto
SalesFlow/
├── src/main/java/
│   └── com/salesflow/
│       ├── config/          
│       ├── controller/     
│       ├── exceptions/        
│       ├── model/      #
│       ├── repository/         
│       ├── security/            
│       ├── service/        
│       └── validator/         
├── src/main/resources/
│   ├── templates/          
│   ├── static/            
│   └── application.yml
└── pom.xml

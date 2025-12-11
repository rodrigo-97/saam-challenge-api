# Projeto API - Backend

Este projeto é uma aplicação backend modular em Java.

## Estrutura de Pastas

### `components`
Agrupa componentes reaproveitáveis da aplicação que podem ser usados por diferentes módulos ou serviços, centralizando funcionalidades comuns.

### `configs`
Contém todas as configurações globais da aplicação, incluindo segurança, documentação da API (OpenAPI) e tratamento de exceções.

### `exceptions`
Define todas as exceções personalizadas do sistema, permitindo lidar de forma padronizada com erros específicos de negócio e validação.

### `models`
Contém as entidades e modelos de dados que representam as principais estruturas do sistema, como usuários e funcionários.

### `modules`
Agrupa a lógica de negócio da aplicação em módulos independentes e específicos, como autenticação, funcionários, métricas e perfil de usuário. Cada módulo encapsula controllers, serviços e regras de negócio relacionadas.

- **auth**: módulo de autenticação e cadastro de usuários.
- **employees**: módulo para gerenciar operações de funcionários (criar, atualizar, ativar, desativar, deletar, buscar).
- **metrics**: módulo que fornece dados e métricas do sistema.
- **profile**: módulo relacionado ao perfil e informações do usuário logado.

### `repositories`
Contém os repositórios responsáveis pela comunicação com o banco de dados, abstraindo operações de persistência e consultas.

### `shared`
Agrupa recursos reutilizáveis entre diferentes módulos, como parâmetros comuns (ex.: paginação) e estruturas de retorno padronizadas.


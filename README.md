# 🎓 ProjectIdeaAI

**ProjectIdeaAI** é uma aplicação Spring Boot que gera ideias de projetos de software usando a API da OpenAI, com base em dados previamente cadastrados no banco de dados.

<div align="center">
  <img src="ProjectIdeaAI.png" alt="Logo do Projeto" width="250"/>
  <h3>Gere ideias de projetos de forma automática!</h3>


![Java](https://img.shields.io/badge/Java-17-brightgreen?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.4-brightgreen?style=for-the-badge&logo=springboot&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2_Database-in--memory-blue?style=for-the-badge&logo=h2&logoColor=white)
![OpenAI API](https://img.shields.io/badge/OpenAI_API-ChatGPT-purple?style=for-the-badge&logo=openai&logoColor=white)
</div>

---

## 📋 Índice

- [Sobre](#-sobre)
- [Como Funciona](#-como-funciona)
- [Tecnologias](#-tecnologias)
- [Instalação](#-instalação)
- [Uso](#-uso)
- [Modelo de Dados](#-modelo-de-dados)
- [Exemplo de Entrada](#-exemplo-de-entrada)
- [Repositório](#-repositório)
- [Licença](#-licença)

---

## 📖 Sobre

Este projeto é uma API REST que consome a OpenAI Chat Completions para gerar uma ideia única de projeto baseada em todas as tecnologias cadastradas no banco de dados H2.

---

## 🚀 Como Funciona

1. **Inicialização**:
    - O ponto de entrada é a classe `ProjectIdeaAiApplication`.
2. **Configuração HTTP**:
    - `WebClientConfig.java` configura o `WebClient` para chamar a API da OpenAI.
3. **Persistência**:
    - Os repositórios `IdeaRepository` e `TecnologiaRepository` usam Spring Data JPA para CRUD.
4. **Modelo**:
    - As entidades principais são `Idea` e `Tecnologia`, com relacionamento Many-to-Many.
5. **Serviços**:
    - `ChatGptService` formata os dados do banco em JSON e envia o prompt à OpenAI.
    - `IdeaService` e `TecnologiaService` gerenciam as operações CRUD dessas entidades.
6. **Controladores REST**:
    - `ProjectIdeaController`, `IdeaController` e `TecnologiaController` expõem endpoints para gerar ideias e gerenciar recursos.
7. **Banco de Dados**:
    - Utiliza H2 em memória para desenvolvimento, com console em `/h2-console`.
    - Migrações são gerenciadas via Flyway.

---

## 🛠️ Tecnologias

- **Java 17**
- **Spring Boot 3.4.4**
- **Spring WebFlux** (WebClient)
- **Spring Data JPA**
- **H2 Database**
- **Flyway**
- **OpenAI API** (ChatGPT)
- **Maven**

---

## 💻 Instalação

### Pré‑requisitos

- Java 17 ou superior
- Maven
- Variável de ambiente `API_KEY` com sua chave da OpenAI

### Passo a passo

```bash
# Clonar o repositório
git clone https://github.com/tonhowtf/ProjectIdeaAI.git
cd ProjectIdeaAI

# Definir variável de ambiente (Linux/macOS)
export API_KEY="sua_chave_aqui"

# Construir e executar
mvn clean install
mvn spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

---

## 📁 Uso

- **GET** `/ideas` – lista todas as ideias cadastradas.
- **POST** `/ideas` – cadastra uma nova ideia no banco.
- **GET** `/generate` – gera uma ideia de projeto única usando as entradas do banco.

---

## 🗃️ Modelo de Dados

### Idea

| Campo         | Tipo                  |
|---------------|-----------------------|
| `id`          | Long                  |
| `nome`        | String                |
| `descricao`   | String                |
| `categoria`   | Enum `Categoria`      |
| `dataCriacao` | LocalDateTime         |
| `tecnologias` | List<Tecnologia>      |

### Tecnologia

| Campo     | Tipo                 |
|-----------|----------------------|
| `id`      | Long                 |
| `nome`    | String               |
| `tipo`    | Enum `TipoTecnologia`|
| `ideias`  | List<Idea>           |

---

## 🔧 Exemplo de Entrada

Suponha que o banco contenha o seguinte JSON de uma ideia:

```json
{
  "nome": "Plataforma de IA para Devs",
  "descricao": "Uma plataforma que ajuda devs a criar bots com IA.",
  "categoria": "WEB",
  "tecnologias": [
    { "nome": "Java", "tipo": "BACKEND" },
    { "nome": "Spring Boot", "tipo": "FRAMEWORK" },
    { "nome": "OpenAI API", "tipo": "API" }
  ]
}
```

Ao chamar **GET** `/generate`, a API retornará uma única ideia de projeto baseada em todas essas tecnologias.

---

## 📦 Repositório

🔗 https://github.com/tonhowtf/ProjectIdeaAI

---

## 📜 Licença

Este projeto está licenciado sob a **MIT License**.

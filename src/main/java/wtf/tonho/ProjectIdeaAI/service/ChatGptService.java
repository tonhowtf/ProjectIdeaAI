package wtf.tonho.ProjectIdeaAI.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import wtf.tonho.ProjectIdeaAI.model.Idea;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private String apiKey = System.getenv("API_KEY");

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateProject(List<Idea> ideas) {
        String inputJson = ideas.stream()
                .map(idea -> {
                    String tecnologiasJson = idea.getTecnologias().stream()
                            .map(tech -> String.format(
                                    "{ \"id\": %d, \"nome\": \"%s\", \"tipo\": \"%s\", \"ideias\": [] }",
                                    tech.getId(), tech.getNome(), tech.getTipo()
                            ))
                            .collect(Collectors.joining(",\n"));

                    return String.format("""
                            {
                              \"id\": %d,
                              \"nome\": \"%s\",
                              \"descricao\": \"%s\",
                              \"categoria\": \"%s\",
                              \"tecnologias\": [
                                %s
                              ],
                              \"dataCriacao\": \"%s\"
                            }
                            """,
                            idea.getId(),
                            idea.getNome(),
                            idea.getDescricao(),
                            idea.getCategoria(),
                            tecnologiasJson,
                            idea.getDataCriacao()
                    );
                })
                .collect(Collectors.joining(",\n"));

        String prompt = String.format("""
[
  {
    "role": "system",
    "content": "Você é o **ProjectIdeaAI Assistant**, especializado em gerar uma única ideia de projeto e um README completo a partir de um JSON de entrada."
  },
  {
    "role": "user",
    "content": "```json\n%s\n```"
  },
  {
    "role": "user",
    "content": \"\"\"
**Instruções**:
1. **Leia** o JSON fornecido exatamente como está.
2. **Gere** uma **única ideia** de projeto usando **todas** as tecnologias listadas em `tecnologias`.
3. **Produza** primeiro um bloco JSON com este schema estrito:
```json
{
  \"nome\": \"<string>\",
  \"descricao\": \"<string>\",
  \"categoria\": \"<WEB|MOBILE|DESKTOP|GAME|DATA_SCIENCE|IA|IOT>\",
  \"tecnologias\": [
    { \"nome\": \"<string>\", \"tipo\": \"<FRONTEND|BACKEND|DATABASE|MOBILE|DEVOPS|IA|CLOUD|FRAMEWORK|API>\" }
  ]
}
```
4. **Em seguida**, gere um **README.md** em markdown, usando o template mustache abaixo **sem alterar a estrutura**.
5. Por fim, valide que **todas** as tecnologias foram usadas e que o JSON e o README seguem o formato exato.

**Template README.md**:
```md
---
# 🎓 {{nome}}

<div align=\"center\">
  <img src=\"ImagemDoProjeto.png\" alt=\"Logo do Projeto\" width=\"500\"/>
  <h3>{{descricao_curta}}</h3>
  <p>
    {{#tecnologias}}
      ![](https://img.shields.io/badge/{{nome}}-{{tipo}}-brightgreen?style=for-the-badge&logo={{nome}}&logoColor=white)
    {{/tecnologias}}
  </p>
</div>

## 📋 Índice
- [Sobre](#-sobre)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias](#-tecnologias)
- [Instalação](#-instalação)
- [Modelo de Dados](#-modelo-de-dados)
- [Repositório](#-repositório)
- [Licença](#-licença)

## 📖 Sobre
{{descricao}}

## ✨ Funcionalidades
{{#funcionalidades}}
- {{.}}
{{/funcionalidades}}

## 🔧 Tecnologias
**Backend**
{{#tecnologias}}
{{#is_tipo FRONTEND}}- {{nome}}{{/is_tipo}}
{{/tecnologias}}
**Frontend**
{{#tecnologias}}
{{#is_tipo BACKEND}}- {{nome}}{{/is_tipo}}
{{/tecnologias}}
**Framework**
{{#tecnologias}}
{{#is_tipo FRAMEWORK}}- {{nome}}{{/is_tipo}}
{{/tecnologias}}
**Banco de Dados**
{{#tecnologias}}
{{#is_tipo DATABASE}}- {{nome}}{{/is_tipo}}
{{/tecnologias}}

## 🚀 Instalação

### Pré‑requisitos
- Java 17+
- Maven
- Docker

### Passo a passo
```bash
git clone https://github.com/seuusuario/{{slug_name}}.git
cd {{slug_name}}
mvn clean install
mvn spring-boot:run
```
Acesse `http://localhost:8080`.

## 🗃️ Modelo de Dados

- **Idea**
  - `id` (Long)
  - `nome` (String)
  - `descricao` (String)
  - `categoria` (String)
  - `dataCriacao` (Timestamp)
  - `tecnologias` (List<Tecnologia>)

- **Tecnologia**
  - `id` (Long)
  - `nome` (String)
  - `tipo` (String)
  - Many-to-Many com **Idea**

## 📦 Repositório
🔗 https://github.com/seuusuario/{{slug_name}}

## 📜 Licença
Este projeto está licenciado sob a Licença MIT.
---
\"\"\"
  }
]
""", inputJson);

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of(
                                "role", "user",
                                "content", prompt
                        )
                ),
                "temperature", 1,
                "max_tokens", 2048,
                "top_p", 1
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                        return (String) message.get("content");
                    }
                    return "Nenhuma resposta foi gerada.";
                });
    }
}
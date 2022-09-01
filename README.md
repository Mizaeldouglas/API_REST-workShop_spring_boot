# Projeto MongoDB com Spring Boot


![mongo-spring](https://user-images.githubusercontent.com/89351018/187951361-7c34744f-ae0b-4c1e-bff8-ad9c0c9f525c.png)




## Objetivo geral:
- Compreender as principais diferenças entre paradigma orientado a documentos e relacional
- Implementar operações de CRUD
- Refletir sobre decisões de design para um banco de dados orientado a documentos
- Implementar associações entre objetos
o Objetos aninhados
o Referências
- Realizar consultas com Spring Data e MongoRepository

# Primeiro commit - projeto criado

## Checklist:
- File -> New -> Spring Starter Project
o Escolher somente o pacote Web por enquanto
- Rodar o projeto e testar: http://localhost:8080
- Se quiser mudar a porta padrão do projeto, incluir em application.properties: server.port=${port:8081}
# Commit: Entity User e REST funcionando
## Checklist para criar entidades:
- Atributos básicos
- Associações (inicie as coleções)
- Construtores (não inclua coleções no construtor com parâmetros)
- Getters e setters
- hashCode e equals (implementação padrão: somente id)
- Serializable (padrão: 1L)
## Checklist:
- No subpacote domain, criar a classe User
- No subpacote resources, criar uma classe UserResource e implementar nela o endpoint GET padrão

# Commit: Conectando ao MongoDB com repository e service
## Checklist:
- Em pom.xml, incluir a dependência do MongoDB:

- No pacote repository, criar a interface UserRepository
- No pacote services, criar a classe UserService com um método findAll
- Em User, incluir a anotação @Document e @Id para indicar que se trata de uma coleção do MongoDB
- Em UserResource, refatorar o código, usando o UserService para buscar os usuários
- Em application.properties, incluir os dados de conexão com a base de dados:
spring.data.mongodb.uri=mongodb://localhost:27017/workshop_mongo
- Subir o MongoDB (comando mongod)
- Usando o MongoDB Compass:
  - o Criar base de dados: workshop_mongo
  - o Criar coleção: user
  - o Criar alguns documentos user manualmente
- Testar o endpoint /users
# Commit: Operação de instanciação da base de dados

## Checklist:
- No subpacote config, criar uma classe de configuração Instantiation que implemente CommandlLineRunner
- Dados para copiar:
- User maria = new User(null, "Maria Brown", "maria@gmail.com");
- User alex = new User(null, "Alex Green", "alex@gmail.com");
- User bob = new User(null, "Bob Grey", "bob@gmail.com");
# Commit: Usando padrão DTO para retornar usuários
- Referências:
https://pt.stackoverflow.com/questions/31362/o-que-é-um-dto
DTO (Data Transfer Object): é um objeto que tem o papel de carregar dados das entidades de forma simples,
podendo inclusive "projetar" apenas alguns dados da entidade original. Vantagens:
- Otimizar o tráfego (trafegando menos dados)
- Evitar que dados de interesse exclusivo do sistema fiquem sendo expostos (por exemplo: senhas, dados de
auditoria como data de criação e data de atualização do objeto, etc.)
- Customizar os objetos trafegados conforme a necessidade de cada requisição (por exemplo: para alterar
um produto, você precisa dos dados A, B e C; já para listar os produtos, eu preciso dos dados A, B e a
categoria de cada produto, etc.).
# Checklist:
- No subpacote dto, criar UserDTO
- Em UserResource, refatorar o método findAll
# Commit: Obtendo um usuário por id
## Checklist:
- No subpacote service.exception, criar ObjectNotFoundException
- Em UserService, implementar o método findById
- Em UserResource, implementar o método findById (retornar DTO)
- No subpacote resources.exception, criar as classes:
o StandardError
o ResourceExceptionHandler
# Commit: Inserção de usuário com POST
## Checklist:
- Em UserService, implementar os métodos insert e fromDTO
- Em UserResource, implementar o método insert
# Commit: Deleção de usuário com DELETE
## Checklist:
- Em UserService, implementar os métodos update e updateData
- Em UserResource, implementar o método update
Criando entity Post com User aninhado
Nota: objetos aninhados vs. referências
Checklist:
- Criar classe Post
- Criar PostRepository
- Inserir alguns posts na carga inicial da base de dados
# Commit: Projeção dos dados do autor com DTO
## Checklist:
- Criar AuthorDTO
- Refatorar Post
- Refatorar a carga inicial do banco de dados
o IMPORTANTE: agora é preciso persistir os objetos User antes de relacionar
# Commit: Referenciando os posts do usuário
## Checklist:
- Em User, criar o atributo "posts", usando @DBRef
o Sugestão: incluir o parâmetro (lazy = true)
- Refatorar a carga inicial do banco, incluindo as associações dos posts
# Commit: Endpoint para retornar os posts de um usuário
## Checklist:
- Em UserResource, criar o método para retornar os posts de um dado usuário
# Commit: Obtendo um post por id
## Checklist:
- Criar PostService com o método findById
- Criar PostResource com método findById
# Commit: Acrescentando comentários aos posts
## Checklist:
- Criar CommentDTO
- Em Post, incluir uma lista de CommentDTO
- Refatorar a carga inicial do banco de dados, incluindo alguns comentários nos posts
# Commit: Consulta simples com query methods
- Referências:
https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
https://docs.spring.io/spring-data/data-document/docs/current/reference/html/
-Consulta:
"Buscar posts contendo um dado string no título"
## Checklist:
- Em PostRepository, criar o método de busca
- Em PostService, criar o método de busca
- No subpacote resources.util, criar classe utilitária URL com um método para decodificar parâmetro de URL
- Em PostResource, implementar o endpoint
# Commit: Consulta simples com @Query
- Referências:
  - https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
  - https://docs.spring.io/spring-data/data-document/docs/current/reference/html/
  - https://docs.mongodb.com/manual/reference/operator/query/regex/

- Consulta:
  - "Buscar posts contendo um dado string no título"
## Checklist:
- Em PostRepository, fazer a implementação alternativa da consulta
- Em PostService, atualizar a chamada da consulta
# Commit: Consulta com vários critérios
### Consulta:
- "Buscar posts contendo um dado string em qualquer lugar (no título, corpo ou comentários) e em um dado
intervalo de datas"
## Checklist:
- Em PostRepository, criar o método de consulta
- Em PostService, criar o método de consulta
- Na classe utilitária URL, criar métodos para tratar datas recebidas
- Em PostResource, implementar o endpoint

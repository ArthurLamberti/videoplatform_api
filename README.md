<h1 align="center">Api da plataforma de video</h1>

<p align="center">API criada durante o módulo 'Projeto Prático' do curso Full Cycle 3.0</p>

<p align="center">
 <a href="#objetivo">Objetivo</a> •
 <a href="#features">Features</a> •
 <a href="#tecnologias">Tecnologias</a> • 
 <a href="#padroes-de-codigo">Padrões de código</a> • 
 <a href="#como-rodar">Como rodar</a> • 
 <a href="#autor">Autor</a>
</p>

<h4 align="center"> 
	🚧  Em andamento...  🚧
</h4>

## Objetivo

<p>O principal objetivo desse projeto é de aplicar os conhecimentos adquiridos e relembrados durante o modulo de Projeto Prático do curso Full Cycle. </p>
<p>Essa Api desenvolvida tem o objetivo de gerir uma plataforma de filmes, onde teremos categorias, generos e o elenco da produção </p>
<p>Para o acesso à API, o administrador do catálogo de vídeo deverá realizar o login (utilizamos keycloak para isso), e após o login, poderá realizar as ações de admin, como fazer o upload dos videos, alterar descrição...</p>

## Features
  - [x] Categoria (entidade, usecases, infra e testes)
  - [x] Genre (entidade, usecases, infra e testes)
  - [x] Cast Member (entidade, usecases, infra e testes)
  - [x] Video (entidade, usecases, infra e testes)
  - [x] Upload do video pro Google Cloud Storage
  - [x] Domain Events (rabbitmq)
  - [x] CI/CD
  - [ ] Autenticação com keyCloak
  - [ ] Ambiente Sandbox
  - [ ] Observabilidade

## Tecnologias
  - Gradle
  - Java 17
  - flyway
  - mySQL
  - hibernate
  - Rabbitmq
  - GitHub CI/CD
  - Docker
  - Google Cloud Storage
  - KeyCloak
  - Elastic Stack
  - Kibana

## Padrões de código

<p>Para o desenvolvimento dessa API, utilizamos o padrão DDD, separando o código nas camadas de application, domain e infrastructure</p>
<h3>Application</h3>
<p>Camada referente à comunicação com a camada de Domain através da implementação dos casos de uso</p>
<p>Possui a implementação dos casos de usos necessário para o funcionamento</p>

<h3>Domain</h3>
<p>Define os objetos de valores e agregados, juntamente com as validações coerentes à entidade</p>
<p>Contém os casos de uso e a lógica principal do negócio</p>

<h3>Infrastructure</h3>
<p>Fornece implementações para as interfaces definidas na camada de domain</p>
<p>Realiza a comunicação entre outros serviços e banco de dados</p>


## Como rodar

## Autor

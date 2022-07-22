### Desafio Técnico Agrotis

O desafio possui uma api documentada usando swagger v3, após o start da aplicação e possivel acessa-lo pelo endereço local
<http://localhost:8080/swagger-ui/index.html>


### Tecnologias utilizadas

* Java 17 LTS
* Gradle 7.4.1
* Spring Boot 2.7.1
* Lombok
* Swagger v3
* PostgreSQL 12
* JUnit 5
* Mockito
* Rest Assured
* Docker
* Docker Compose

### Modos de execução

#### 1 - Para executar projeto local é necessário possuir o docker é docker-compose instalado.

Passo 1:

```
Para clonar o repósitorio utilize o seguinte comando "git clone git@github.com:alanjholiveira/desafio-agrotis.git" ou "git clone https://github.com/alanjholiveira/desafio-agrotis.git"
Para download do projeto sem precisar clonar acesse o link "https://github.com/alanjholiveira/desafio-agrotis/archive/refs/heads/main.zip"
```

Passo 2:

```
docker-compose up -d --build
```

Passo 3:

```
Acesse a url abaixo para ter acesso a documentação da api
<http://localhost:8080/swagger-ui/index.html>
```

### Rodar os testes da aplicação

#### 1 - Para executar projeto local é necessário possuir o docker é docker-compose instalado.

Passo 1:

```
cd api
./gradlew test #Unix
ou
gradlew.bat test #Windows
```

### Como utilizar a API

1. Na tabela pessoa e conta, foram cadastrados dados inicial que podem ser utilizados para testes
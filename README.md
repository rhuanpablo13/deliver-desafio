<h1 align="center">

👾 DESAFIO DELIVER 👾

</h1>
<p align="center">🚀  Sistema de cadastro de contas a pagar 
</p>

### Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com/), 
[Maven 3.6.3](http://charlesmms.azurewebsites.net/2017/09/04/instalando-maven-no-windows-10/), 
[Java 11](https://www.ic.unicamp.br/~ra100621/class/2020.1/LPOO_files/curso/prologo/00-instalacao/windows/00-tuto_instal_windows.html),
[VSCode](https://code.visualstudio.com/) .
[Docker](https://www.docker.com/) .

### Instale as seguintes extensões do vscode
    👉 Language Support for Java(TM) by Red Hat
    👉 Extension Pack for Java
    👉 Spring Initializr Java Support

### Se preferir pode seguir o seguinte tutorial para configurar o vscode
(https://medium.com/@vinicius.b.martinez/microsoft-vscode-para-desenvolvedores-java-f1e9f69e6fa6)


### 🎲 Rodando a aplicação

# Clone este repositório
```
git https://github.com/rhuanpablo13/deliver-desafio
```

## Abra o projeto no vscode
#### 💠Execute o maven update, clicando com o botão direito do mouse no arquivo pom.xml e selecionando a opção "Update Project"
#### 💠Abra o item [MAVEN] no painel esquerdo e clique com o botão direito sobre o projeto [deliver] e selecione a opção "install"
![alt text](/resources-readme/maven.jpg) .

## Configurando o application.properties 📂

#### 💠Configure a conexão com o banco de dados conforme sua necessidade e perfil de projeto (prod, default)

## Iniciando o projeto 🚀

#### 💠Inicie o projeto na aba [SPRING BOOT DASHBOARD]
![alt text](/resources-readme/spring-start.jpg)

#### 💠O servidor inciará na porta:8080 - acesse <http://localhost:8080/api/swagger-ui.html>


## Iniciando o projeto com os containers 🚀
#### 💠Execute o comando a seguir na pasta raiz do projeto
```
docker-compose up
```
#### 💠O servidor inciará na porta:8080 - acesse <http://localhost:8080/api/swagger-ui.html>


### 🛠 Tecnologias

#### As seguintes ferramentas foram usadas na construção do projeto:

- [Java](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html)
- [Maven](https://maven.apache.org/docs/3.6.3/release-notes.html) 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Docker](https://www.docker.com/)
- [Mysql](https://hub.docker.com/_/mysql)

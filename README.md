# Treinamento Spring Boot

Treinamento básico de Spring Boot para desenvolvimento de uma API Rest em Java.

Esse treinamento foi desenvolvimento por mim, [Felipe Assoline](http://about.me/fassoline), com o objetivo de ajudar os alunos da PUC-Campinas que vão remodelar os sistemas da [Semana Integrada do CEATEC/PUC-Campinas](http://semanaintegrada.com.br/).

# O que o projeto faz?

* Serialização de Objetos Java em JSON
* Autenticação Http Basic
* Acesso a base de dados usando ORM (JPA/Spring Data)
* URLs no padrão REST com métodos GET, POST, DELETE e PUT
* Tests Automatizados (JUnit/Spring Test/RestAssured)
* Aplicação leve e self hosted, podendo ser executada sem a necessidade de um tomcat ou outro application server/servlet container.

Make jar not war :)

# Requisitos

Espera-se que você tenha um conhecimento básico de Java, Orientação a Objetos e SQL para esse treinamento, esse projeto base foi desenvolvimento em um OSX El Capitan, mas você pode executa-lo em qualquer ambiente, seja ~nix ou Windows, considerando que os softwares abaixo estejam instalados:

* Java 8+ [Download]()
* Maven 3+ [Download](https://maven.apache.org/download.cgi)
* Eclipse ou qualquer IDE com suporte ao Maven 
	* Eclipse [Download](http://www.eclipse.org/)
	* IntelliJ [Download](https://www.jetbrains.com/idea/)
	* Netbeans [Download](https://netbeans.org/) 


# Executando o projeto	

Na pasta principal do projeto execute o comando abaixo:

    mvn spring-boot:run

ou

    mvn clean package -DskipTests
    java -jar target/*.jar

ou via IDE.

# Executando testes

Na pasta principal do projeto execute o comando abaixo:

    mvn test

ou via IDE.

# Ao infinito e além

Depois desse treinamento, você será capaz de desenvolver uma API básica se utilizando do framework Spring/Spring Boot, mas você ainda precisa ir atrás de mais conhecimento para realmente colocar uma aplicação em produção, abaixo alguns links úteis com mais conteúdos:


* [Spring Boot](http://projects.spring.io/spring-boot/): Página de referência do projeto Spring Boot
* [Spring Initializer](https://start.spring.io/): Para criar o projeto com as dependencias do Maven já configuradadas
* [Heroku](https://www.heroku.com/home) Plataforma cloud para executar aplicações Spring Boot em containers Docker (você pode ter servidores ilimitados com uptime de 18horas dia Free).






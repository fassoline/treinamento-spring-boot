package com.felipeassoline.treinamento;

import static com.jayway.restassured.RestAssured.with;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest(value = "server.port:0")
public class ApplicationTests {

	@Autowired
	ContatoRepository contatoRepository;

	@Value("${local.server.port}")
	int port;

	Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

	@Before
	public void setup() {

		// deleta todos os contatos
		contatoRepository.deleteAll();

		// configuracoes do RestAssured
		RestAssured.port = port;
		RestAssured.requestSpecification = new RequestSpecBuilder().build().contentType("application/json;charset=utf-8");
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testDeveCriarUmContato() {

		String nome = "Felipe";
		String email = "fassoline@gmail.com";

		// cria o novo contato
		Contato contato = new Contato(nome, email);
		with().body(contato).when().post("/contatos").then().statusCode(200);

		// verifica se o contato existe
		assertNotNull(contatoRepository.findByEmail(email));

	}

	@Test
	public void testDeveAtualizarONomeDoContato() {

		String nome = "Rafael";
		String novoNome = "Rafael Pucc";
		String email = "gomes.rafaelcosta@gmail.com";

		// cria o contato
		Contato contato = new Contato(nome, email);
		contatoRepository.save(contato);

		// ainda tem que ser igual o nome e dif de novoNome
		assertNotEquals(contato.getNome(), novoNome);

		// agora chama o servico de atualizar
		contato.setNome(novoNome);
		with().body(contato).when().put("/contatos").then().statusCode(200);

		// obtem o rafael atualizado
		Contato contatoAtualizado = contatoRepository.findByEmail(email);

		// verifica se o nome esta OK
		assertEquals(contatoAtualizado.getNome(), novoNome);

	}

}

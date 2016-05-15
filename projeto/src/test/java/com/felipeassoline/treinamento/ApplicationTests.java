package com.felipeassoline.treinamento;

import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.RestAssured.with;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static com.jayway.restassured.RestAssured.basic;

import org.hamcrest.Matchers;
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
		RestAssured.authentication = basic("admin", "qwe123");
		RestAssured.port = port;
		RestAssured.requestSpecification = new RequestSpecBuilder().build().contentType("application/json;charset=utf-8");
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

	@Test
	public void testDeveExcluirOContato() {

		String nome = "Sprint Boot";
		String email = "spring@example.com";

		// cria o contato
		Contato contato = new Contato(nome, email);
		contatoRepository.save(contato);

		// verifica se critou
		assertNotNull(contato.getId());
		assertNotNull(contatoRepository.findOne(contato.getId()));

		when() //
				.delete(String.format("/contatos/%s", contato.getId())) //
				.then() //
				.statusCode(200);

		// tenta obter o contato
		// para vetificar se deletou
		assertNull(contatoRepository.findOne(contato.getId()));

	}

	@Test
	public void testDeveRetornar404AoExcluirUmContatoQueNaoExiste() {

		when() //
				.delete("/contatos/-1") //
				.then() //
				.statusCode(404);

	}

	@Test
	public void testDeveRetornarUmContatoEVerificarONome() {

		String nome = "Sprint Boot";
		String email = "spring@example.com";

		// cria o contato
		Contato contato = new Contato(nome, email);
		contatoRepository.save(contato);

		// verifica se critou
		assertNotNull(contato.getId());

		when() //
				.get(String.format("/contatos/%s", contato.getId())) //
				.then() //
				.statusCode(200) //
				.body("nome", Matchers.equalTo(nome));

	}

	@Test
	public void testDeveRetornar404AoObterUmContatoQueNaoExiste() {

		when() //
				.get("/contatos/-1") //
				.then() //
				.statusCode(404);

	}

	@Test
	public void testDeveObterTodosOsContatos() {

		Contato contato1 = contatoRepository.save(new Contato("Contato 1", "contato1@example.com"));
		Contato contato2 = contatoRepository.save(new Contato("Contato 2", "contato2@example.com"));

		when() //
				.get("/contatos") //
				.then() //
				.statusCode(200) //
				.body("nome", Matchers.hasItems(contato1.getNome(), contato2.getNome()));

	}

	@Test
	public void testDeveFiltrarContatosPorNome() {

		Contato contato1 = contatoRepository.save(new Contato("Spring Boot", "contato1@example.com"));
		Contato contato2 = contatoRepository.save(new Contato("Spring Framework", "contato2@example.com"));
		Contato contato3 = contatoRepository.save(new Contato("Java Enterprise", "contato3@example.com"));

		when() //
				.get(String.format("/contatos/por-nome?nome=%s", "Spring")) //
				.then() //
				.statusCode(200) //
				.body("nome", Matchers.hasItems(contato1.getNome(), contato2.getNome()))
				.body("nome", Matchers.not(Matchers.hasItems(contato3.getNome())));

	}

}

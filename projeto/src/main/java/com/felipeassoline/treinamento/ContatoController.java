package com.felipeassoline.treinamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

	@Autowired
	private ContatoRepository contatoRepository;

	@RequestMapping(method = RequestMethod.POST)
	public Contato create(@RequestBody Contato contato) {
		Assert.isNull(contato.getId());
		return contatoRepository.save(contato);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Contato update(@RequestBody Contato contato) {
		Assert.notNull(contato.getId());
		return contatoRepository.save(contato);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Contato delete(@PathVariable Integer id) {
		Contato contato = contatoRepository.findOne(id);
		contatoRepository.delete(contato);
		return contato;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Contato> getAll() {
		return contatoRepository.findAll();
	}

	@RequestMapping(value = "/{nome}", method = RequestMethod.GET)
	public List<Contato> getByNome(@PathVariable String nome) {
		return contatoRepository.findByNome(nome);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Contato getOne(@PathVariable Integer id) {
		return contatoRepository.findOne(id);
	}

}

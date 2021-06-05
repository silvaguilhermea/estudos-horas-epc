package com.silvaguilherme.estudoshorasepc.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.silvaguilherme.estudoshorasepc.entities.Projeto;
import com.silvaguilherme.estudoshorasepc.services.ProjetoService;

@RestController
@RequestMapping(value = "/projetos")
public class ProjetoResource {
/*	
	private List<Projeto> list;

	@Autowired
	private ProjetoRepository projetoRepository;
	
	@GetMapping
	public ResponseEntity<List<Projeto>> findAll() {
		List<Projeto> list = projetoRepository.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Projeto> findById(@PathVariable Long id) {
		Projeto obj = projetoRepository.findById(id).get();
		return ResponseEntity.ok().body(obj);
	}
*/
	@Autowired /* instância objetos automaticamente */
	public ProjetoService service;

	/* Método Inserir */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Projeto obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/* Método Atualizar */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Projeto obj,@PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	/* Método Deletar */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		 service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/* método GET para tratar as requisições  */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Projeto obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	/* FAZER COM O DTO */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Projeto>> buscarTudo() {
		List<Projeto> obj = service.buscarTudo();
		return ResponseEntity.ok().body(obj);
	}
}

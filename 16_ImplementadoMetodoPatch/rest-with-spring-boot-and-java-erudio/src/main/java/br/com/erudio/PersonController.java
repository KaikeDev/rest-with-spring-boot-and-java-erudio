package br.com.erudio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

//@CrossOrigin
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing people") // Atualiza o o nome lá no navegador swagger
public class PersonController {

	@Autowired
	private PersonServices service;

	@GetMapping(produces = { br.com.erudio.util.MediaType.APPLICATION_JSON,
			br.com.erudio.util.MediaType.APPLICATION_XML, br.com.erudio.util.MediaType.APPLICATION_YML })

	// Adicionando a documentação das operações no swagger no navegador
	@Operation(summary = "Finds All People", description = "Finds All People", tags = { "People" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))) }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public List<PersonVO> findAll() {
		return service.findAll();
	}

	@CrossOrigin(origins = "http://localhost:8080") // permite acesso apenar ao localhost
	@GetMapping(value = "/{id}", produces = { br.com.erudio.util.MediaType.APPLICATION_JSON,
			br.com.erudio.util.MediaType.APPLICATION_XML, br.com.erudio.util.MediaType.APPLICATION_YML })

	@Operation(summary = "Finds a Person", description = "Finds a Person", tags = { "People" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),

			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })

	public PersonVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}

	@CrossOrigin(origins = { "http://localhost:8080", "http://erudio.com.br" })
	@PostMapping(consumes = { br.com.erudio.util.MediaType.APPLICATION_JSON,
			br.com.erudio.util.MediaType.APPLICATION_XML, br.com.erudio.util.MediaType.APPLICATION_YML }, produces = {
					br.com.erudio.util.MediaType.APPLICATION_JSON, br.com.erudio.util.MediaType.APPLICATION_XML,
					br.com.erudio.util.MediaType.APPLICATION_YML })

	@Operation(summary = "Adds a new Person", description = "Adds a new Person passing XML/JSON", tags = {
			"People" }, responses = {
					@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),

					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public PersonVO create(@RequestBody PersonVO person) {
		return service.create(person);
	}

	@PostMapping(value = "/v2", consumes = { br.com.erudio.util.MediaType.APPLICATION_JSON,
			br.com.erudio.util.MediaType.APPLICATION_XML, br.com.erudio.util.MediaType.APPLICATION_YML }, produces = {
					br.com.erudio.util.MediaType.APPLICATION_JSON, br.com.erudio.util.MediaType.APPLICATION_XML })
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
		return service.createV2(person);
	}

	@PutMapping(consumes = { br.com.erudio.util.MediaType.APPLICATION_JSON,
			br.com.erudio.util.MediaType.APPLICATION_XML, br.com.erudio.util.MediaType.APPLICATION_YML }, produces = {
					br.com.erudio.util.MediaType.APPLICATION_JSON, br.com.erudio.util.MediaType.APPLICATION_XML,
					br.com.erudio.util.MediaType.APPLICATION_YML })

	@Operation(summary = "Updates a Person", description = "Updates a Person", tags = { "People" }, responses = {
			@ApiResponse(description = "Updats", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public PersonVO update(@RequestBody PersonVO person) {
		return service.update(person);
	}

	@CrossOrigin(origins = "http://localhost:8080") // permite acesso apenar ao localhost
	@PatchMapping(value = "/{id}", produces = { br.com.erudio.util.MediaType.APPLICATION_JSON,
			br.com.erudio.util.MediaType.APPLICATION_XML, br.com.erudio.util.MediaType.APPLICATION_YML })

	@Operation(summary = "Disable a specific Person by your id", description = "Disable a specific Person by your id", tags = { "People" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),

			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })

	public PersonVO disablePerson(@PathVariable(value = "id") Long id) {
		return service.disablePerson(id);
	}

	@DeleteMapping(value = "/{id}")

	@Operation(summary = "Delets a Person", description = "Delets a Person", tags = { "People" }, responses = {
			@ApiResponse(description = "No content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
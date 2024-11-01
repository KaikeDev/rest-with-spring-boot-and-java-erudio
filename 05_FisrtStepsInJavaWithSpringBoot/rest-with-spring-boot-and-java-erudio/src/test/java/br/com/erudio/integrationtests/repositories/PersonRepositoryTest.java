/*

package br.com.erudio.integrationtests.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.erudio.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepositories;

@ExtendWith(SpringExtension.class)
@DataJpaTest // Para testar repositorios
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // FAZ COM QUE NÃO ALTERE O BANCO DE DADOS
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class PersonRepositoryTest extends AbstractIntegrationTest {

	@Autowired
	PersonRepositories personRepositories;

	private static Person person;

	@BeforeAll
	private static void setup() {

		person = new Person();
		
	}
		
		@Test
		@Order(1)
		public void testFindByName() throws JsonMappingException, JsonProcessingException {
			
			Pageable pageable = PageRequest.of(0, 6, Sort.by(Direction.ASC, "firstName"));
			person = personRepositories.findPersonByName("ayr", pageable).getContent().get(0);
			
			assertNotNull(person.getId());
			assertNotNull(person.getFirstName());
			assertNotNull(person.getLastName());
			assertNotNull(person.getAddress());
			assertNotNull(person.getGender());

			assertTrue(person.getEnabled());
			
			assertEquals(1, person.getId());
			
			assertEquals("Ayrton", person.getFirstName());
			assertEquals("Senna", person.getLastName());
			assertEquals("São Paulo", person.getAddress());
			assertEquals("Male", person.getGender());
		}
		
		@Test
		@Order(2)
		public void testDisablePerson() throws JsonMappingException, JsonProcessingException {
			
			personRepositories.disablePerson(person.getId());
			
			Pageable pageable = PageRequest.of(0, 6, Sort.by(Direction.ASC, "firstName"));
			person = personRepositories.findPersonByName("ayr", pageable).getContent().get(0);
			
			assertNotNull(person.getId());
			assertNotNull(person.getFirstName());
			assertNotNull(person.getLastName());
			assertNotNull(person.getAddress());
			assertNotNull(person.getGender());
			
			assertFalse(person.getEnabled());
			
			assertEquals(1, person.getId());
			
			assertEquals("Ayrton", person.getFirstName());
			assertEquals("Senna", person.getLastName());
			assertEquals("São Paulo", person.getAddress());
			assertEquals("Male", person.getGender());
		}


}


*/

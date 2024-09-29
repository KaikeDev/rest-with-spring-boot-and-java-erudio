package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepositories;
import br.com.erudio.services.BookServices;
import br.com.erudio.unittests.mapper.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	MockBook input;

	@InjectMocks
	private BookServices service;

	@Mock
	BookRepositories repositories;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Book> list = input.mockEntityList();

		when(repositories.findAll()).thenReturn((list));

		var book = service.findAll();
		assertNotNull(book);
		assertEquals(14, book.size());

		var result = book.get(1);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(25D, result.getPrice());
		assertEquals("Some Title1", result.getTitle());

		var result2 = book.get(4);

		assertNotNull(result2);
		assertNotNull(result2.getKey());
		assertNotNull(result2.getLinks());
		assertTrue(result2.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));
		assertEquals("Some Author4", result2.getAuthor());
		assertNotNull(result2.getLaunchDate());
		assertEquals(25D, result2.getPrice());
		assertEquals("Some Title4", result2.getTitle());

		var result3 = book.get(7);

		assertNotNull(result3);
		assertNotNull(result3.getKey());
		assertNotNull(result3.getLinks());
		assertTrue(result3.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
		assertEquals("Some Author7", result3.getAuthor());
		assertNotNull(result3.getLaunchDate());
		assertEquals(25D, result3.getPrice());
		assertEquals("Some Title7", result3.getTitle());
	}

	@Test
	void testFindById() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);

		when(repositories.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(25D, result.getPrice());
		assertEquals("Some Title1", result.getTitle());
	}

	@Test
	void testCreate() {
		Book entity = input.mockEntity(1);

		Book persisted = entity;
		persisted.setId(1L);

		BookVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repositories.save(entity)).thenReturn(persisted);

		var result = service.create(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(25D, result.getPrice());
		assertEquals("Some Title1", result.getTitle());
	}

	@Test
	void testCreateWithNullBook() {

		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});

		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testUpdate() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);

		Book persisted = entity;
		persisted.setId(1L);

		BookVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repositories.findById(1L)).thenReturn(Optional.of(entity));
		when(repositories.save(entity)).thenReturn(persisted);

		var result = service.update(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(25D, result.getPrice());
		assertEquals("Some Title1", result.getTitle());
	}

	@Test
	void testUpdateWithNullBook() {

		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});

		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}

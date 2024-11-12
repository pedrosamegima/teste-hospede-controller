package com.teste.hospede.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.teste.hospede.entity.Quarto;

@DataJpaTest
class QuartoRepositoryTest {

	@Autowired
	private QuartoRepository quartoRepository;

	@DisplayName("Testando o save")
	@Test
	void testSalvarRepository() {
		// Given / Arrange
		Quarto quarto1 = new Quarto(null, "214",
				"suite");

		//When / Act
		Quarto saveQuarto = quartoRepository.save(quarto1);

		//Then / Assert
		assertNotNull(saveQuarto);
		assertTrue(saveQuarto.getId() > 0);
	}
	@DisplayName("Testando get para todos quartos")
	@Test
	void testGetAllRepository() {
		//Given / Arrange
		Quarto quarto1 = new Quarto(null, "214",
				"suite");

		Quarto quarto2 = new Quarto(null, "255",
				"suite master");

		quartoRepository.save(quarto1);
		quartoRepository.save(quarto2);

		// When / Act
		List<Quarto> quartoList = quartoRepository.findAll();

		//Then / Assert 
		assertNotNull(quartoList);
		assertEquals(2, quartoList.size());
	}
	@DisplayName("Testando GET by ID")
	@Test
	void testGetById() {
		//Given / Arrange
		Quarto quarto1 = new Quarto(null, "214",
				"suite master");
		quartoRepository.save(quarto1);

		//When / Act
		Quarto saveQuarto = quartoRepository.findById(quarto1.getId()).get();

		//Then / Assert 

		assertNotNull(saveQuarto);
		assertEquals(quarto1.getId(), saveQuarto.getId());
	}
	@DisplayName("Testando o update")
	@Test
	void testUpdateHospede() {
		//Given / Arrange
		Quarto quarto1 = new Quarto(null, "14",
				"suite master");
		quartoRepository.save(quarto1);

		//When / Act
		Quarto saveQuarto = quartoRepository.findById(quarto1.getId()).get();
		quarto1.setNum("14");
		quarto1.setTipo("suite");

		Quarto updateQuarto = quartoRepository.save(saveQuarto);

		//Then / Assert
		assertNotNull(updateQuarto);
		assertEquals("14", updateQuarto.getNum());
		assertEquals("suite", updateQuarto.getTipo());
	}
	@DisplayName("testando o delete")
	@Test
	void testeDeleteQuarto() {

		//Given / Arrange
		Quarto quarto1 = new Quarto(null, "14",
				"suite");

		quartoRepository.save(quarto1);

		//When / Act
		quartoRepository.deleteById(quarto1.getId());
		Optional<Quarto> quartoOptional = quartoRepository.findById(quarto1.getId());

		//then / Assert
		assertTrue(quartoOptional.isEmpty());
	}
}
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

import com.teste.hospede.entity.Veiculo;



@DataJpaTest
class VeiculoRepositoryTest {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@DisplayName("Testando o Save")
	@Test
	void test() {

		// Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "Toyota",
				"Corolla gr",
				2020,
				"Preto");

		// When / Act 
		Veiculo saveVeiculo = veiculoRepository.save(veiculo1);

		//Then / Assert
		assertNotNull(saveVeiculo);
		assertTrue(saveVeiculo.getId() > 0);
	}

	@DisplayName("Testando Get para todos os Veiculos")
	@Test
	void testGetAllRepository() {
		//given / Arrange 
		Veiculo Veiculo1 = new Veiculo (null, "Toyota",
				"Corolla gr",
				2020,
				"Preto");
		Veiculo Veiculo2 = new Veiculo (null, "Nissan",
				"350Z",
				2005,
				"Cinza");
		veiculoRepository.save(Veiculo1);
		veiculoRepository.save(Veiculo2);

		//when / Act 
		List<Veiculo> veiculoList =  veiculoRepository.findAll();

		//then / Assert
		assertNotNull(veiculoList);
		assertEquals(2, veiculoList.size());
	}
	@DisplayName("Testando Get By ID")
	@Test
	void testGetById() {

		//given / Arrange 
		Veiculo veiculo1 = new Veiculo (null,  "Toyota",
				"Corolla gr",
				2020,
				"Preto");
		veiculoRepository.save(veiculo1);

		//when / Act 
		Veiculo saveVeiculo =  veiculoRepository.findById(veiculo1.getId()).get();


		//then / Assert
		assertNotNull(saveVeiculo);
		assertEquals(veiculo1.getId(), saveVeiculo.getId());

	}
	@DisplayName("Testando o Update")
	@Test
	void testUpdateVeiculo() {
		//given / Arrange 
		Veiculo veiculo1 = new Veiculo (null,  "Toyota",
				"Corolla gr",
				2020,
				"Preto");
		veiculoRepository.save(veiculo1);
		//when / Act 
		Veiculo saveVeiculo =  veiculoRepository.findById(veiculo1.getId()).get();
		veiculo1.setMarca("Ford");
		veiculo1.setModelo("maverick");
		veiculo1.setAno(1998);
		veiculo1.setCor("Preto");


		Veiculo updateVeiculo = veiculoRepository.save(saveVeiculo);

		//then / Assert
		assertNotNull(updateVeiculo);
		assertEquals("Ford", updateVeiculo.getMarca());
		assertEquals("maverick", updateVeiculo.getModelo());
		assertEquals(1998, updateVeiculo.getAno());
		assertEquals("Preto", updateVeiculo.getCor());

	}
	@DisplayName("Testando o Delete")
	@Test
	void testDeleteVeiculo() {


		//given / Arrange 
		Veiculo Veiculo1 = new Veiculo (null, "Toyota",
				"Corolla gr",
				2020,
				"Preto");
		veiculoRepository.save(Veiculo1);
		//when / Act 
		veiculoRepository.deleteById(Veiculo1.getId());

		Optional<Veiculo> veiculoOptional = veiculoRepository.findById(Veiculo1.getId());

		//then / assert

		assertTrue(veiculoOptional.isEmpty());
	}

}
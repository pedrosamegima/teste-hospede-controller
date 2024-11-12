package com.teste.hospede.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.hospede.entity.Veiculo;

public interface VeiculoRepository extends JpaRepository <Veiculo, Long> {

}

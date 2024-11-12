package com.teste.hospede.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.hospede.entity.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {

}

package com.teste.hospede.controller;

import com.teste.hospede.entity.Hospede;
import com.teste.hospede.service.HospedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hospedes")
public class HospedeController {

    @Autowired
    private HospedeService hospedeService;

    @PostMapping
    public ResponseEntity<Hospede> salvarHospede(@RequestBody Hospede hospede) {
        Hospede novoHospede = hospedeService.salvarHospede(hospede);
        return new ResponseEntity<>(novoHospede, HttpStatus.CREATED);
    }

  
    @GetMapping
    public ResponseEntity<List<Hospede>> listarTodos() {
        List<Hospede> hospedes = hospedeService.listarTodos();
        return new ResponseEntity<>(hospedes, HttpStatus.OK);
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<Hospede> buscarPorId(@PathVariable Long id) {
        Optional<Hospede> hospede = hospedeService.buscarPorId(id);
        return hospede.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<Hospede> atualizarHospede(@PathVariable Long id, 
    											@RequestBody Hospede hospede) {
        if (!hospedeService.buscarPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hospede.setId(id);
        Hospede hospedeAtualizado = hospedeService.atualizarHospede(hospede);
        return new ResponseEntity<>(hospedeAtualizado, HttpStatus.OK);
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarHospede(@PathVariable Long id) {
        if (!hospedeService.buscarPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hospedeService.deletarHospede(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

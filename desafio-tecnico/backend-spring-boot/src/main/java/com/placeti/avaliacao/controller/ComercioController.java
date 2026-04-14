package com.placeti.avaliacao.controller;

import com.placeti.avaliacao.dto.ComercioDTO;
import com.placeti.avaliacao.dto.ComercioDTOResponse;
import com.placeti.avaliacao.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//--------------------------------------------------
/** Endpoint para consultar e manter comércios */
//--------------------------------------------------
@RestController
@RequestMapping("/comercios")
public class ComercioController {

    private final ProjetoService projetoService;

    public ComercioController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    //----------------------------------------------------------
    /** Endpoint que retorna um comércio conforme seu ID */
    //----------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<ComercioDTO> buscaPeloId(@PathVariable Long id) {
        // TODO: Responde GET em http://localhost:8080/placeti/comercios/1
        return projetoService.pesquisarComercio(id);
    }

    //----------------------------------------------------------
    /** Endpoint que retorna todos os comércios cadastrados */
    //----------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<ComercioDTO>> pesquisarComercios() {
        // TODO: Responde GET em http://localhost:8080/placeti/comercios
        return projetoService.pesquisarComercios();
    }

    //----------------------------------------------------------
    /** Endpoint para incluir novo comércio */
    //----------------------------------------------------------
    @PostMapping
    public ResponseEntity<ComercioDTOResponse> incluirComercio(@Valid @RequestBody ComercioDTO comercioDto) {
        // TODO: Responde POST em http://localhost:8080/placeti/comercios
        return projetoService.incluirComercio(comercioDto);
    }

    //----------------------------------------------------------
    /** Endpoint para alterar comércio */
    //----------------------------------------------------------
    @PutMapping
    public ResponseEntity<ComercioDTOResponse> alterarComercio(@Valid @RequestBody ComercioDTO comercioDto) {
        // TODO: Responde PUT em http://localhost:8080/placeti/comercios
        return projetoService.alterarComercio(comercioDto);
    }

    //----------------------------------------------------------
    /** Endpoint para excluir um comércio */
    //----------------------------------------------------------
    @DeleteMapping("/{idComercio}")
    public ResponseEntity<Void> excluirComercio(@PathVariable Long idComercio) {
        // TODO: Responde DELETE em http://localhost:8080/placeti/comercios/{idComercio}
        return projetoService.excluirComercio(idComercio);
    }
}

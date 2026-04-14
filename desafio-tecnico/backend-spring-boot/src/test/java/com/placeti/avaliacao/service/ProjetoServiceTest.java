package com.placeti.avaliacao.service;

import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.dto.ComercioDTO;
import com.placeti.avaliacao.dto.ComercioDTOResponse;
import com.placeti.avaliacao.dto.TipoComercio;
import com.placeti.avaliacao.mapper.CidadeMapper;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.repository.CidadeRepository;
import com.placeti.avaliacao.repository.ComercioRepository;
import com.placeti.avaliacao.mapper.ComercioMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjetoServiceTest {

    @Mock
    private CidadeRepository cidadeRepository;

    @Mock
    private CidadeMapper cidadeMapper;

    @Mock
    private ComercioRepository comercioRepository;

    @Mock
    private ComercioMapper comercioMapper;

    @InjectMocks
    private ProjetoService projetoService;

    @Test
    @DisplayName("Deve retornar uma cidade quando o ID existir")
    void pesquisarCidade() {
        Long id = 1L;
        Cidade cidade = new Cidade();
        cidade.setId(id);
        cidade.setNome("Florianópolis");

        when(cidadeRepository.findById(id)).thenReturn(Optional.of(cidade));

        ResponseEntity<CidadeDTO> response = projetoService.pesquisarCidade(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Florianópolis", response.getBody().nome());
        verify(cidadeRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve retornar lista de cidades")
    void pesquisarCidades() {
        Cidade c1 = new Cidade();
        c1.setNome("Cidade 1");
        when(cidadeRepository.findAll()).thenReturn(List.of(c1));

        ResponseEntity<List<CidadeDTO>> response = projetoService.pesquisarCidades();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    @DisplayName("Deve incluir uma cidade com sucesso")
    void incluirCidade() {
        CidadeDTO dto = new CidadeDTO(null, "Blumenau", "SC", false);
        Cidade cidade = new Cidade();
        cidade.setNome("Blumenau");

        when(cidadeMapper.dtoToEntity(any())).thenReturn(cidade);
        when(cidadeRepository.save(any())).thenReturn(cidade);
        when(cidadeMapper.entityToDTO(any())).thenReturn(dto);

        ResponseEntity<CidadeDTO> response = projetoService.incluirCidade(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(cidadeRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve alterar cidade existente")
    void alterarCidade() {
        CidadeDTO dto = new CidadeDTO(1L, "Novo Nome", "SC", true);
        Cidade cidade = new Cidade();

        when(cidadeRepository.findById(1L)).thenReturn(Optional.of(cidade));
        when(cidadeMapper.dtoToEntity(dto)).thenReturn(cidade);
        when(cidadeRepository.save(cidade)).thenReturn(cidade);
        when(cidadeMapper.entityToDTO(cidade)).thenReturn(dto);

        ResponseEntity<CidadeDTO> response = projetoService.alterarCidade(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cidadeRepository).save(any());
    }

    @Test
    @DisplayName("Deve excluir cidade com sucesso")
    void excluirCidade() {
        Long id = 1L;
        when(cidadeRepository.findById(id)).thenReturn(Optional.of(new Cidade()));

        ResponseEntity<Void> response = projetoService.excluirCidade(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cidadeRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve retornar um comércio quando o ID existir")
    void pesquisarComercio() {
        Long id = 1L;

        Cidade cidadeMock = new Cidade();
        cidadeMock.setId(10L);
        cidadeMock.setNome("Joinville");

        Comercio comercio = new Comercio();
        comercio.setId(id);
        comercio.setNomeComercio("Loja de Ferramentas");
        comercio.setTipoComercio(TipoComercio.POSTO_GASOLINA);
        comercio.setCidade(cidadeMock);

        when(comercioRepository.findById(id)).thenReturn(Optional.of(comercio));

        ResponseEntity<ComercioDTO> response = projetoService.pesquisarComercio(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Loja de Ferramentas", response.getBody().nomeComercio());
        assertEquals(10L, response.getBody().idCidade());

        verify(comercioRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve retornar lista de todos os comércios")
    void pesquisarComercios() {
        Cidade cidadeMock = new Cidade();
        cidadeMock.setId(10L);

        Comercio c1 = new Comercio();
        c1.setId(1L);
        c1.setNomeComercio("Comércio 1");
        c1.setTipoComercio(TipoComercio.PADARIA);
        c1.setCidade(cidadeMock);

        Comercio c2 = new Comercio();
        c2.setId(2L);
        c2.setNomeComercio("Comércio 2");
        c2.setTipoComercio(TipoComercio.POSTO_GASOLINA);
        c2.setCidade(cidadeMock);

        when(comercioRepository.findAll()).thenReturn(List.of(c1, c2));

        ResponseEntity<List<ComercioDTO>> response = projetoService.pesquisarComercios();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());

        assertEquals(10L, response.getBody().get(0).idCidade());

        verify(comercioRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve incluir um comércio vinculando a uma cidade existente")
    void incluirComercio() {
        TipoComercio tipo = TipoComercio.PADARIA;
        CidadeDTO cidadeDtoMock = new CidadeDTO(10L, "Cidade Teste", "SC", true);

        ComercioDTO inputDto = new ComercioDTO(
                null,
                "Padaria",
                "Dono",
                tipo,
                10L
        );

        ComercioDTOResponse responseDto = new ComercioDTOResponse(
                1L,
                "Padaria",
                "Dono",
                "PADARIA",
                cidadeDtoMock
        );

        Comercio comercioEntity = new Comercio();
        Cidade cidadeEntity = new Cidade();
        cidadeEntity.setId(10L);

        when(comercioMapper.dtoToEntity(inputDto)).thenReturn(comercioEntity);
        when(cidadeRepository.findById(10L)).thenReturn(Optional.of(cidadeEntity));
        when(comercioRepository.save(any(Comercio.class))).thenReturn(comercioEntity);
        when(comercioMapper.entityToResponse(comercioEntity)).thenReturn(responseDto);

        ResponseEntity<ComercioDTOResponse> response = projetoService.incluirComercio(inputDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().id());

        verify(cidadeRepository).findById(10L);
        verify(comercioRepository).save(any(Comercio.class));
    }

    @Test
    @DisplayName("Deve alterar comércio existente com sucesso")
    void alterarComercio() {
        TipoComercio tipo = TipoComercio.LANCHONETE;
        CidadeDTO cidadeDtoMock = new CidadeDTO(1L, "Florianópolis", "SC", true);

        ComercioDTO dto = new ComercioDTO(
                1L,
                "Nome Alterado",
                "Responsável Novo",
                tipo,
                1L
        );

        ComercioDTOResponse res = new ComercioDTOResponse(
                1L,
                "Nome Alterado",
                "Responsável Novo",
                "LANCHONETE",
                cidadeDtoMock
        );

        Comercio comercio = new Comercio();

        when(comercioRepository.findById(1L)).thenReturn(Optional.of(comercio));
        when(comercioMapper.dtoToEntity(dto)).thenReturn(comercio);
        when(comercioRepository.save(comercio)).thenReturn(comercio);
        when(comercioMapper.entityToResponse(comercio)).thenReturn(res);

        ResponseEntity<ComercioDTOResponse> response = projetoService.alterarComercio(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Nome Alterado", response.getBody().nomeComercio());

        verify(comercioRepository).save(comercio);
    }

    @Test
    @DisplayName("Deve excluir comércio com sucesso")
    void excluirComercio() {
        Long id = 1L;
        when(comercioRepository.findById(id)).thenReturn(Optional.of(new Comercio()));

        ResponseEntity<Void> response = projetoService.excluirComercio(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(comercioRepository).deleteById(id);
    }
}
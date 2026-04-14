package com.placeti.avaliacao.service;

import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.dto.ComercioDTO;
import com.placeti.avaliacao.dto.ComercioDTOResponse;
import com.placeti.avaliacao.mapper.CidadeMapper;
import com.placeti.avaliacao.mapper.ComercioMapper;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.repository.CidadeRepository;
import com.placeti.avaliacao.repository.ComercioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

//------------------------------------------------------------------
/** Service usado para acessar os repositórios da aplicação */
//------------------------------------------------------------------
@Service
public class ProjetoService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final CidadeRepository cidadeRepository;
	private final ComercioRepository comercioRepository;
	private final ComercioMapper comercioMapper;
	private final CidadeMapper cidadeMapper;

	public ProjetoService(CidadeRepository cidadeRepository, ComercioRepository comercioRepository, ComercioMapper comercioMapper, CidadeMapper cidadeMapper) {
		this.cidadeRepository = cidadeRepository;
		this.comercioRepository = comercioRepository;
		this.comercioMapper = comercioMapper;
		this.cidadeMapper = cidadeMapper;
	}

	//---------------------------------------------------------
	/** Método que busca uma cidade pelo seu ID */
	//---------------------------------------------------------
	public ResponseEntity<CidadeDTO> pesquisarCidade(Long id) {
		Optional<Cidade> cidadeOptional = cidadeRepository.findById(id);
		if(cidadeOptional.isPresent()) {
			return ResponseEntity.ok(new CidadeDTO(cidadeOptional.get()));
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cidade Not Found");
	}

	//---------------------------------------------------------
	/** Método que retorna todas as cidades cadastradas */
	//---------------------------------------------------------
	public ResponseEntity<List<CidadeDTO>> pesquisarCidades() {
		List<Cidade> cidades = cidadeRepository.findAll();
		List<CidadeDTO> cidadeDTOs = cidades.stream().map(CidadeDTO::new).toList();

		return ResponseEntity.status(HttpStatus.OK).body(cidadeDTOs);
	}

	//----------------------------------------------------------
	/** Método chamado para incluir uma nova cidade */
	//----------------------------------------------------------	
	public ResponseEntity<CidadeDTO> incluirCidade(CidadeDTO dto) {
		Cidade cidade = cidadeMapper.dtoToEntity(dto);
		Cidade cidadeSalva = cidadeRepository.save(cidade);

		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeMapper.entityToDTO(cidadeSalva));
	}

	//----------------------------------------------------------
	/** Método chamado para alterar os dados de uma cidade */
	//----------------------------------------------------------
	public ResponseEntity<CidadeDTO> alterarCidade(CidadeDTO dto) {
		Optional<Cidade> cidadeOptional = cidadeRepository.findById(dto.id());
		if(cidadeOptional.isPresent()) {
			Cidade cidadeExistente = cidadeMapper.dtoToEntity(dto);
			Cidade cidadeAtualizada = cidadeRepository.save(cidadeExistente);
			return ResponseEntity.status(HttpStatus.OK).body(cidadeMapper.entityToDTO(cidadeAtualizada));
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cidade Not Found");
	}

	//----------------------------------------------------------
	/** Método chamado para excluir uma cidade */
	//----------------------------------------------------------	
	public ResponseEntity<Void> excluirCidade(Long idCidade) {
		Optional<Cidade> cidadeOptional = cidadeRepository.findById(idCidade);
		if(cidadeOptional.isPresent()) {
			cidadeRepository.deleteById(idCidade);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cidade Not Found");
	}



	//---------------------------------------------------------
	/** Método que busca uma comércio pelo seu ID */
	//---------------------------------------------------------
	public ResponseEntity<ComercioDTO> pesquisarComercio(Long id) {
		Optional<Comercio> comercioOptional = comercioRepository.findById(id);
		if(comercioOptional.isPresent()) {
			return ResponseEntity.ok(new ComercioDTO(comercioOptional.get()));
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comercio Not Found");
	}

	//---------------------------------------------------------
	/** Método que retorna todos os comércios cadastrados */
	//---------------------------------------------------------
	public ResponseEntity<List<ComercioDTO>> pesquisarComercios() {
		List<Comercio> comercios = comercioRepository.findAll();
		List<ComercioDTO> comercioDTOs = comercios.stream().map(ComercioDTO::new).toList();

		return ResponseEntity.status(HttpStatus.OK).body(comercioDTOs);
	}

	//----------------------------------------------------------
	/** Método chamado para incluir um novo comércio */
	//----------------------------------------------------------
	public ResponseEntity<ComercioDTOResponse> incluirComercio(ComercioDTO dto) {
		Comercio comercio = comercioMapper.dtoToEntity(dto);
		Cidade cidade = cidadeRepository.findById(dto.idCidade()).orElseThrow(EntityNotFoundException::new);
		comercio.setCidade(cidade);
		Comercio comercioSalvo = comercioRepository.save(comercio);

		return ResponseEntity.status(HttpStatus.CREATED).body(comercioMapper.entityToResponse(comercioSalvo));
	}

	//----------------------------------------------------------
	/** Método chamado para alterar os dados de um comércio */
	//----------------------------------------------------------
	public ResponseEntity<ComercioDTOResponse> alterarComercio(ComercioDTO dto) {
		Optional<Comercio> comercioOptional = comercioRepository.findById(dto.id());
		if(comercioOptional.isPresent()) {
			Comercio comercioExistente = comercioMapper.dtoToEntity(dto);
			Comercio comercioAtualizado = comercioRepository.save(comercioExistente);
			return ResponseEntity.status(HttpStatus.OK).body(comercioMapper.entityToResponse(comercioAtualizado));
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comercio Not Found");
	}

	//----------------------------------------------------------
	/** Método chamado para excluir um comércio */
	//----------------------------------------------------------
	public ResponseEntity<Void> excluirComercio(Long idComercio) {
		Optional<Comercio> comercioOptional = comercioRepository.findById(idComercio);
		if(comercioOptional.isPresent()) {
			comercioRepository.deleteById(idComercio);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comercio Not Found");
	}
}

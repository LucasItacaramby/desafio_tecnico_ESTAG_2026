package com.placeti.avaliacao.mapper;

import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.model.Cidade;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class CidadeMapper {

    public abstract CidadeDTO entityToDTO(Cidade cidade);

    public abstract Cidade dtoToEntity(CidadeDTO dto);
}

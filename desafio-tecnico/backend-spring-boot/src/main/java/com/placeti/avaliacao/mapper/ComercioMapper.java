package com.placeti.avaliacao.mapper;

import com.placeti.avaliacao.dto.ComercioDTO;
import com.placeti.avaliacao.dto.ComercioDTOResponse;
import com.placeti.avaliacao.model.Comercio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class ComercioMapper {

    public abstract ComercioDTOResponse entityToResponse(Comercio comercio);

    @Mapping(source = "idCidade", target = "cidade.id")
    public abstract Comercio dtoToEntity(ComercioDTO dto);
}

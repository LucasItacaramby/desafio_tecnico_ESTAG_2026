package com.placeti.avaliacao.dto;

import com.placeti.avaliacao.model.Comercio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ComercioDTOResponse(

        Long id,

        @NotBlank(message = "O nome do comércio é obrigatório")
        @Size(max = 100)
        String nomeComercio,

        @NotBlank(message = "O nome do responsável é obrigatório")
        @Size(max = 100)
        String nomeResponsavel,

        String tipoComercio,

        @NotNull(message = "A cidade é obrigatória")
        CidadeDTO cidade
) {

}

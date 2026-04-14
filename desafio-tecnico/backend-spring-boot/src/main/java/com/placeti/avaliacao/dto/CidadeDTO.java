package com.placeti.avaliacao.dto;

import com.placeti.avaliacao.model.Cidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO que guarda os dados de uma cidade
 */
public record CidadeDTO(

        //---------------------------------------
        // Atributos do DTO
        //---------------------------------------
        Long id,

        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100)
        String nome,

        @NotBlank(message = "A UF é obrigatória")
        @Size(max = 100)
        String uf,

        @NotNull(message = "A informação de capital é obrigatória")
        Boolean capital
) {
        public CidadeDTO(Cidade cidade) {
            this(cidade.getId(), cidade.getNome(), cidade.getUf(), cidade.getCapital());
        }
}

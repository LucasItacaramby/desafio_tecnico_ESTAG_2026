package com.placeti.avaliacao.dto;

import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.model.Comercio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO que guarda os dados de um comercio
 */
public record ComercioDTO(

        //---------------------------------------
        // Atributos do DTO
        //---------------------------------------
        Long id,

        @NotBlank(message = "O nome do comércio é obrigatório")
        @Size(max = 100)
        String nomeComercio,

        @NotBlank(message = "O nome do responsável é obrigatório")
        @Size(max = 100)
        String nomeResponsavel,

        TipoComercio tipoComercio,

        @NotNull(message = "A cidade é obrigatória")
        Long idCidade
) {
        public ComercioDTO(Comercio comercio) {
                this(comercio.getId(), comercio.getNomeComercio(), comercio.getNomeResponsavel(), comercio.getTipoComercio(), comercio.getCidade().getId());
        }
}

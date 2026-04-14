package com.placeti.avaliacao.model;

import com.placeti.avaliacao.dto.ComercioDTO;
import com.placeti.avaliacao.dto.TipoComercio;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

//-------------------------------------------------
/** Entidade que guarda os dados de um comércio */
//-------------------------------------------------
@Data
@NoArgsConstructor
@Entity
@Table(name = "comercio")
public class Comercio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome_comercio", length = 100, nullable = false)
    private String nomeComercio;

    @Column(name = "nome_responsavel", length = 100, nullable = false)
    private String nomeResponsavel;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_comercio", length = 100, nullable = false)
    private TipoComercio tipoComercio;

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;
}

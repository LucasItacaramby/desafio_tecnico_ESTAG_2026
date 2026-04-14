package com.placeti.avaliacao.model;

import com.placeti.avaliacao.dto.CidadeDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//-------------------------------------------------
/** Entidade que guarda os dados de uma cidade */
//-------------------------------------------------
@Data
@NoArgsConstructor
@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    
    @Column(name = "uf", length = 100, nullable = false)
    private String uf;
    
    @Column(name = "capital", nullable = false)
    private Boolean capital;

    @OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cidade")
    private List<Comercio> comercio;

    public Cidade(CidadeDTO dto) {
        this.nome = dto.nome();
        this.uf = dto.uf();
        this.capital = dto.capital();
    }
}

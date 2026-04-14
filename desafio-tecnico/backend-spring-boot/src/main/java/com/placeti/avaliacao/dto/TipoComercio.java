package com.placeti.avaliacao.dto;

public enum TipoComercio {
    FARMACIA("Farmácia"), PADARIA("Padaria"), POSTO_GASOLINA("Posto de Gasolina"), LANCHONETE("Lanchonete");

    private final String descricao;

    public String getDescricao() {
        return descricao;
    }

    TipoComercio(String descricao) {
        this.descricao = descricao;
    }
}

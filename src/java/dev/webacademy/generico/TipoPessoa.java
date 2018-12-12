package dev.webacademy.generico;

public enum TipoPessoa {

    PESSOA_FISICA(1, "Física"),
    PESSOA_JURIDICA(2, "Juridica");

    private int valor;
    private String descricao;

    TipoPessoa(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}

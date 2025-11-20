package br.icev.vendas;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto {
    private final String codigo;
    private final String nome;
    private final BigDecimal preco;

    public Produto(String codigo, String nome, BigDecimal preco) {
        if (codigo == null) throw new NullPointerException("codigo nulo");
        if (nome == null) throw new NullPointerException("nome nulo");
        if (preco == null) throw new NullPointerException("preco nulo");
        if (preco.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("preco negativo");
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }


    public BigDecimal getPrecoUnitario() {
        return preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return codigo.equals(produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}

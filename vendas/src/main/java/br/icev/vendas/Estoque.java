package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import br.icev.vendas.excecoes.SemEstoqueException;

import java.util.LinkedHashMap;
import java.util.Map;

public class Estoque {
    private final Map<String, Integer> disponiveis = new LinkedHashMap<>();

    public void adicionarEstoque(String codigo, int quantidade) throws QuantidadeInvalidaException {
        if (codigo == null) throw new NullPointerException("codigo nulo");
        if (quantidade <= 0) throw new QuantidadeInvalidaException("quantidade invalida");
        int atual = disponiveis.getOrDefault(codigo, 0);
        disponiveis.put(codigo, atual + quantidade);
    }

    public int getDisponivel(String codigo) {
        if (codigo == null) throw new NullPointerException("codigo nulo");
        return disponiveis.getOrDefault(codigo, 0);
    }

    public void reservar(String codigo, int quantidade)
            throws SemEstoqueException, QuantidadeInvalidaException {
        if (codigo == null) throw new NullPointerException("codigo nulo");
        if (quantidade <= 0) throw new QuantidadeInvalidaException("quantidade invalida");
        int atual = disponiveis.getOrDefault(codigo, 0);
        if (atual < quantidade) throw new SemEstoqueException("sem estoque suficiente");
        disponiveis.put(codigo, atual - quantidade);
    }
}

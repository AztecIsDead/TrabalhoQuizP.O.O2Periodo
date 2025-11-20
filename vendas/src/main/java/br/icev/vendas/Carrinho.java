package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Carrinho {
    private final Map<Produto, Integer> itens = new LinkedHashMap<>();

    public void adicionar(Produto produto, int quantidade) throws QuantidadeInvalidaException {
        if (produto == null) throw new NullPointerException("produto nulo");
        if (quantidade <= 0) throw new QuantidadeInvalidaException("quantidade invalida");
        Integer atual = itens.getOrDefault(produto, 0);
        itens.put(produto, atual + quantidade);
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = UtilDinheiro.zero();
        for (Map.Entry<Produto, Integer> e : itens.entrySet()) {
            BigDecimal linha = UtilDinheiro.multiplicar(e.getKey().getPrecoUnitario(), e.getValue());
            subtotal = subtotal.add(linha).setScale(2, RoundingMode.HALF_EVEN);
        }
        return subtotal.setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getTotalCom(PoliticaDesconto politica) {
        if (politica == null) return getSubtotal();
        BigDecimal subtotal = getSubtotal();
        BigDecimal total = politica.aplicar(subtotal);
        if (total == null) return UtilDinheiro.zero();
        if (total.compareTo(BigDecimal.ZERO) < 0) return UtilDinheiro.zero();
        return total.setScale(2, RoundingMode.HALF_EVEN);
    }

    public int getTotalItens() {
        int soma = 0;
        for (Integer q : itens.values()) soma += q;
        return soma;
    }

    public Map<Produto, Integer> getItens() {
        return Collections.unmodifiableMap(itens);
    }

    public void limpar() {
        itens.clear();
    }
}

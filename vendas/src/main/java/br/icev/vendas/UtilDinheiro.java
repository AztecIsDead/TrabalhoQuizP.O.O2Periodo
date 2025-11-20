package br.icev.vendas;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UtilDinheiro {
    public static BigDecimal multiplicar(BigDecimal valor, int quantidade) {
        if (valor == null) throw new NullPointerException("valor nulo");
        BigDecimal q = BigDecimal.valueOf(quantidade);
        return valor.multiply(q).setScale(2, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal somar(BigDecimal a, BigDecimal b) {
        if (a == null) a = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
        if (b == null) b = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
        return a.add(b).setScale(2, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal zero() {
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
    }
}

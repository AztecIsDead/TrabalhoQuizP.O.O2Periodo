package br.icev.vendas;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pedido {
    public enum Status { PAGO }

    private final Map<String, Integer> itensPorCodigo = new LinkedHashMap<>();
    private final BigDecimal totalPago;
    private final String codigoAutorizacao;
    private final Status status;

    public Pedido(Map<String, Integer> itensPorCodigo, BigDecimal totalPago,
                  String codigoAutorizacao, Status status) {
        if (itensPorCodigo != null) this.itensPorCodigo.putAll(itensPorCodigo);
        this.totalPago = totalPago == null ? UtilDinheiro.zero() : totalPago.setScale(2, java.math.RoundingMode.HALF_EVEN);
        this.codigoAutorizacao = codigoAutorizacao;
        this.status = status;
    }

    public BigDecimal getTotalPago() { return totalPago; }

    public String getCodigoAutorizacao() { return codigoAutorizacao; }

    public Status getStatus() { return status; }

    public int getQuantidadeItem(String codigo) {
        if (codigo == null) throw new NullPointerException("codigo nulo");
        return itensPorCodigo.getOrDefault(codigo, 0);
    }

    public Map<String, Integer> getItensPorCodigo() {
        return Collections.unmodifiableMap(itensPorCodigo);
    }
}

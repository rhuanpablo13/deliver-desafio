package com.deliver.calculos;

import java.math.BigDecimal;

public class Multa implements MultasAtraso {

    private Integer diasAtraso;
    private BigDecimal valorDocumento;

    private final BigDecimal _2 = new BigDecimal("2");
    private final BigDecimal _3 = new BigDecimal("3");
    private final BigDecimal _5 = new BigDecimal("5");
    private final BigDecimal _100 = new BigDecimal("100");


    public Multa(Integer diasAtraso, BigDecimal valorDocumento) {
        this.diasAtraso = diasAtraso;
        this.valorDocumento = valorDocumento;
    }

    @Override
    public double calcular() {
        if (diasAtraso <= 3) {
            return valorDocumento.multiply(_2).divide(_100).doubleValue();
        }
        if (diasAtraso > 3 && diasAtraso <= 5) {
            return valorDocumento.multiply(_3).divide(_100).doubleValue();
        }
        return valorDocumento.multiply(_5).divide(_100).doubleValue();
    }
    
}
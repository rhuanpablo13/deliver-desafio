package com.deliver.calculos;

import java.math.BigDecimal;

public class Juros implements MultasAtraso {

    private Integer diasAtraso;
    private BigDecimal valorDocumento;


    private final BigDecimal _01 = new BigDecimal("0.1");
    private final BigDecimal _02 = new BigDecimal("0.2");
    private final BigDecimal _03 = new BigDecimal("0.3");


    public Juros(Integer diasAtraso, BigDecimal valorDocumento) {
        this.diasAtraso = diasAtraso;
        this.valorDocumento = valorDocumento;
    }


    @Override
    public double calcular() {
        if (diasAtraso <= 3) {
            return valorDocumento.multiply(_01).doubleValue();
        }
        if (diasAtraso > 3 && diasAtraso <= 5) {
            return valorDocumento.multiply(_02).doubleValue();
        }
        return valorDocumento.multiply(_03).doubleValue();
    }
    
}
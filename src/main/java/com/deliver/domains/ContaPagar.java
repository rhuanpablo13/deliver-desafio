package com.deliver.domains;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deliver.dto.ContaPagarDTO;
import com.deliver.utils.DatePattern;
import com.deliver.utils.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CONTA_A_PAGAR")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ContaPagar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal valorOriginal;

    @Column(nullable = false)
    private Date dataVencimento;

    @Column(nullable = false)
    private Date dataPagamento;

    @Column(nullable = false)
    private BigDecimal multa;

    @Column(nullable = false)
    private BigDecimal jurosDia;

    @Column(nullable = false)
    private Integer diasAtraso;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal valorCorrigido;
    

    public ContaPagarDTO convertDTO() {
        return ContaPagarDTO.builder()
        .id(id)
        .nome(nome)
        .dataPagamento(Utils.dateToString(dataPagamento, DatePattern.DDMMYYYY.getPattern()))
        .dataVencimento(Utils.dateToString(dataVencimento, DatePattern.DDMMYYYY.getPattern()))
        .valorOriginal(Utils.formatMoney(valorOriginal))
        .jurosDia(Utils.formatMoney(jurosDia))
        .multa(Utils.formatMoney(multa))
        .diasAtraso(diasAtraso)
        .valorCorrigido(Utils.formatMoney(valorCorrigido))
        .build();
    }
}

package com.deliver.domain;

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

@Entity
@Table(name = "CONTA_A_PAGAR")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Column()
    private Date dataPagamento;


    public ContaPagarDTO convertDTO() {
        return ContaPagarDTO.builder()
        .id(id)
        .nome(nome)
        .dataPagamento(Utils.dateToString(dataPagamento, DatePattern.DDMMYYYY))
        .dataVencimento(Utils.dateToString(dataVencimento, DatePattern.DDMMYYYY))
        .valorOriginal(Utils.formmatMoney(valorOriginal))
        .build();
    }
}

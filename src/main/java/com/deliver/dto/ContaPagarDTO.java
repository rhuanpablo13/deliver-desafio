package com.deliver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ContaPagarDTO {
    
    private Integer id;
    private String nome;
    private String valorOriginal;
    private String dataVencimento;
    private String dataPagamento;
    private Integer diasAtraso;
    private String multa;
    private String jurosDia;
    private String valorCorrigido;
}
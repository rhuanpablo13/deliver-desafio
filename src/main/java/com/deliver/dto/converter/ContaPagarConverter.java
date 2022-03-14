package com.deliver.dto.converter;

import java.math.BigDecimal;

import com.deliver.domains.ContaPagar;
import com.deliver.dto.ContaPagarRequestDTO;
import com.deliver.utils.DatePattern;
import com.deliver.utils.Utils;

import org.springframework.stereotype.Service;

@Service
public class ContaPagarConverter {
    
    public static ContaPagar convertToContaPagar(ContaPagarRequestDTO dto) {
        ContaPagar conta = new ContaPagar();
        conta.setDataPagamento(Utils.stringToDate(dto.getDataPagamento(), DatePattern.YYYY_MM_DD.getPattern()));
        conta.setDataVencimento(Utils.stringToDate(dto.getDataVencimento(), DatePattern.YYYY_MM_DD.getPattern()));
        String valorOriginal = corrigirValoresMonetarios(dto.getValorOriginal());
        conta.setValorOriginal(new BigDecimal(valorOriginal));
        conta.setDiasAtraso(0);
        conta.setJurosDia(BigDecimal.ZERO);
        conta.setMulta(BigDecimal.ZERO);
        conta.setValorCorrigido(BigDecimal.ZERO);
        conta.setNome(dto.getNome());
        return conta;
    }

    private static String corrigirValoresMonetarios(String valor) {
        return valor.replace(".", "").replace(",", ".");
    }
}
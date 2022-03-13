package com.deliver.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.deliver.calculos.Juros;
import com.deliver.calculos.Multa;
import com.deliver.domains.ContaPagar;
import com.deliver.repository.ContaPagarRepository;
import com.deliver.utils.DateOperations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaPagarService {

    private static final Logger log = LoggerFactory.getLogger(ContaPagarService.class);

    @Autowired
    private ContaPagarRepository contaPagarRepository;

      
    /**
     * Salva/altera um conta a pagar
     * @param contaPagar
     * @return ContaPagar
     */
    public ContaPagar persist(ContaPagar contaPagar) {
        log.info("Salvando novo contas a pagar {}", contaPagar.getNome());
        return contaPagarRepository.save(contaPagar);
    }

    /**
     * Deleta um conta a pagar por id
     * @param contaPagar
     */
    public void delete(ContaPagar contaPagar) {
        log.info("Deletando contas a pagar {}", contaPagar.getNome());
        contaPagarRepository.deleteById(contaPagar.getId());
    }

    /**
     * Busca um conta a pagar por id
     * @param id
     * @return ContaPagar
     */
    public Optional<ContaPagar> findById(Integer id) {
        log.info("Buscando contas a pagar por id {}", id);
        return contaPagarRepository.findById(id);
    }

    /**
     * Retorna uma lista de contas a pagar
     * @return List<ContaPagar>
     */
    public List<ContaPagar> findAll() {
        log.info("Buscando todos os contas a pagar");
        return contaPagarRepository.findAll();
    }


    public void processarConta(ContaPagar contaPagar) {
        
        int diasAtraso = DateOperations.subtrairDatas(contaPagar.getDataPagamento(), contaPagar.getDataVencimento());
        
        // se diasAtraso > 0 -> está atrasado
        if (diasAtraso > 0) {
            diasAtraso = diasAtraso * -1;
            log.info("conta a pagar está atrasado {} dias", (diasAtraso));

            contaPagar.setDiasAtraso(diasAtraso);

            CalculadoraJurosMulta calculadora = new CalculadoraJurosMulta();

            Juros juros = new Juros(diasAtraso, contaPagar.getValorOriginal());
            Multa multa = new Multa(diasAtraso, contaPagar.getValorOriginal());

            double valorJuros = calculadora.calcular(juros);
            double valorMulta = calculadora.calcular(multa);

            contaPagar.setMulta(new BigDecimal(valorMulta));
            contaPagar.setJurosDia(new BigDecimal(valorJuros));

            contaPagar.setValorCorrigido(
                contaPagar.getValorOriginal()
                .add(contaPagar.getMulta())
                .add(contaPagar.getJurosDia())
            );

            log.info("Conta a pagar calculado: " + contaPagar.toString());
        } else {
            contaPagar.setValorCorrigido(
                contaPagar.getValorOriginal()
            );
        }
    }

}
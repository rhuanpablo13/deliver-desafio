package com.deliver.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.deliver.domains.ContaPagar;
import com.deliver.dto.ContaPagarDTO;
import com.deliver.dto.ContaPagarRequestDTO;
import com.deliver.dto.converter.ContaPagarConverter;
import com.deliver.response.Response;
import com.deliver.services.ContaPagarService;
import com.deliver.utils.DatePattern;
import com.deliver.validations.ValidationRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta-pagar")
public class ContaPagarController {
    
    private static final Logger log = LoggerFactory.getLogger(ContaPagarController.class);

    @Autowired
    private ValidationRequest validation;

    @Autowired
    private ContaPagarService contaPagarService;


    @PostMapping()
    public ResponseEntity<Response<ContaPagarDTO>> save(@Valid @RequestBody ContaPagarRequestDTO dto, BindingResult result) {

        Response<ContaPagarDTO> response = new Response<>();

        validateRequestDto(dto, result);
        if (result.hasErrors()) {
            response.addMessages(result);
            return ResponseEntity.badRequest().body(response);
        }

        ContaPagar conta = ContaPagarConverter.convertToContaPagar(dto);
        contaPagarService.processarConta(conta);

        conta = contaPagarService.persist(conta);
        response.setData(conta.convertDTO());

        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/all")
    public ResponseEntity<Response<List<ContaPagarDTO>>> list() {

        Response<List<ContaPagarDTO>> response = new Response<>();
     
        List<ContaPagar> contas = contaPagarService.findAll();
        if (contas == null || contas.isEmpty()) {
            response.addMessage("Nenhum registro encontrado");
            return ResponseEntity.status(404).body(response);
        }
        
        log.info("{} contas a pagar encontrados", contas.size());
        
        List<ContaPagarDTO> contasDto = new ArrayList<>();
        contas.forEach(conta -> {
            contasDto.add(conta.convertDTO());
        });

        response.setData(contasDto);
        return ResponseEntity.ok().body(response);
    }


    private void validateRequestDto(ContaPagarRequestDTO dto, BindingResult result) {
        validation.emptyOrNull(dto.getNome(), "Nome do conta a pagar é obrigatório", result);
        validation.dateStringFormat(dto.getDataPagamento(), DatePattern.YYYY_MM_DD.getPattern(), "Data de pagamento inválida, informe a data no formato: " + DatePattern.YYYY_MM_DD, result);
        validation.dateStringFormat(dto.getDataVencimento(), DatePattern.YYYY_MM_DD.getPattern(), "Data de vencimento inválida, informe a data no formato: " + DatePattern.YYYY_MM_DD, result);
        validation.bigDecimalStringFormat(dto.getValorOriginal(), "Valor original é inválido, informe no padrão brasileiro, por exemplo: 1.250,50", result);
    }
}
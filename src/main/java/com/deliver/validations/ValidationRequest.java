package com.deliver.validations;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Service
public class ValidationRequest {
    
    /**
     * Verifica se o parametro field é nulo ou branco
     * @param field
     * @param message
     * @param result
     */
    public void emptyOrNull(String field, String message, BindingResult result) {
        if (field == null || field.isBlank()) {
            result.addError(new ObjectError("dto", message));
        }
    }

    /**
     * tenta converter uma string para o format especificado
     * @param field
     * @param format
     * @param message
     * @param result
     */
    public void dateStringFormat(String field, String format, String message, BindingResult result) {
        if (field == null || field.isBlank()) {
            result.addError(new ObjectError("dto", "Valor null: " + message));
        }

        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            df.parse(field);
        } catch (ParseException e) {
            result.addError(new ObjectError("dto", "Falha de conversão: " + message));
        }
    }

    /**
     * Tenta converter o field para big decimal
     * @param field
     * @param message
     * @param result
     */
    public void bigDecimalStringFormat(String field, String message, BindingResult result) {
        if (field == null || field.isBlank()) {
            result.addError(new ObjectError("dto", "Valor null: " + message));
        }

        try {
            field = field.replace(".", "").replace(",", ".");  
            new BigDecimal(field);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            result.addError(new ObjectError("dto", "Falha de conversão do valor: " + field + " -> " + message));
        }
    }
}
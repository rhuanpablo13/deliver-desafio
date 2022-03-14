package com.deliver.deviler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.deliver.domains.ContaPagar;
import com.deliver.services.ContaPagarService;
import com.deliver.utils.DatePattern;
import com.deliver.utils.Utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("default")
class DeliverApplicationTests {

	@Autowired
	ContaPagarService contaPagarService;


	@Test
	void contextLoads() {
	}

	@Test
	void processarContaDataVencimentoMaiorDataPagamentoTest() {
		ContaPagar conta = new ContaPagar();
		conta.setNome("conta teste");
		conta.setDataPagamento(Utils.stringToDate("2022-03-14", DatePattern.YYYY_MM_DD.getPattern()));
		conta.setDataVencimento(Utils.stringToDate("2022-03-15", DatePattern.YYYY_MM_DD.getPattern()));
		conta.setValorOriginal(BigDecimal.TEN);
		contaPagarService.processarConta(conta);
		
		// data de vencimento maior que a de pagamento
		assertEquals(new BigDecimal("10.00"), conta.getValorCorrigido().setScale(2, RoundingMode.UP));
	}


	@Test
	void processarContaAte3DiasAtrasoTest() {
		ContaPagar conta = new ContaPagar();
		conta.setNome("conta teste");
		conta.setDataPagamento(Utils.stringToDate("2022-03-15", DatePattern.YYYY_MM_DD.getPattern()));
		conta.setDataVencimento(Utils.stringToDate("2022-03-13", DatePattern.YYYY_MM_DD.getPattern()));
		conta.setValorOriginal(BigDecimal.TEN);
		contaPagarService.processarConta(conta);
		
		assertEquals(new BigDecimal("11.21"), conta.getValorCorrigido().setScale(2, RoundingMode.UP));
	}

	@Test
	void processarContaSuperior3DiasAtrasoTest() {
		ContaPagar conta = new ContaPagar();
		conta.setNome("conta teste");
		conta.setDataPagamento(Utils.stringToDate("2022-03-15", DatePattern.YYYY_MM_DD.getPattern()));
		conta.setDataVencimento(Utils.stringToDate("2022-03-10", DatePattern.YYYY_MM_DD.getPattern()));
		conta.setValorOriginal(BigDecimal.TEN);
		contaPagarService.processarConta(conta);
		
		assertEquals(new BigDecimal("12.30"), conta.getValorCorrigido().setScale(2, RoundingMode.UP));
	}

	@Test
	void processarContaSuperior5DiasAtrasoTest() {
		ContaPagar conta = new ContaPagar();
		conta.setNome("conta teste");
		conta.setDataPagamento(Utils.stringToDate("2022-03-15", DatePattern.YYYY_MM_DD.getPattern()));
		conta.setDataVencimento(Utils.stringToDate("2022-03-05", DatePattern.YYYY_MM_DD.getPattern()));
		conta.setValorOriginal(BigDecimal.TEN);
		contaPagarService.processarConta(conta);
		
		assertEquals(new BigDecimal("13.50"), conta.getValorCorrigido().setScale(2, RoundingMode.UP));
	}
}

package com.szalkowm.homework.infrastructure.rest.controller;

import com.szalkowm.homework.infrastructure.rest.dto.LoanApplicationDto;
import com.szalkowm.homework.infrastructure.rest.dto.LoanDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void loanApplicationTest() {

        LoanApplicationDto requestBody = new LoanApplicationDto();
        requestBody.setAmount(new BigDecimal("1000"));
        requestBody.setTermInDays(60);


        ResponseEntity<LoanDto> responseEntity =
                restTemplate.postForEntity("/loans", requestBody, LoanDto.class);


        LoanDto loanDto = responseEntity.getBody();

        assertNotNull(loanDto);
        assertNotNull(loanDto.getId());
        assertEquals(0, new BigDecimal("1000").compareTo(loanDto.getAmount()));
        assertEquals(0, new BigDecimal("100").compareTo(loanDto.getCost()));
        assertEquals(LocalDate.now().plusDays(60), loanDto.getDueDate());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}

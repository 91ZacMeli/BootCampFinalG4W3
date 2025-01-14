package com.mercadolibre.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.demo.dto.TokenDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.net.URI;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureTestEntityManager
@SpringBootTest
@AutoConfigureMockMvc
public class BatchStockControllerTest {

    private URI uri;

    @Autowired
    MockMvc mockMvc;

    private TokenDTO tokenDTO;

    @BeforeEach
    public void testandoAutenticacao() throws Exception {
        String json = "{\"user\": \"filipe\", \"senha\": \"123\"}";
        uri = new URI("/auth");

        MvcResult resultContendoToken = mockMvc
                .perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andReturn();
        tokenDTO = new ObjectMapper().readValue(resultContendoToken.getResponse().getContentAsString(), TokenDTO.class);
    }
    
    @Test
    public void testSaveBatchStocNoSuccess() throws Exception {

        uri = new URI("/api/v1/fresh-products/batchstock/save");

        assertNotNull(uri);

        String requestJson =  "{\"currentTemperature\": 38,\"minimumTemperature\": 5,\"initialQuantity\": 200,\"currentQuantity\": 25,\"dueDate\": \"2021-11-11\",\"idSalesAd\":1000}";

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(requestJson)
                        .header("Content-Type", "application/json")
                        .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isNotFound()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }

    @Test
    public void testSaveBatchStockWithSuccess() throws Exception {

        uri = new URI("/api/v1/fresh-products/batchstock/save");

        assertNotNull(uri);

        String requestJson =  "{\"currentTemperature\": 38,\"minimumTemperature\": 5,\"initialQuantity\": 200,\"currentQuantity\": 25,\"dueDate\": \"2021-11-11\",\"idSalesAd\":1}";

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(requestJson)
                        .header("Content-Type", "application/json")
                        .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isCreated()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }

    @Test
    public void testlistBatchStock() throws Exception {

        uri = new URI("/api/v1/fresh-products/batchstock/list");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(uri).header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isOk()).andReturn();

        String jsonRetorno = result.getResponse().getContentAsString();

        assertNotNull(jsonRetorno);

    }
    
    @Test
    public void testUpdateSellerNoSuccess() throws Exception{

        uri = new URI("/api/v1/fresh-products/batchstock/update/1000");

        assertNotNull(uri);

        String requestJson =  "{\"currentTemperature\": 38,\"minimumTemperature\": 5,\"initialQuantity\": 200,\"currentQuantity\": 25,\"dueDate\": \"2021-11-11\",\"idSalesAd\":1}";

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.put(uri)
                        .content(requestJson)
                        .header("Content-Type", "application/json")
                        .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isNotFound()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }

    @Test
    public void testUpdateSellerWithSuccess() throws Exception{

        uri = new URI("/api/v1/fresh-products/batchstock/update/1");

        assertNotNull(uri);

        String requestJson =  "{\"currentTemperature\": 38,\"minimumTemperature\": 5,\"initialQuantity\": 200,\"currentQuantity\": 25,\"dueDate\": \"2021-11-11\",\"idSalesAd\":1}";

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.put(uri)
                        .content(requestJson)
                        .header("Content-Type", "application/json")
                        .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isCreated()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }
}

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
public class DiscountBlackFridayControllerTest {

    private URI uri;

    @Autowired
    MockMvc mockMvc;

    private TokenDTO tokenDTO;

    @BeforeEach
    public void testandoAutenticacao() throws Exception {
        String json = "{\"user\": \"isac\", \"senha\": \"123\"}";
        uri = new URI("/auth");

        MvcResult resultContendoToken = mockMvc
                .perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andReturn();
        tokenDTO = new ObjectMapper().readValue(resultContendoToken.getResponse().getContentAsString(), TokenDTO.class);
    }

    @Test
    public void testAppyDiscountBlackFridayNoSuccess() throws Exception {
        uri = new URI("/api/v1/fresh-products/cupom-black-friday/applydiscount?cupomBlackFriday=CONGELADOSBLACK&idSeller=31");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .header("Content-Type", "application/json")
                        .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isBadRequest()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }
    @Test
    public void testAppyDiscountBlackFriday() throws Exception {
        uri = new URI("/api/v1/fresh-products/cupom-black-friday/applydiscount?cupomBlackFriday=FRESCOSBLACK&idSeller=1");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .header("Content-Type", "application/json")
                        .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isCreated()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }

    @Test
    public void testListDiscountBlackFridayTtest() throws Exception {

        uri = new URI("/api/v1/fresh-products/cupom-black-friday/list");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(uri).header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isOk()).andReturn();

        String jsonRetorno = result.getResponse().getContentAsString();

        assertNotNull(jsonRetorno);

    }

    @Test
    void listEnumsTeste() throws Exception {

        uri = new URI("/api/v1/fresh-products/cupom-black-friday/listcupoms");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(uri).header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isOk()).andReturn();

        String jsonRetorno = result.getResponse().getContentAsString();

        assertNotNull(jsonRetorno);

    }


    @Test
    public void testDeleteDiscountBlackFridayNoSuccess() throws Exception {

        uri = new URI("/api/v1/fresh-products/cupom-black-friday/delete/88");

        assertNotNull(uri);
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete(uri)
                        .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isNotFound()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }
    @Test
    public void testDeleteDiscountBlackFriday() throws Exception {

        uri = new URI("/api/v1/fresh-products/cupom-black-friday/delete/1");

        assertNotNull(uri);
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete(uri)
                        .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isOk()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }
}
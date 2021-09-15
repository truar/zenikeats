package com.zenika.zenikeats;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureMockMvc
public class OrderAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void an_order_is_created_after_shoppingcart_has_been_validated() throws Exception {
        //language=JSON
        String orderPost = "{\n" +
                "  \"clientId\": \"clientId\",\n" +
                "  \"items\": [{\n" +
                "    \"id\": \"itemId\",\n" +
                "    \"name\": \"itemName\",\n" +
                "    \"price\": 20,\n" +
                "    \"quantity\": 1\n" +
                "  }]\n" +
                "}";
        MvcResult mvcResult = mockMvc.perform(post("/orders")
                .content(orderPost)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andDo(print())
                .andReturn();

        String orderLocation = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(get(orderLocation))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.orderId").value(orderId))
                .andExpect(jsonPath("$.clientId").value("clientId"))
                .andExpect(jsonPath("$.status").value("CREATED"));
        // ... item list...

    }
}

package com.zenika.zenikeats;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = MOCK)
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
        mockMvc.perform(post("/orders")
                .content(orderPost)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isCreated());
    }
}

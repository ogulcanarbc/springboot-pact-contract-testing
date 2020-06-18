package controller;

import com.oglcnarbc.controller.DeliveryController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeliveryController.class)
@AutoConfigureMockMvc
public class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void it_should_be_return_delivery() throws Exception {

        ResultActions resultActions = mockMvc.perform(
                get("/producer/api/v1/delivery/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE));

        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deliveryNumber").value("PO473134"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderNumber").value("4513415"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.supplierId").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fulfillmentType").value("FT"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.healthState").value("HEALTHY"))
                .andDo(print());

    }

}

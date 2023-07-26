package com.javaschoolshop;

import com.javaschoolshop.controller.DatesController;
import com.javaschoolshop.service.DatesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class DatesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DatesService datesService;

    @InjectMocks
    private DatesController datesController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(datesController)
                .build();
    }

    @Test
    public void testGetRevenueStats() throws Exception {
        // Prepare test data
        Object[] stats1 = {"Weeks Revenues", 1000};
        Object[] stats2 = {"Monthly Revenues", 2000};
        List<Object[]> expectedStats = Arrays.asList(stats1, stats2);

        // Mock the behavior of the DatesService
        when(datesService.getRevenueStats()).thenReturn(expectedStats);

        // Perform the API request
        mockMvc.perform(get("/dates/revenue-stats"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0][0]").value("Weeks Revenues"))
                .andExpect(jsonPath("$[0][1]").value(1000))
                .andExpect(jsonPath("$[1][0]").value("Monthly Revenues"))
                .andExpect(jsonPath("$[1][1]").value(2000));

        // Verify the interaction with the DatesService
        Mockito.verify(datesService, Mockito.times(1)).getRevenueStats();
    }
}

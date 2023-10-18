package ru.app.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.app.Main;
import ru.app.model.Result;
import ru.app.service.CalculationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class CalcControllerTest {
    @MockBean
    private CalculationService service;

    @Autowired
    private MockMvc mock;

    private Result res;

    private Gson gson;

    @BeforeEach
    private void init() {
        res = new Result();
        res.setRes("20.0+20.0=40.0");
        gson = new Gson();
    }

    @Test
    public void whenCalculateParams() throws Exception {
        when(service.calculateParams("plus_20.0_20.0")).thenReturn(res);
        mock.perform(post("http://localhost:8080/api/calc/params?value=plus_20_20_divide_2"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void whenCalculateJson() throws Exception {
        when(service.calculateParams("plus_20.0_20.0")).thenReturn(res);
        String requestBody = gson.toJson(res);
        mock.perform(post("http://localhost:8080/api/calc/json")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void whenGetLast() throws Exception {
        when(service.getLastRecord()).thenReturn(Optional.of(res));
        mock.perform(get("http://localhost:8080/api/calc"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void whenGetAll() throws Exception {
        List<Result> list = new ArrayList<>();
        list.add(res);
        when(service.getAll()).thenReturn(list);
        mock.perform(get("http://localhost:8080/api/calc/logs"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
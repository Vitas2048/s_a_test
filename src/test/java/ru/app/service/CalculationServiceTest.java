package ru.app.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.app.Main;
import ru.app.model.Result;
import ru.app.repository.ResultRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class CalculationServiceTest {

    @Autowired
    private CalculationService service;

    private Result res;

    @BeforeEach
    private void init() {
        res = new Result();
        res.setRes("20.0+20.0=40.0");
    }

    @Test
    public void whenCalculateThenResult() {
        var result = service.calculateParams("plus_20.0_20.0");
        assertEquals(res.getRes(), result.getRes());
    }

    @Test
    public void whenWrongOperationThenException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateParams("sad_21_21");
        });
    }
}
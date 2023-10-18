package ru.app.repository;

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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ResultRepositoryTest {
    @Autowired
    private ResultRepository repository;

    private Result res;

    @BeforeEach
    private void init() {
        res = new Result();
        res.setRes("plus_20.0_20.0=40.0");
    }

    @AfterEach
    private void teardown() {
        repository.deleteAll();
    }
    @Test
    public void whenSave() {
        assertEquals(res, repository.save(res));
    }

    @Test
    public void whenGetLast() {
        repository.save(res);
        assertEquals(res, repository.findTopByOrderByIdDesc().get());
    }
    @Test void whenGetAll() {
        var savedRes = repository.save(res);
        var res2 = new Result();
        res2.setRes("plus_20.0_20.0=40.0");
        var savedRes2 = repository.save(res2);

        var list = new ArrayList<>();
        list.add(savedRes);
        list.add(savedRes2);
        assertEquals(repository.findAll(), list);
    }

}
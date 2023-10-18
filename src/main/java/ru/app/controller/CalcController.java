package ru.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.app.model.JsonParam;
import ru.app.model.Result;
import ru.app.service.CalculationService;

import java.util.List;

@RestController
@RequestMapping("/api/calc")
@AllArgsConstructor
public class CalcController {
    private CalculationService calculationService;

    @PostMapping("/params")
    public ResponseEntity<Result> paramCalculate(@RequestParam("value") String params) {
        return ResponseEntity.ok(calculationService.calculateParams(params));
    }

    @PostMapping("/json")
    public ResponseEntity<Result> jsonCalculate(@RequestBody JsonParam body) {
        return ResponseEntity.ok(calculationService.calculateParams(body.getParams()));
    }

    @GetMapping
    public ResponseEntity<Result> getLast() {
        return ResponseEntity.of(calculationService.getLastRecord());
    }

    @GetMapping("/logs")
    public ResponseEntity<List<Result>> getAll() {
        return ResponseEntity.ok(calculationService.getAll());
    }
}

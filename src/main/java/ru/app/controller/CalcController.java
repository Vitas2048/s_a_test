package ru.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.app.model.JsonParam;
import ru.app.model.Result;
import ru.app.service.CalculationService;

@RestController
@RequestMapping("/api/calc")
@AllArgsConstructor
public class CalcController {
    private CalculationService calculationService;

    @PostMapping("/params")
    public ResponseEntity<Result> paramCalculate(@RequestParam("params") String params) {
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

    @GetMapping("logs")
    public ResponseEntity<Result> getAll() {
        return ResponseEntity.of(calculationService.getLastRecord());
    }
}

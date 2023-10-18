package ru.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.model.Result;
import ru.app.repository.ResultRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CalculationService {

    private ResultRepository repository;

    public Result calculateParams(String params) {
        StringBuilder lineBuilder = new StringBuilder();

        String[] parts = params.split("_");

        if (parts.length == 0) {
            throw new IllegalArgumentException("Пустой запрос.");
        }

        double result = 0.0;

        String operator = "plus";

        for (String part : parts) {
            if (isOperator(part)) {
                operator = part;
            } else {
                double operand = Double.parseDouble(part);

                switch (operator) {
                    case "plus" -> {
                        result += operand;
                        lineBuilder.append("+").append(operand);
                    }
                    case "minus" -> {
                        result -= operand;
                        lineBuilder.append("-").append(operand);
                    }
                    case "multiply" -> {
                        result *= operand;
                        lineBuilder.append("*").append(operand);
                    }
                    case "divide" -> {
                        result /= operand;
                        lineBuilder.append("/").append(operand);
                    }
                    default -> throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
                }
            }
        }
        var line = lineBuilder.append("=").append(result).substring(1);
        var res = new Result();
        res.setRes(line);
        return repository.save(res);
    }

    private boolean isOperator(String part) {
        return part.equals("plus") || part.equals("minus") || part.equals("multiply") || part.equals("divide");
    }

    public List<Result> getAll() {
        return repository.findAll();
    }

    public Optional<Result> getLastRecord() {
        return repository.findTopByOrderByIdDesc();
    }
}

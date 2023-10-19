package ru.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.app.model.JsonParam;
import ru.app.model.Result;
import ru.app.service.CalculationService;

import java.util.List;

@Tag(name = "Calculator", description = "calculator API")
@RestController
@RequestMapping("/api/calc")
@AllArgsConstructor
public class CalcController {
    private CalculationService calculationService;

    @Operation(summary = "Calculate with query params for example ?value=plus_20_20_minus_10")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "calculated"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "wrong params",
                    content = @Content(
                            schema = @Schema(
                                    implementation = IllegalArgumentException.class
                            )
                    )
            )


    })
    @PostMapping("/params")
    public ResponseEntity<Result> paramCalculate(@RequestParam("value") String params) {
        return ResponseEntity.ok(calculationService.calculateParams(params));
    }

    @Operation(summary = "Calculate with json for example - plus_20_20_minus_10")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "calculated"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "wrong parmas",
                    content = @Content(
                            schema = @Schema(
                                    implementation = IllegalArgumentException.class
                            )
                    )
            )
    })
    @PostMapping("/json")
    public ResponseEntity<Result> jsonCalculate(@RequestBody JsonParam body) {
        return ResponseEntity.ok(calculationService.calculateParams(body.getParams()));
    }

    @Operation(summary = "Get last result")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "last result"
            )
    })
    @GetMapping
    public ResponseEntity<Result> getLast() {
        return ResponseEntity.of(calculationService.getLastRecord());
    }

    @Operation(summary = "Get results history")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "all results"
            )
    })
    @GetMapping("/logs")
    public ResponseEntity<List<Result>> getAll() {
        return ResponseEntity.ok(calculationService.getAll());
    }
}

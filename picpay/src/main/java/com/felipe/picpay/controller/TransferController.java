package com.felipe.picpay.controller;

import com.felipe.picpay.model.Transfer;
import com.felipe.picpay.model.dto.TransferDTO;
import com.felipe.picpay.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @Operation(summary = "Transação", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A transação será bem sucedida"),
            @ApiResponse(responseCode = "400", description = "Ocorrerá erro de saldo insuficient "),
            @ApiResponse(responseCode = "400", description = "Usuário não existir")
    })
    @Cacheable("transfer")
    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDTO dto){
        var response = service.transfer(dto);

        return ResponseEntity.ok(response);
    }
}

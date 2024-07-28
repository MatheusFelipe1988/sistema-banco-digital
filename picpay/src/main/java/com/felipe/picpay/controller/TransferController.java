package com.felipe.picpay.controller;

import com.felipe.picpay.model.Transfeer;
import com.felipe.picpay.model.dto.TransferDTO;
import com.felipe.picpay.service.TransferService;
import jakarta.validation.Valid;
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

    @PostMapping("/transfer")
    public ResponseEntity<Transfeer> transfer(@RequestBody @Valid TransferDTO dto){
        var response = service.transfeer(dto);

        return ResponseEntity.ok(response);
    }
}

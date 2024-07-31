package com.felipe.picpay.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class EmailDTO{

    private UUID id;
    private String emailTo;
    private String subject;
    private String text;
}

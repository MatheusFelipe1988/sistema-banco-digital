package com.bank.mail.domain;


import java.util.UUID;

public record EmailDTO(UUID id, String emailTo, String subject, String text) {
}

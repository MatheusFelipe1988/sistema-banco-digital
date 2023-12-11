package com.system.bank.DTOs;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal valor, Long senderID, Long receiverID){


}

package com.system.bank.domain.transaction;

import com.system.bank.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "transacao")
@Entity(name = "transacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    private LocalDateTime timestamp;
}

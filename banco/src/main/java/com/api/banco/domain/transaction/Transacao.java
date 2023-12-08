package com.api.banco.domain.transaction;

import com.api.banco.domain.user.User;
import jakarta.persistence.*;;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transacao")
@Table(name = "transacao")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "join_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "join_id")
    private User receiver;
    private LocalDateTime timestamp;

}

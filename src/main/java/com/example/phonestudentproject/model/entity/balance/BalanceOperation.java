package com.example.phonestudentproject.model.entity.balance;

import com.example.phonestudentproject.model.entity.DefaultSystemAttributes;
import com.example.phonestudentproject.model.entity.balance.Balance;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BalanceOperation extends DefaultSystemAttributes {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @Column(name = "balance")
    private Balance balance;

    @Column(name = "timeOperation")
    private OffsetDateTime timeOperation;
}

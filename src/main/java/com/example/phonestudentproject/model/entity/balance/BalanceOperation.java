package com.example.phonestudentproject.model.entity.balance;

import com.example.phonestudentproject.model.entity.DefaultSystemAttributes;
import com.example.phonestudentproject.model.entity.balance.Balance;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

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
    @Column(name = "balance_operation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "balance_id")
    private Balance balance;

    @Column(name = "timeOperation")
    private OffsetDateTime timeOperation;
}

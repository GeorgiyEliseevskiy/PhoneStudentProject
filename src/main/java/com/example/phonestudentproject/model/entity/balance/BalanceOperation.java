package com.example.phonestudentproject.model.entity.balance;

import com.example.phonestudentproject.model.entity.DefaultSystemAttributes;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "balance_operations")
public class BalanceOperation extends DefaultSystemAttributes implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "balance_operation_seq_gen")
    @Column(name = "balance_operation_id", nullable = false)
    @SequenceGenerator(name = "balance_operation_seq_gen", sequenceName = "balance_operation_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "balance_id")
    private Balance balance;

    @Column(name = "operation_date")
    private String operationDate;
}

package com.example.phonestudentproject.model.entity.balance;

import com.example.phonestudentproject.model.entity.DefaultSystemAttributes;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "balance")
public class Balance extends DefaultSystemAttributes implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "balance_seq_gen")
    @SequenceGenerator(name = "balance_seq_gen", sequenceName = "balance_seq", allocationSize = 1)
    @Column(name = "balance_id", nullable = false)
    private Long id;

    @Column(name = "phone_id")
    private String phoneNumber;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @OneToMany(mappedBy = "balance", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<BalanceOperation> historyOperation;
}

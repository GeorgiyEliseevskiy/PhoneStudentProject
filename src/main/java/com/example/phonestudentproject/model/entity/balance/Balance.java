package com.example.phonestudentproject.model.entity.balance;

import com.example.phonestudentproject.model.entity.DefaultSystemAttributes;
import com.example.phonestudentproject.model.entity.Phone;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Balance extends DefaultSystemAttributes {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @Column(name = "phone")
    private Phone phone;

    @Column(name = "balance")
    private BigDecimal balance;

    /*@OneToMany
    private List<BalanceOperation> historyOperation;*/



}

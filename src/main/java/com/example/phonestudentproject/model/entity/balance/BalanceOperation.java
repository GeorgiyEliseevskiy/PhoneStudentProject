package com.example.phonestudentproject.model.entity.balance;

import com.example.phonestudentproject.model.entity.DefaultSystemAttributes;
import com.example.phonestudentproject.model.entity.balance.Balance;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BalanceOperation extends DefaultSystemAttributes {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Balance balance;

}

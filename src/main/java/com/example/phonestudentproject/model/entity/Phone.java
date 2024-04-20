package com.example.phonestudentproject.model.entity;

import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.Enum.RegionEnum;
import com.example.phonestudentproject.model.entity.balance.Balance;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "phone")
public class Phone extends DefaultSystemAttributes {

    @Id
    @Column(name = "phone_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_seq_gen")
    @SequenceGenerator(name = "phone_seq_gen", sequenceName = "phone_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$\n")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "balance_id")
    private Balance balance;

    @Column(name = "status")
    private PhoneStatusEnum status;

    @Column(name = "region")
    private RegionEnum region;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")*/
    @Column(name = "operator", nullable = false)
    private String operator;

}

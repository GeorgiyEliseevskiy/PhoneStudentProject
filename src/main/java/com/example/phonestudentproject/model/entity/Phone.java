package com.example.phonestudentproject.model.entity;

import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.Enum.RegionEnum;
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
public class Phone extends DefaultSystemAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$\n")
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @OneToOne
    @Column(name = "balance", nullable = false)
    private Balance balance;

    @Column(name = "probability")
    private String probability;

    @Column(name = "status")
    private PhoneStatusEnum status;

    @Column(name = "region")
    private RegionEnum region;

    //вне лабы
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")
    @Column(name = "operator", nullable = false)
    private Operator operator;

    @ElementCollection
    private List<String> logCalls = new ArrayList<>();

}

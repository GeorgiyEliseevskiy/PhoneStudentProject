package com.example.phonestudentproject.model.entity;

import com.example.phonestudentproject.model.Enum.PhoneStatusEnum;
import com.example.phonestudentproject.model.Enum.RegionEnum;
import com.example.phonestudentproject.model.entity.balance.Balance;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "phone")
@EntityListeners(AuditingEntityListener.class)
public class Phone extends DefaultSystemAttributes implements Serializable {

    @Id
    @Column(name = "phone_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_seq_gen")
    @SequenceGenerator(name = "phone_seq_gen", sequenceName = "phone_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_number", referencedColumnName = "phone_id", updatable = false, insertable = false)
    private Balance balance;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private PhoneStatusEnum status;

    @Column(name = "region")
    @Enumerated(value = EnumType.STRING)
    private RegionEnum region;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")*/
    @Column(name = "operator", nullable = false)
    private String operator;

}

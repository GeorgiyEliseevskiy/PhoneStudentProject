package com.example.phonestudentproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "operator")
public class Operator extends DefaultSystemAttributes{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operator_id")
    private Long id;

    @Column(name = "name")
    private String name;

}

package com.example.phonestudentproject.mapper;

public interface CommonMapper <E, D> {

    E toEntity(D var1);

    D toDto(E var2);
}

package ru.app.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Table(name = "results")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter@Setter
public class Result {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String res;
}

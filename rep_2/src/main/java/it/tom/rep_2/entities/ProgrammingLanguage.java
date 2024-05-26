package it.tom.rep_2.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class ProgrammingLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    private Date firstAppearance;
    @Column(nullable = false)
    private String inventor;
}

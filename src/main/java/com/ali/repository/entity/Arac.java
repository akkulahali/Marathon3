package com.ali.repository.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblarac")
public class Arac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String marka;
    String model;
    boolean musaitmi;
    @ManyToOne(cascade = CascadeType.ALL)
    Kiralama kiralamaArac;
}

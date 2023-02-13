package com.ali.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name ="tblkiralama")
public class Kiralama {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToMany(mappedBy = "kiralama")
    List<Arac> aracList;
}

package org.finalproject.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Entity
@Table(name = "Adresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@OptimisticLocking
public class Adress {
    @Id
    @Column(name = "adress_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Version
    long version;
    String country;
    String city;
    String street;
    String house;
}

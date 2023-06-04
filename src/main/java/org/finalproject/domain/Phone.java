package org.finalproject.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Entity
@Table(name = "phones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@OptimisticLocking
public class Phone {
    @Id
    @Column(name = "phone_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Version
    long version;
    String phone;
}

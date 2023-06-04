package org.finalproject.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Entity
@Table(name = "rubrics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@OptimisticLocking
public class Rubric {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rubric_id")
    int id;
    @Version
    long version;
    String name;
}

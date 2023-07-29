package org.finalproject.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
    int version;
    @Column(name = "rubric_name")
    @Size(min = 2, max = 40, message = "Rubric name must incorporates min 2 and max 40 characters")
    String name;
}

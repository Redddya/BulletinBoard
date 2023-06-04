package org.finalproject.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Entity
@Table(name = "Emails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@OptimisticLocking
public class Email {
    @Id
    @Column(name = "email_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Version
    long version;
    String email;
}

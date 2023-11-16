package org.finalproject.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OptimisticLocking;


@Entity
@Table(name = "Emails", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email_id"),
        @UniqueConstraint(columnNames = "email"),
})
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
    @jakarta.validation.constraints.Email
    String email;
}

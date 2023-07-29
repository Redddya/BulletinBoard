package org.finalproject.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import javax.validation.constraints.Size;

//        автор(телефоны(отдельная сущность), адрес(отдельная сущность),
//        имя, э/почта(отдельная сущность)). --Homework
@Entity
@Table(name = "Authors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@OptimisticLocking
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    int id;
    @Version
    long version;
    @Column(name = "author_name")
    @Size(min = 2, max = 40, message = "Name must incorporates min 2 and max 40 characters")
    String name;
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "FK_Author_Phone")
    Phone phone;
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "FK_Author_Address")
    Address address;
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "FK_Author_Email")
    @javax.validation.constraints.Email
    Email email;
}

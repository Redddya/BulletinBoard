package org.finalproject.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.util.List;

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
    String name;
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    @JoinColumn(name = "FK_Author_Phone")
    Phone phone;
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    @JoinColumn(name = "FK_Author_Adress")
    Adress adress;
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    @JoinColumn(name = "FK_Author_Email")
    Email email;
}

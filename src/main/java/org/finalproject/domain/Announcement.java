package org.finalproject.domain;


//- Объявление(название, дата публикации, текст объявления, стоимость услуги,
//        автор(телефоны(отдельная сущность), адрес(отдельная сущность),
//        имя, э/почта(отдельная сущность)). --Homework

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "announcements")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "announcement_id")
    int id;
    @Column(name = "announcement_name")
    String name;
    @Column(name = "publication_date")
    LocalDateTime publicationDate;
    @Column(name = "announcement_text")
    String text;
    BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "FK_Announcement_Author")
    Author author;
    @ManyToOne
    @JoinColumn(name = "FK_Announcement_Rubric")
    Rubric rubric;
}

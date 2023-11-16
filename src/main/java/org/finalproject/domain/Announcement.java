package org.finalproject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OptimisticLocking;

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
@OptimisticLocking
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "announcement_id")
    private int id;

    @Version
    long version;

    @Column(name = "announcement_name")
    @Size(min = 2, max = 40, message = "Name must incorporates min 2 and max 40 characters")
    @NotEmpty(message = "Name can't be empty")
    String name;

    @Column(name = "publication_date")
    LocalDateTime publicationDate;

    @Column(name = "announcement_text")
    @NotEmpty(message = "Description can't be empty")
    @Size(min = 10, max = 200, message = "Text must incorporates min 10 and max 200 characters")
    String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 8, fraction = 2)
    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "FK_Announcement_Author")
    Author author;

    @ManyToOne
    @JoinColumn(name = "FK_Announcement_Rubric")
    Rubric rubric;

    @NotNull(message = "Field isActive can't be null")
    @Column(name = "is_active")
    boolean isActive;
}


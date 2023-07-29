package org.finalproject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
@Entity
@Table(name = "matching_ad")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@OptimisticLocking
public class MatchingAd {
    @Id
    @Column(name = "matchingAd_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Version
    long version;
    @Size(min = 4, max = 25, message = "Title must incorporates min 4 and max 25 characters")
    String title;
   @Column(name = "price_from")
   @JsonFormat(shape = JsonFormat.Shape.STRING)
   @DecimalMin(value = "0.0", inclusive = false)
   @Digits(integer = 8, fraction = 2)
   BigDecimal priceFrom;
   @Column(name = "price_to")
   @JsonFormat(shape = JsonFormat.Shape.STRING)
   @DecimalMin(value = "0.0", inclusive = false)
   @Digits(integer = 8, fraction = 2)
   BigDecimal priceTo;
   @ManyToOne
   @JoinColumn(name = "FK_MatchingAd_Rubric")
   Rubric rubric;
   @ManyToOne
   @JoinColumn(name = "FK_MatchingAd_Author")
   Author author;
}

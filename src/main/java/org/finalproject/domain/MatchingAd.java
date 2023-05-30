package org.finalproject.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "matching_ad")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MatchingAd {
 //   После сохранения объявления необходимо найти все э/почты Авторов,
 //   у к-рых поля MatchingAd(s) совпадают с параметрами нового объявления
    @Id
    @Column(name = "matchingAd_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ManyToOne
    @JoinColumn(name = "FK_MatchingAd_Rubric")
    Rubric rubric;
    String title;
    @Column(name = "price_from")
    BigDecimal priceFrom;
    @Column(name = "price_to")
    BigDecimal priceTo;
    @ManyToOne
    @JoinColumn(name = "FK_MatchingAd_Author")
    Author author;
}

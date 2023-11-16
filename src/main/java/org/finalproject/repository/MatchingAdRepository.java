package org.finalproject.repository;

import org.finalproject.domain.MatchingAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public interface MatchingAdRepository extends JpaRepository<MatchingAd, Integer> {

    @Query("FROM MatchingAd ma " +
            "WHERE :an_pr BETWEEN ma.priceFrom AND ma.priceTo AND " +
            ":an_name LIKE CONCAT('%', ma.title, '%')")
    public List<MatchingAd> findAllByAdvertisementParams(@Param("an_name") String anName,
                                                         @Param("an_pr") BigDecimal price);
}

package org.finalproject.service;

import org.finalproject.domain.Announcement;
import org.finalproject.domain.MatchingAd;

import java.util.List;

public interface MatchingAdService extends CRUDService<MatchingAd> {
    public void sendMassagesByMatchingAds(Announcement announcement);
}

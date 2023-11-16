package org.finalproject.dao;

import org.finalproject.domain.Announcement;
import org.finalproject.domain.MatchingAd;

import java.util.List;

public interface MatchingAdDAO extends CRUDDAO<MatchingAd> {

    public List<MatchingAd> filter(Announcement announcement);
}

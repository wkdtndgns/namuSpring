package io.namoosori.travelclub.spring.service.logic;

import io.namoosori.travelclub.spring.TravelClubApp;
import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.service.ClubService;
import io.namoosori.travelclub.spring.service.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.store.ClubStore;
import io.namoosori.travelclub.spring.store.mapstore.ClubMapStore;
import io.namoosori.travelclub.spring.util.exception.NoSuchClubException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service("ClubService")
public class ClubServiceLogic implements ClubService {

  private ClubStore clubStore;

  public ClubServiceLogic(ClubMapStore clubMapStore) {
    this.clubStore = clubMapStore;
  }

  @Override
  public String registerClub(TravelClubCdo club) {
    TravelClub newClub = new TravelClub(club.getName(), club.getIntro());
    newClub.checkValidation();
    return clubStore.create(newClub);
  }

  @Override
  public TravelClub findClubById(String id) {
    return clubStore.retrieve(id);
  }

  @Override
  public List<TravelClub> findClubsByName(String name) {
    return clubStore.retrieveByName(name);
  }

  @Override
  public void modify(String clubId, NameValueList nameValues) {
    TravelClub travelClub = clubStore.retrieve(clubId);
    if (travelClub == null) {
      throw new NoSuchClubException("No" + clubId);
    }
    travelClub.modifyValues(nameValues);
    clubStore.update(travelClub);
  }

  @Override
  public void remove(String clubId) {
    if (!clubStore.exists(clubId)) {
      throw new NoSuchClubException("No " + clubId);
    }

    clubStore.delete(clubId);
  }
}

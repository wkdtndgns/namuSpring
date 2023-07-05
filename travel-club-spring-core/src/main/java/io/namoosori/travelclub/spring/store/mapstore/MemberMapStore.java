package io.namoosori.travelclub.spring.store.mapstore;

import io.namoosori.travelclub.spring.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.store.MemberStore;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.text.html.Option;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@Repository
public class MemberMapStore implements MemberStore {
  private Map<String,CommunityMember> memberMap;

  public MemberMapStore() {
    this.memberMap = new LinkedHashMap<>();
  }

  @Override
  public String create(CommunityMember member) {
    memberMap.put(member.getId(),member);
    return member.getId();
  }

  @Override
  public CommunityMember retrieve(String memberId) {
    return memberMap.get(memberId);
  }

  @Override
  public CommunityMember retrieveByEmail(String email) {

    CommunityMember communityMember = null;

    for (CommunityMember member : memberMap.values()) {
      if(member.getEmail().equals(email)){
        communityMember = member;
        break;
      }
    }
    return communityMember;
//    return memberMap.values().stream().filter(member -> member.getEmail().equals(email)).collect(Collectors.toList());
  }

  @Override
  public List<CommunityMember> retrieveByName(String name) {
    return memberMap.values().stream().filter(member -> member.getName().equals(name)).collect(Collectors.toList());
  }

  @Override
  public void update(CommunityMember member) {
    memberMap.put(member.getId(),member);
  }

  @Override
  public void delete(String memberId) {
  memberMap.remove(memberId);
  }

  @Override
  public boolean exists(String memberId) {
    return Optional.ofNullable(memberMap.get(memberId)).isPresent();
  }
}

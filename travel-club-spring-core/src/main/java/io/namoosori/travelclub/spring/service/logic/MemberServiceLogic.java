package io.namoosori.travelclub.spring.service.logic;

import io.namoosori.travelclub.spring.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.service.MemberService;
import io.namoosori.travelclub.spring.service.sdo.MemberCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.store.MemberStore;
import io.namoosori.travelclub.spring.util.exception.NoSuchMemberException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceLogic implements MemberService {

  private MemberStore memberStore;

  public MemberServiceLogic(MemberStore memberStore) {
    this.memberStore = memberStore;
  }

  @Override
  public String registerMember(MemberCdo member) {

    String email = member.getEmail();
    CommunityMember foundMember = memberStore.retrieveByEmail(email);

    foundMember = new CommunityMember(member.getEmail(), member.getName(), member.getPhoneNumber());
    foundMember.setNickName(member.getNickName());
    foundMember.setBirthDay(member.getBirthDay());

    foundMember.checkValidation();

    memberStore.create(foundMember);
    return foundMember.getId();
  }

  @Override
  public CommunityMember findMemberById(String memberId) {
    return memberStore.retrieve(memberId);
  }

  @Override
  public CommunityMember findMemberByEmail(String memberEmail) {
    return memberStore.retrieveByEmail(memberEmail);
  }

  @Override
  public List<CommunityMember> findMembersByName(String name) {
    return memberStore.retrieveByName(name);
  }

  @Override
  public void modifyMember(String memberId, NameValueList nameValueList) {
    CommunityMember communityMember = memberStore.retrieve(memberId);
    if (communityMember == null) {
      throw new NoSuchMemberException("id" + memberId);
    }

    communityMember.modifyValues(nameValueList);
    memberStore.update(communityMember);
  }

  @Override
  public void removeMember(String memberId) {
    if (!memberStore.exists(memberId)) {
      throw new NoSuchMemberException("id" + memberId);
    }
    memberStore.delete(memberId);
  }
}

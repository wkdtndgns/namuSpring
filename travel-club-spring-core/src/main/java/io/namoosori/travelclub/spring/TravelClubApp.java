package io.namoosori.travelclub.spring;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.service.ClubService;
import io.namoosori.travelclub.spring.service.sdo.TravelClubCdo;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TravelClubApp {

  public static void main(String[] args){
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    String[] beanNames = context.getBeanDefinitionNames();
    System.out.println(Arrays.toString(beanNames));

//    TravelClubCdo travelClubCdo = new TravelClubCdo("travelClub","travle test club");
//    ClubService clubService =context.getBean("ClubService", ClubService.class);
//    String clubId = clubService.registerClub(travelClubCdo);
//    System.out.println("id:   " + clubId);
//
//    TravelClub foundClub= clubService.findClubById(clubId);
//    System.out.println("name : " + foundClub.getName());
//    System.out.println("intro : " + foundClub.getIntro());
//    System.out.println("foundationTime : " + new Date(foundClubb.getFoundationTime()));



  }
}

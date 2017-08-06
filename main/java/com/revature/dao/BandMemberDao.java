package main.java.com.revature.dao;


import main.java.com.revature.domain.BandMember;

public interface BandMemberDao
{
    int createBandMember(BandMember bm);

    BandMember getBandMemberById(int id);
}

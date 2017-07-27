package com.revature.dao;


import com.revature.domain.BandMember;

public interface BandMemberDao
{
    int createBandMember(BandMember bm);

    BandMember getBandMemberById(int id);
}

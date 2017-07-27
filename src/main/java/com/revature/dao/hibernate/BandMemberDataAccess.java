package com.revature.dao.hibernate;


import com.revature.dao.BandMemberDao;
import com.revature.domain.BandMember;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;

//TODO Finish implementing and add wrapper class
public class BandMemberDataAccess implements BandMemberDao
{
    private Session session = HibernateUtil.getSession();

    @Override
    public int createBandMember(BandMember bm)
    {
        int id = (int) session.save(bm);
        session.close();

        return id;
    }

    @Override
    public BandMember getBandMemberById(int id)
    {
        BandMember bandMember = (BandMember) session.get(BandMember.class, id);
        session.close();

        return bandMember;
    }
}

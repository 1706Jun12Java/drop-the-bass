package main.java.com.revature.dao.hibernate;


import main.java.com.revature.dao.BandMemberDao;
import main.java.com.revature.domain.BandMember;
import main.java.com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
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

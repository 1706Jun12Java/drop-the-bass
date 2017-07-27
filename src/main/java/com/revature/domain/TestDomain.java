package com.revature.domain;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.util.HibernateUtil;

public class TestDomain {
//Test class for  seeing if the table structures are actually doing what they should
	//we should write some Test files for this stuff as well
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		VenueOwner vo = new VenueOwner();
		/*vo.setFirstName("bob");
		vo.setLastName("bob");
		vo.setUsername("bob");
		vo.setPassword("bob");
		vo.setPhoneNumber("999999999999");
		vo.setAccountType(0);
		sess.save(vo);*/
		Artist a = new Artist();
		a.setUsername("bill");
		a.setPassword("bill");
		a.setGenre("Rock");
		a.setWebsite("DankMeme.com");
		a.setAccountType(1);
		sess.save(a);
		tx.commit();
		sess.close();
		System.exit(0);
	}

}
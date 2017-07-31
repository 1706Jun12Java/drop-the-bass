package main.java.com.revature.domain;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "BAND_MEMBER")
public class BandMember implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BAND_ID_Seq")
	@SequenceGenerator(allocationSize = 1, name = "BAND_ID_Seq", sequenceName = "BAND_ID_Seq")
	@Column(name = "BAND_ID")
	private long id;
	@Column(name = "FIRSTNAME")
	private String firstName;
	@Column(name = "LASTNAME")
	private String lastName;
	@Column(name = "PHONE")
	private String phoneNumber;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Artist_ID")
	private Artist artist;
}

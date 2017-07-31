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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getFirstName() {

		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {

		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "LASTNAME")

	private String lastName;
	@Column(name = "PHONE")
	private String phoneNumber;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Artist_ID")
	private Artist artist;
}

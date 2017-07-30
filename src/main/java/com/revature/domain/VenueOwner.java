package main.java.com.revature.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name= "VenueOwner")
@PrimaryKeyJoinColumn(name = "OWNER_ID", referencedColumnName = "USER_ID")
public class VenueOwner extends User implements Serializable{
	@Column(name = "FIRSTNAME")
	private String firstName;
	@Column(name = "LASTNAME")
	private String lastName;
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	@OneToMany(mappedBy="id",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Venue> venues;
	@OneToMany(mappedBy="id",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Event> events;
	
	
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<Venue> getVenues() {
		return venues;
	}
	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
}

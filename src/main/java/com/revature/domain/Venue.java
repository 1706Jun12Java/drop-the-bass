package main.java.com.revature.domain;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Indexed
@Entity
@Table(name = "Venue")
public class Venue implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VenueIDSeq")
	@SequenceGenerator(allocationSize = 1, name = "VenueIDSeq", sequenceName = "VenueIDSeq")
	@Column(name = "VenueID")
	private long id;

	@Field
	@Column(name = "ADDRESS")
	private String address;

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	@Column(name = "PIC_URL")
	private String pictureURL;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "OWNED_BY")
	private VenueOwner venueOwner;

	@Field
	@Column(name = "VENUE_NAME")
	private String name;
	@Column(name = "WEBSITE")
	private String website;

	@IndexedEmbedded
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="venues")
	//@JoinTable(name = "VENUE_EVENT", joinColumns = { @JoinColumn(name = "VenueID") }, inverseJoinColumns = { @JoinColumn(name = "EventID") })
	private List<Event> events;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public VenueOwner getVenueOwner() {
		return venueOwner;
	}
	public void setVenueOwner(VenueOwner venueOwner) {
		this.venueOwner = venueOwner;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
}

package com.revature.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name="Event_TABLE")
public class Event implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EventIDSeq")
	@SequenceGenerator(allocationSize = 1, name = "EventIDSeq", sequenceName = "EventIDSeq")
	@Column(name = "EventID")
	private long id;
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Venue> venues;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "EVENT_START")
	private Timestamp start;
	@Column(name = "EVENT_END")
	private Timestamp end;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "CREATED_BY")
	private VenueOwner venueOwner;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public List<Venue> getVenues() {
		return venues;
	}
	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public VenueOwner getVenueOwner() {
		return venueOwner;
	}

	public void setVenueOwner(VenueOwner venueOwner) {
		this.venueOwner = venueOwner;
	}
}

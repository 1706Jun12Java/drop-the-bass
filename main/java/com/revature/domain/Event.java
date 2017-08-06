package main.java.com.revature.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
@Entity
@Table(name="Event_TABLE")
public class Event implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EventIDSeq")
	@SequenceGenerator(allocationSize = 1, name = "EventIDSeq", sequenceName = "EventIDSeq")
	@Column(name = "EventID")
	private int id;
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Venue> venues;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;


	@Column(name = "EVENT_START")
	private Timestamp start;
	@Column(name = "EVENT_END")
	private Timestamp end;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name ="CREATED_BY")
	private VenueOwner venueOwner;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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

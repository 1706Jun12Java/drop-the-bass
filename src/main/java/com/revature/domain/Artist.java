package com.revature.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ARTIST")
@PrimaryKeyJoinColumn(name = "OWNER_ID", referencedColumnName = "USER_ID")
public class Artist extends User implements Serializable{
	@Column(name = "WEBSITE")
	private String website;
	@Column(name = "GENRE")
	private String genre;
	@Column(name = "SOUND_URL",nullable = true)
	private String soundCloudURL;
	@OneToMany(mappedBy="id",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<BandMember> members;
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getSoundCloudURL() {
		return soundCloudURL;
	}
	public void setSoundCloudURL(String soundCloudURL) {
		this.soundCloudURL = soundCloudURL;
	}
	public List<BandMember> getMembers() {
		return members;
	}
	public void setMembers(List<BandMember> members) {
		this.members = members;
	}
	
}

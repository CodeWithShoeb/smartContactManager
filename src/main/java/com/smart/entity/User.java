package com.smart.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "MYUSER")
public class User {

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    
	@NotBlank(message="User name can't be ampty !! !")
	@Size(min=3, max=20,message="User name must be between 3 - 20 characters !! !")
	private String name;

	@Column(length = 500) // length of this column's as letters.
	@Size(min=5, max=500 , message="About must be contain between 5 - 500 characters !! !")
	private String about;

	//@Email(regexp="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$")
	private String password;
	
	
	private String role;
	
	
	private boolean enabled;
	
	public boolean isAgreement() {
		return agreement;
	}

	public void setAgreement(boolean agreement) {
		this.agreement = agreement;
	}

	private String imageUrl;
	
	@AssertTrue(message="Must be enabled !! !")
	private boolean agreement;

	@Column(unique = true) // for making 'email' column as unique.
	@Email(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	
	
	
//   mappedBy = "user" == mapped with foreign column and no create another table for this,means here one more table will be created as 'adminuser_list' for list of contact that is waste so we can remove with a syntax as mappedBy="columName" inside 'cascade',
	// fetch = FetchType.LAZY==when find out user and when need contacts of that
	// user then after calling we will get it and not during finding out user so
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user") // [cascade = CascadeType.ALL]== when you save user then all contact saved or delete a user then all															// delete.
	List<Contact> contacts = new ArrayList<>();// for adding more user's contact

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public User() {
		super();
		
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", about=" + about + ", password=" + password + ", role=" + role
				+ ", enabled=" + enabled + ", imageUrl=" + imageUrl + ", agreement=" + agreement + ", email=" + email
				+ ", contacts=" + contacts + "]";
	}

	
	
	

}

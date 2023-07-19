package it.guitarhub.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String firstName;
    private String lastName;
	private String gender;
    private String username;
    private LocalDate birthday;
    private Address address;
	private String email;
	private String password;
	private String role;
	private String cardNumber;
	private LocalDate cardExpDate;
	private String cvv;
	private String billingAddress;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
        return this.firstName;
  }

  public void setFirstName(String firstName) {
        this.firstName = firstName;
  }

  public String getLastName() {
        return this.lastName;
  }

  public void setLastName(String lastName) {
        this.lastName = lastName;
  }

  public String getGender() {
        return this.gender;
  }

  public void setGender(String genderIn) {
        this.gender = genderIn;
  }

  public LocalDate getBirthday() {
        return this.birthday;
  }

  public void setBirthday(LocalDate birthdayIn) {
        this.birthday = birthdayIn;
  }
  
  public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
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

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public LocalDate getCardExpDate() {
		return cardExpDate;
	}


	public void setCardExpDate(LocalDate cardExpDate) {
		this.cardExpDate = cardExpDate;
	}


	public String getCvv() {
		return cvv;
	}


	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	public String getBillingAddress() {
		return billingAddress;
	}


	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", birthday=" + birthday + ", addresses=" + ", email="
				+ email + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}

	

}


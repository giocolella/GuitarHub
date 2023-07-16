package it.guitarhub.beans;

public class Address {
	private int id;
	private int uid;
	private String firstName;
	private String lastName;
	private String address;
	private String postalCode;
	private String city;
	private String province;
	private String phone;
	private boolean preferred;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isPreferred() {
		return preferred;
	}
	public void setPreferred(boolean preferred) {
		this.preferred = preferred;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format(
				"%s %s %s %s %s (%s), phone: %s", 
				this.getFirstName(), this.getLastName(), this.getAddress(),
				this.getPostalCode(), this.getCity(), this.getProvince(),
				this.getPhone()
		);
	}
	
}
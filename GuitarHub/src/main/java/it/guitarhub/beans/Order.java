package it.guitarhub.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Order implements Cloneable, Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String destination;
	private int totalProducts;
	private double totalPaid;
	private String trackNumber;
	private Collection<OrderItem> items;
	private User user;
	private Date createdAt;
	
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public String getTotalPaidString() {
		return String.format("%.2f", totalPaid);
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Collection<OrderItem> getItems() {
		return new ArrayList<OrderItem>(items);
	}

	public void setItems(Collection<OrderItem> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDestination() {
		return destination.replace(",", "\n");

	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(int totalProducts) {
		this.totalProducts = totalProducts;
	}

	public double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	public void addItem(OrderItem bean) {
		if (this.items == null)
			this.items = new ArrayList<>();
		this.items.add(bean);
	}

}

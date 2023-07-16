package it.guitarhub.beans;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Order {
	private int id;
	private String destination;
	private int totalProducts;
	private double totalPaid;
	private String status;
	private String trackNumber;
	private Collection<OrderItem> items;
	private User user;
	private LocalDateTime createdAt;
	
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public Date getCreateDate() {
		return Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant());
	}

	public String getTotalPaidString() {
		return String.format("%.2f", totalPaid);
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void addItem(OrderItem bean) {
		if (this.items == null)
			this.items = new ArrayList<>();
		this.items.add(bean);
	}

}

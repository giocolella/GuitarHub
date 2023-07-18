package it.guitarhub.beans;

import java.io.Serializable;
import java.util.Collection;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	public static int NO_ITEM = -1;
	
	private int id;
	private String name; 
	private String description;
	private String shortDescription;
	private String imagePath;
	private double price;
	private String brand;
	private double discount;
	private double weight;
	private int tax;
	private int quantity;
	private Collection<Review> reviewes;
	
	
	
	private boolean available;
	
	public Product(int id, String name, String description,String shortDescription, 
			double weight, double price, double discount, int quantity,
			boolean available) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.shortDescription = shortDescription;
		this.weight = weight;
		this.price = price;
		this.discount = discount;
		this.quantity = quantity;
		this.available = available;
		
	}
	
	public Product(String name, String description, String shortDescription,
			double weight, double price, double discount, int quantity, int onSale, boolean available) {
		this(0, name, description,shortDescription, weight, price, discount, quantity,available);
		
	}

	public Product() {
		this.id = NO_ITEM;
	}
	
	public Product(int id) {
		this.id = id;
	}
	
	static boolean isProductInizialized(Product b) {
		return b.getId() != NO_ITEM;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getShortdescription() {
		return shortDescription;
	}

	public void setShortdescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public String getPriceToString() {
		return String.format("%.2f", price);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Override
	public String toString() {
		return "ProductBean [id=" + id + ", name=" + name + ", description=" + description +
				", weight=" + weight + ", price=" + price + ", marca=" + brand + ", discount=" + discount + ", quantity=" + quantity
				+ ", available=" + available + "]";
	}

	
	public Collection<Review> getReviewes() {
		return reviewes;
	}

	public void setReviewes(Collection<Review> reviewes) {
		this.reviewes = reviewes;
	}

}


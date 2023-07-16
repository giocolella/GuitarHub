package it.guitarhub.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	public static int NO_ITEM = -1;

	static boolean isProductInizialized(Product b) {
		return b.getId() != NO_ITEM;
	}
	
	private int id;
	private String name; 
	private String description;  
	private String imagePath;
	private double price;
	private String brand;
	private double discount;
	private double weight;
	private int tax;
	private int quantity;
	private Collection<Category> categories ;
	private Collection<Review> reviewes;
	
	
	
	private boolean available;
	
	public Product(int id, String name, String description, 
			double weight, double price, double discount, int quantity,
			boolean available) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.weight = weight;
		this.price = price;
		this.discount = discount;
		this.quantity = quantity;
		this.available = available;
		
	}
	
	public Product(String name, String description, String shortDescription, String metaDescription, String metaKeyword,
			double weight, double price, double discount, int quantity, int onSale, boolean available) {
		this(0, name, description, weight, price, discount, quantity,available);
		
	}

	public Product() {
		this.id = NO_ITEM;
	}
	
	public Product(int id) {
		this.id = id;
	}

	public void addCategory(Category category) {
		if(categories == null ) categories = new ArrayList<Category>();
		if(!categories.contains(category))
			categories.add(category);
	}
	
	public Collection<Category> getCategories() {
		if(categories == null ) categories = new ArrayList<Category>(); 
		return categories;
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

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (available ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		long temp;
		temp = Double.doubleToLongBits(discount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (available != other.available)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(discount) != Double.doubleToLongBits(other.discount))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	public Collection<Review> getReviewes() {
		return reviewes;
	}

	public void setReviewes(Collection<Review> reviewes) {
		this.reviewes = reviewes;
	}

}


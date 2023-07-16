package it.guitarhub.beans;

public class CartProduct {
	private static final long serialVersionUID = 1L;
	int quantity;
	private Product product;

	public CartProduct(Product product) {
		super();
		this.setQuantity(0);
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void addProd() {
		this.setQuantity(this.getQuantity() + 1);
	}

	public void delProd() {
		this.setQuantity(this.getQuantity() - 1);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof CartProduct)) {
			return false;
		}
		CartProduct prod = (CartProduct) obj;
		return prod.getId() == this.getId();
	}

	public int getId() {
		return this.product.getId();
	}
 
	public String getTotalPriceToString() {  
		return String.format("%.0f", getTotalPrice())+"%";
	}

	public double getTotalPrice() {
		return (double) this.product.getPrice() *this.getQuantity();
	}

}


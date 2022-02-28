package shopping;

public class PRODUCT implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public PRODUCT() {
		name = "";
		price = 0.0;
		quantity = 0;
	}

	private String name;
	private Double price;
	private Integer quantity;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}



	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "BOOK [name=" + name + "  , price=" + price
				+ ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		PRODUCT other = (PRODUCT) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
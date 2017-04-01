/******************************************************************************
 * <pre>
 * File:Product.java
 * 
 * The Product class manages the products attributes for each type of product.
 * To be accessed by the RetailTransacition class for each
 * transaction.
 * </pre>
 * @author Nicol Salgado
 *
 *****************************************************************************/
public class Product {
	double price;
	String productName;
	boolean warrantyEligible;
	String brand;
	String productId;

	public String getproductId(){
		return productId;
	}
	public void setproductId(String productId){
		this.productId = productId;
	}
	public double getPrice(){
		return price;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public String getProductName(){
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public boolean isWarrantyEligible(){
		return warrantyEligible;
	}
	public void setWarrantyEligible(boolean warrantyEligible) {
		this.warrantyEligible = warrantyEligible;
	}
	public String getBrand(){
		return brand;
	}
	public void setBrand(String brand){
		this.brand = brand;
	}

}

/******************************************************************************
 * <pre>
 * File:LineItem.java
 * 
 * The LineItem makes accessible the items purchased attributes for the 
 * RetailTransaction class to use.
 * 
 * </pre>
 * @author Nicol Salgado
 *****************************************************************************/
 public class LineItem {
	Product product;
	int quantity;
	int numberOfWarranties;
	
	public Product getProduct(){
		return product;
	}
	public void setProduct(Product product){
		this.product = product;
	}
	public int getQuantity(){
		return quantity;
	}
	public void setQuantity(int numquantity){
		this.quantity = numquantity;
	}
	public int getNumberOfWarranties(){
		return numberOfWarranties;
	}
	public void setNumberOfWarranties(int numberOfWarranties){
		this.numberOfWarranties = numberOfWarranties;
	}
	
}

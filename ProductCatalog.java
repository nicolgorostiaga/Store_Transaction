import java.util.ArrayList;
/******************************************************************************
 * <pre>
 * File:ProductCatalog.java
 * 
 * The ProductCatalog simulates a catalog of Products sold.
 * </pre>
 * @author Nicol Salgado
 *
 *****************************************************************************/
public class ProductCatalog {
	
	private ArrayList<Product> products = new ArrayList<Product>();
	
	/**************************************************************************
	 * Adds the product information to the list of products in the catalog.
	 * @param product product object
	 *************************************************************************/
	public void addProduct(Product product){
			products.add(product);
	}
	/**************************************************************************
	 * <pre>
	 * The method compares if the transaction productId 
	 * matches the productId in the product catalog.
	 * </pre>
	 * @param 	productId the productId from each transaction.
	 * @return  returns the product information if the productId
	 *  matches the productId within the product catalog.
	 * Otherwise, returns null.
	 *************************************************************************/
	public Product findProductById(String productId){
		Product p = null;
		
		for(int i = 0; i < products.size();i++){
			p =(Product)products.get(i);
			if(productId.equals(p.getproductId())){
				return p;
			}
		}
		return null;
	}
}

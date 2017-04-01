import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
/******************************************************************************
 * <pre>
 * File:RetailTransaction.java
 * 
 * Simulates a cash register transaction.
 * 
 * </pre>
 * @author Nicol Salgado
 *
 *****************************************************************************/
public class RetailTransaction {
	
	private final double TAX = 1.0725;
	private final int NEGATIVE_ONE = -1;
	private final int ONE = 1;
	private final double TEN_PERCENT = 0.1;
	
	private long transactionId;
	private int registerId;
	private TransactionTypeEnum transactionType;
	private String transactionDate;
	private double subTotal = 0;
	private int numwarranty;
	private int numquantity;
	private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
	/**************************************************************************
	 * Creates a new RetailTransaction with the given transaction information.
	 * 
	 * @param transactionId    the transaction number.
	 * @param registerId       the register number.   
	 * @param transactionDate  the date of the transaction.
	 * @param transactionType  the type of transaction.
	 *************************************************************************/
	public RetailTransaction(long transactionId,int registerId,
			String transactionDate, TransactionTypeEnum transactionType){
		this.transactionId = transactionId; 
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.registerId = registerId;
	}
	public long gettransactionId(){
		return transactionId;
	}
	public int getregisterId(){
		return registerId;
	}
	public TransactionTypeEnum gettransactionType(){
		return transactionType;
	}
	public String gettransactionDate(){
		return transactionDate;
	}
	/**************************************************************************
	 * Formats the amount display in receipt, summary transaction and
	 * console output.
	 * @param salesTotal the total amount for all transactions.
	 * @return DecimalFormat This returns a DecimalFormat object.
	 *************************************************************************/
	public static String getTotalAsFormattedString(double salesTotal){
		DecimalFormat dollarFormatter = new DecimalFormat("$#,##0.00");
		dollarFormatter.setNegativePrefix("($");
		dollarFormatter.setNegativeSuffix(")");
		return dollarFormatter.format(salesTotal);
	}
	public void setquantity(int quantity){
		this.numquantity = quantity;
	}
	public void setwarranty(int warranty){
		this.numwarranty = warranty;
	}
	/**************************************************************************
	 * Adds the product information to the transaction list of LineItems
	 * @param itemScanned product information for each transaction.
	 *************************************************************************/
	public void scanItem(Product itemScanned){
		LineItem items = new LineItem();
		items.setProduct(itemScanned);
		items.setQuantity(numquantity);
		items.setNumberOfWarranties(numwarranty);
		
		boolean found = true;
		if(itemScanned.getproductId() == null)
			return;
		for(int i = 0; i < lineItems.size(); i++){
			LineItem item = (LineItem)lineItems.get(i);
			if(item.getProduct().getproductId() == itemScanned.getproductId()){
				item.setQuantity(item.getQuantity()+ numquantity);
				item.setNumberOfWarranties(item.getNumberOfWarranties() + numwarranty);
				found = false;
			}
		}
		if(found)
			lineItems.add(items);
	}
	/**************************************************************************
	 * Calculates the total amount of each transactions.
	 * @return double this returns the total after checking if the 
	 * subTotal is a return or sale.
	 *************************************************************************/
	public double calculateTransactionTotal(){
		TransactionTypeEnum enumNumber = transactionType;
		return (subTotal < 0)? subTotal *TAX : subTotal * TAX*
			(enumNumber.equals(TransactionTypeEnum.RETURN)? NEGATIVE_ONE: ONE);
	}
	/**************************************************************************
	 * Sums all the singleSubtotal.
	 * @return double this returns the subTotal of each transaction, 
	 * after checking if the transaction is a return or sale.
	 *************************************************************************/
	public double calculateSubtotal(){
		TransactionTypeEnum enumNumber = transactionType;
		
		for(int i = 0; i < lineItems.size(); i++){
			LineItem p = (LineItem)lineItems.get(i);
			double price = p.getProduct().getPrice();
			subTotal += price * p.getQuantity() + p.getNumberOfWarranties()*price * TEN_PERCENT;
			subTotal *= (enumNumber.equals(TransactionTypeEnum.RETURN)? NEGATIVE_ONE : ONE);
		}
		return subTotal;
	}
	/**************************************************************************
	 * Calculates a singleSubtotal for each product purchased.
	 * @param lineItem passes the product transaction information.
	 * @return double a singleSubtotal for each product purchased.
	 *************************************************************************/
	public double calculateSingleSubtotal(LineItem lineItem){
		double singleSubtotal;
		double price = lineItem.getProduct().getPrice();
		singleSubtotal = price * lineItem.getQuantity() + lineItem.getNumberOfWarranties()*price*TEN_PERCENT;
		return singleSubtotal;
	}
	/**************************************************************************
	 * Calculates the total number of items purchased.
	 * @return int total amount of items purchased.
	 *************************************************************************/
	public int getNumberOfProductsSold(){
		int numberOfItems = 0;
		for(int i = 0; i < lineItems.size(); i++){
			LineItem p = (LineItem)lineItems.get(i);
			numberOfItems+=p.getQuantity();
		}
		return numberOfItems;
	}
	/**************************************************************************
	 * Generates a receipt for each cash register transaction.
	 * @throws FileNotFoundException this displays an error if the 
	 * file is not found.
	 *************************************************************************/
	public void generateReceipt() throws FileNotFoundException{
		String text = "receipt_"+ transactionId + "_"+ registerId +".txt";
		PrintWriter file = new PrintWriter(text);
		double subTotal = calculateSubtotal();
		
		StringBuilder s = new StringBuilder();
			s.append(transactionType);
			s.append(" Transaction: ");
			s.append(transactionId);
			s.append("\nRegister: ");
			s.append(registerId);
			s.append("\nTransaction Date: ");
			s.append(transactionDate);
			s.append("\n----------------------------------------\n");

		for(int i = 0; i < lineItems.size();i++){
			LineItem item = (LineItem)lineItems.get(i);
			s.append(item.getProduct().getProductName());
			s.append("\n\tquantity: " + item.getQuantity());
			s.append("\n\tprice: " + getTotalAsFormattedString(item.getProduct().getPrice()));
			s.append("\n\twarranties: "+ item.getNumberOfWarranties());
			s.append("\n\tsubtotal: " + getTotalAsFormattedString(calculateSingleSubtotal(item)));
			s.append("\n");
		}
			s.append("========================================");
			s.append("\nSubtotal: " + getTotalAsFormattedString(subTotal));
			s.append("\nTax: " + getTotalAsFormattedString((calculateTransactionTotal() - subTotal)));
			s.append("\nTOTAL: " + getTotalAsFormattedString(calculateTransactionTotal()));
			s.append("\n");
			
		file.print(s);
		file.close();
	}
}

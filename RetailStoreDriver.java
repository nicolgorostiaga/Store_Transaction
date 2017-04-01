import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


/******************************************************************************
 * <pre> 
 * File: RetailStoreDriver.java
 * 
 * The RetailStoreDriver program implements an application
 * to simulates several customers store transaction.
 * 
 * </pre>
 * @author  Nicol Salgado
 * 				
 *****************************************************************************/
public class RetailStoreDriver {
	private final static String CELL_PHONE_STRING = "cell phone";
	private final static String HEADPHONES_STRING = "headphones";
	private final static String TRANSACTION_STRING ="transaction";
	/**************************************************************************
	 * <pre>
	 * Distributes the transactions and items purchased information 
	 * to the RetailTransaction class.
	 * </pre>
	 * @param  transactionScanner access the text file.
	 * @param  transactionFields information of each transaction.
	 * @param  catalog 
	 * @return transaction This returns a transaction object.
	 *************************************************************************/
	private static RetailTransaction readTransaction(Scanner transactionScanner,
								String[] transactionFields, ProductCatalog catalog){
		RetailTransaction transaction =  RetailTransactionParser.getTransactionFromFields(transactionFields);
		boolean doneReadingTransaction = false;
		
		while (!doneReadingTransaction)
		{
			String line = transactionScanner.nextLine();
			String[] productFields = line.split("\\,");
			if("End Transaction".equalsIgnoreCase(line))
				doneReadingTransaction = true;
			else
			{
				
				transaction.setquantity(Integer.parseInt(productFields[1]));
				transaction.setwarranty(Integer.parseInt(productFields[2]));
				transaction.scanItem(catalog.findProductById(productFields[0]));
			}
		}
		return transaction;
	}
	/**************************************************************************
	 * <pre>
	 * The main method separates the product information between the
	 * productCatalog which stores the products sold at the store and 
	 * the transaction of the items sold.
	 * After each transaction, the method will print a receipt.
	 * After all transaction, the method will print a summary of
	 * each transaction and display it on the screen.
	 * </pre>
	 * @param  args unused.
	 * @throws FileNotFoundException throws an error if the file is not found.
	 *************************************************************************/
	public static void main (String[] args) throws FileNotFoundException{
		File transactionsFile = new File("retail_store.txt");
		Scanner fileScanner = null; 
		PrintWriter transactionOutput = new PrintWriter("transaction_summary.txt");
		CellPhone cellphone = new CellPhone();
		HeadPhones headphones = new HeadPhones();
		ProductCatalog catalog = new ProductCatalog();
		double totalSales = 0;
			
		try{
			fileScanner = new Scanner(transactionsFile);
			String line;
			String[] lineFields;
				
			while(fileScanner.hasNext()){
				line = fileScanner.nextLine();
				lineFields = line.split("\\,");
					
				switch(lineFields[0].toLowerCase()){
					case CELL_PHONE_STRING:
						catalog.addProduct(RetailTransactionParser.getCellPhoneFromFields(lineFields));
						cellphone.setCarrier(lineFields[6]);
						break;
					case HEADPHONES_STRING:
						catalog.addProduct(RetailTransactionParser.getHeadphonesFromFields(lineFields));
						headphones.setHasBluetoothFlag(Boolean.parseBoolean(lineFields[6]));
						break;
					case TRANSACTION_STRING:
						RetailTransaction currentTransaction = readTransaction(fileScanner, lineFields, catalog);
						currentTransaction.generateReceipt();
						
						String transactionLine = String.format("Transaction %s: %s items totaling %s on register %s",
								currentTransaction.gettransactionId(),
								currentTransaction.getNumberOfProductsSold(),
								RetailTransaction.getTotalAsFormattedString(currentTransaction.calculateTransactionTotal()),
								currentTransaction.getregisterId());
						
						totalSales += currentTransaction.calculateTransactionTotal();
						transactionOutput.println(transactionLine);
						System.out.println(transactionLine);
					default:
							break;
					}
				}
			String summaryOutput = String.format("Total Sales: %s", RetailTransaction.getTotalAsFormattedString(totalSales));
			transactionOutput.println(summaryOutput);
			System.out.println(summaryOutput);
			} catch(FileNotFoundException file){
				System.exit(1);
			}finally{
				if(fileScanner != null){
					fileScanner.close();
				}
				if(transactionOutput != null){
					transactionOutput.close();
				}
		}
	}//end main
}//end class

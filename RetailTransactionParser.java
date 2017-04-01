/******************************************************************************
 * <pre> 
 * File:RetailTransactionParser.java
 * 
 * The RetailTransactionParser distributes each product and transaction
 * information to the appropriate location.
 * 
 * </pre>
 * @author Nicol Salgado
 *
 *****************************************************************************/
public class RetailTransactionParser {
	/**************************************************************************
	 * <pre>
	 * Distributes the cellphone attributes to the product setters since the 
	 * CellPhone class inherits from the Product class.
	 * </pre>
	 * @param   cellPhoneFields a string array of the cellPhone attributes.
	 * @return  CellPhone This returns a cellphone object.
	 *************************************************************************/
	public static Product getCellPhoneFromFields(String[] cellPhoneFields){
		CellPhone cellphone = new CellPhone();
		cellphone.setproductId(cellPhoneFields[1].trim());
		cellphone.setPrice(Double.parseDouble(cellPhoneFields[2]));
		cellphone.setProductName(cellPhoneFields[3]);
		cellphone.setWarrantyEligible(Boolean.parseBoolean(cellPhoneFields[4]));
		cellphone.setBrand(cellPhoneFields[5]);
		return cellphone;
		
	}
	/**************************************************************************
	 * <pre>
	 * Distributes the headphone attributes to the product setters since the 
	 * CellPhone class inherits from the Product class.
	 * </pre>
	 * @param 	headphonesFields a string array of headphone attributes.
	 * @return  HeadPhones This returns a headphone object.
	 *************************************************************************/
	public static Product getHeadphonesFromFields(String[] headphonesFields){
		HeadPhones headphone = new HeadPhones();
		headphone.setproductId(headphonesFields[1].trim());
		headphone.setPrice(Double.parseDouble(headphonesFields[2]));
		headphone.setProductName(headphonesFields[3]);
		headphone.setWarrantyEligible(Boolean.parseBoolean(headphonesFields[4]));
		headphone.setBrand(headphonesFields[5]);
		return headphone;
		}
	/**************************************************************************
	 * Sends the transaction attributes to the RetailTransaction constructor.
	 * @param 	transactionFields a string array of each transaction attributes.
	 * @return  RetailTransaction This returns a RetailTransaction object.
	 *************************************************************************/
	
	public static RetailTransaction getTransactionFromFields(String[] transactionFields){
		return new RetailTransaction(Long.parseLong(transactionFields[1]),Integer.parseInt(transactionFields[2]),
				transactionFields[3],TransactionTypeEnum.valueOf(transactionFields[4].toUpperCase()));
	}
}

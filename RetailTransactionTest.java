import static org.junit.Assert.*;
import org.junit.Test;

/******************************************************************************
 * <pre>
 * File:RetailTransactionTest.java
 * 
 * Test the scanItem, calculateTransactionTotal, and getNumberOfProductsSold
 * methods.
 * </pre>
 * @author Nicol Salgado
 *
 *****************************************************************************/
public class RetailTransactionTest {

	@Test
	/**************************************************************************
	 * 
	 * Test if the scanItem method works for a Sale transaction.
	 * 
	 *************************************************************************/
	public void test_scanItem_sale() {
		//GIVEN//
	
		CellPhone cellphone = new CellPhone();
		cellphone.setproductId("h-11111");
		cellphone.setPrice(100.00);
		cellphone.setProductName("Apple 20");
		cellphone.setWarrantyEligible(true);
		cellphone.setBrand("Apple");
		cellphone.setCarrier("verizon");
		
		RetailTransaction sut = new RetailTransaction(2468,15,"2017-01-01 08:01:59",TransactionTypeEnum.SALE);
		//WHEN//
		sut.setquantity(2);
		sut.setwarranty(1);
		sut.scanItem(cellphone);
		double result = sut.calculateSubtotal();
		double expectedsubtotal = (100.00*2 +100.00*0.1);
		//THEN//
		assertTrue(result==expectedsubtotal);
		assertEquals((int)expectedsubtotal, (int)result);
	}
	@Test
	/**************************************************************************
	 * 
	 * Test if the scanItem method works for a return transaction.
	 * 
	 *************************************************************************/
	public void test_scanItem_return() {
		//GIVEN//
	
		CellPhone cellphone = new CellPhone();
		cellphone.setproductId("h-11111");
		cellphone.setPrice(100.00);
		cellphone.setProductName("Apple 20");
		cellphone.setWarrantyEligible(true);
		cellphone.setBrand("Apple");
		cellphone.setCarrier("verizon");
		
		RetailTransaction sut = new RetailTransaction(2468,15,"2017-01-01 08:01:59",TransactionTypeEnum.RETURN);
		//WHEN//
		sut.setquantity(2);
		sut.setwarranty(1);
		sut.scanItem(cellphone);
		double result = sut.calculateSubtotal();
		double expectedsubtotal = (100.00*2 +100.00*0.1)* -1;
		//THEN//
		assertTrue(result==expectedsubtotal);
		assertEquals((int)expectedsubtotal, (int)result);
	}
	@Test
	/**************************************************************************
	 * 
	 * Test if the calculateTransactionTotal method works for a Sale transaction.
	 * 
	 *************************************************************************/
	public void test_calculateTransactionTotal_sale() {
		//GIVEN//
		
		CellPhone cellphone = new CellPhone();
		cellphone.setproductId("h-11111");
		cellphone.setPrice(100.00);
		cellphone.setProductName("Apple 20");
		cellphone.setWarrantyEligible(true);
		cellphone.setBrand("Apple");
		cellphone.setCarrier("verizon");
		
		RetailTransaction sut = new RetailTransaction(2468,15,"2017-01-01 08:01:59",TransactionTypeEnum.SALE);
		//WHEN//
		sut.setquantity(2);
		sut.setwarranty(1);
		sut.scanItem(cellphone);
		sut.calculateSubtotal();
		double result = sut.calculateTransactionTotal();
		double expectedtotal = (100.00*2 +100.00*0.1)*1.0725;
		//THEN//
		assertTrue(result==expectedtotal);
		assertEquals((int)expectedtotal, (int)result);
	}
	@Test
	/**************************************************************************
	 * 
	 * Test if the calculateTransactionTotal method works for a 
	 * return transaction.
	 * 
	 *************************************************************************/
	public void test_calculateTransactionTotal_return() {
		//GIVEN//
		
		CellPhone cellphone = new CellPhone();
		cellphone.setproductId("h-11111");
		cellphone.setPrice(100.00);
		cellphone.setProductName("Apple 20");
		cellphone.setWarrantyEligible(true);
		cellphone.setBrand("Apple");
		cellphone.setCarrier("verizon");
		
		RetailTransaction sut = new RetailTransaction(2468,15,"2017-01-01 08:01:59",TransactionTypeEnum.RETURN);
		//WHEN//
		sut.setquantity(2);
		sut.setwarranty(1);
		sut.scanItem(cellphone);
		sut.calculateSubtotal();
		double total = sut.calculateTransactionTotal();
		double expectedtotal = (100.00*2 +100.00*0.1)*1.0725*-1;
		//THEN//
		assertTrue(total==expectedtotal);
		assertEquals((int)expectedtotal, (int)total);
	}
	@Test
	/**************************************************************************
	 * 
	 * Test if the getNumberOfProductSold method works for a Sale transaction.
	 * 
	 *************************************************************************/
	public void test_getNumberOfProductSold_TWO() {
		//GIVEN//
		CellPhone cellphone = new CellPhone();
		cellphone.setproductId("h-11111");
		cellphone.setPrice(100.00);
		cellphone.setProductName("Apple 20");
		cellphone.setWarrantyEligible(true);
		cellphone.setBrand("Apple");
		cellphone.setCarrier("verizon");
				
		RetailTransaction sut = new RetailTransaction(2468,15,"2017-01-01 08:01:59",TransactionTypeEnum.SALE);
		//WHEN//
		sut.setquantity(2);
		sut.setwarranty(1);
		sut.scanItem(cellphone);
		double result = sut.getNumberOfProductsSold();
		//THEN//
		assertTrue(result == 2.0);
		assertEquals(2, (int)result);
	}
	@Test
	/**************************************************************************
	 * 
	 * Test if the getNumberOfProductSold method works for a return transaction.
	 * 
	 *************************************************************************/
	public void test_getNumberOfProductSold_FIVE() {
		//GIVEN//
		CellPhone cellphone = new CellPhone();
		cellphone.setproductId("h-11111");
		cellphone.setPrice(100.00);
		cellphone.setProductName("Apple 20");
		cellphone.setWarrantyEligible(true);
		cellphone.setBrand("Apple");
		cellphone.setCarrier("verizon");
				
		RetailTransaction sut = new RetailTransaction(2468,15,"2017-01-01 08:01:59",TransactionTypeEnum.RETURN);
		//WHEN//
		sut.setquantity(5);
		sut.setwarranty(0);
		sut.scanItem(cellphone);
		double result = sut.getNumberOfProductsSold();
		//THEN//
		assertTrue(result == 5.0);
		assertEquals(5, (int)result);
	}
}

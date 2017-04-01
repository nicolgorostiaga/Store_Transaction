/******************************************************************************
 * <pre>
 * File: CellPhone.java
 * 
 * Manages the CellPhone carrier information and
 * inherits all the fields of the Product class.
 * 
 * </pre>
 * @author Nicol Salgado
 *
 *****************************************************************************/
public class CellPhone extends Product {

	private String carrier;

	public String getCarrier(){
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
}

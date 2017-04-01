/******************************************************************************
 * <pre>
 * File: HeadPhones.java
 * 
 * Manages the HeadPhone hasbluetooth information and
 * inherits all the fields of the Product class.
 * 
 * </pre>
 * @author Nicol Salgado
 *
 *****************************************************************************/
public class HeadPhones extends Product{

	private boolean hasBluetooth;
	
	public boolean hasBluetooth(){
		return hasBluetooth;
	}
	public void setHasBluetoothFlag(boolean hasBluetooth) {
		this.hasBluetooth = hasBluetooth;
	}
}

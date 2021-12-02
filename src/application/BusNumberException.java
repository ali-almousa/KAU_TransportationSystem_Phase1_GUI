/**
 * @author Ali
 *
 */
package application;

public class BusNumberException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numberOfBusses;
	
	public BusNumberException(int numberOfBusses) {
		super("Invalid number of busses " + numberOfBusses);
		this.numberOfBusses = numberOfBusses;
	}

	/**
	 * @return the numberOfBusses
	 */
	public int getNumberOfBusses() {
		return numberOfBusses;
	}
	
}




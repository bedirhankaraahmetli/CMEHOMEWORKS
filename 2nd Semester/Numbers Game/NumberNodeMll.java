
public class NumberNodeMll {

	private int number;
	private NumberNodeMll next;
	
	public NumberNodeMll (int data_to_add) {
		
		this.number = data_to_add;
		this.next = null;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public NumberNodeMll getNext() {
		return next;
	}

	public void setNext(NumberNodeMll next) {
		this.next = next;
	}
	
}

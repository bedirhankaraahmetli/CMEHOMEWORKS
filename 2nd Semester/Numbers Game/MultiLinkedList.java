import NumbersGame.console.ConsoleHelper;

public class MultiLinkedList {
	
	public ColumnNodeMll head = null;
	
	
	public void addColumn(String data_to_add) {
		
		if (head == null) {
			
			ColumnNodeMll new_node = new ColumnNodeMll(data_to_add);
			head = new_node;
		}
		else {
			
			ColumnNodeMll temp = head;
			
			while (temp.getRight() != null) {
				
				temp = temp.getRight();
			}
			
			ColumnNodeMll new_node = new ColumnNodeMll(data_to_add);
			temp.setRight(new_node);
		}
	}
	
	public void addNumber(String column, int data_to_add) {
		
		if (head == null) {
			System.out.println("Add a columns before a number");
		}
		else {
			
			ColumnNodeMll temp = head;
			
			while (temp != null) {
				
				if (column.equals(temp.getColumn())) {
					
					NumberNodeMll temp2 = temp.getDown();
					
					if (temp2 == null) {
						
						NumberNodeMll new_node = new NumberNodeMll(data_to_add);
						temp.setDown(new_node);
					}
					else {
						
						while (temp2.getNext() != null) {
							
							temp2 = temp2.getNext();
						}
						
						NumberNodeMll new_node = new NumberNodeMll(data_to_add);
						temp2.setNext(new_node);
					}
				}
				
				temp = temp.getRight();
			}
		}
	}
	
	public int sizeNumber(String column) {
		
		int count = 0;
		
		if (head == null) {
			System.out.println("Linked list is empty");
		}
		else {
			
			ColumnNodeMll temp = head;
			
			while (temp != null) {
				
				if (column.equals(temp.getColumn())) {
					break;
				}
				temp = temp.getRight();
			}
			
			NumberNodeMll temp2 = temp.getDown();
				
			while (temp2 != null) {
				
				count++;
				temp2 = temp2.getNext();
			}
		}
		
		return count;
	}

	public void printSingleColumn(String column, int x, int y) {
		
		ColumnNodeMll temp = head;
		
		while (!column.equals(temp.getColumn())) {
			
			temp = temp.getRight();
		}
		
		NumberNodeMll temp2 = temp.getDown();
		
		while (temp2 != null) {
			
			ConsoleHelper.print(Integer.toString(temp2.getNumber()), x, y);
			
			y++;
			temp2 = temp2.getNext();
		}
	}

	public String theValueOfTheLocation(String column, int x, int y) { // Verilen konumda bulunan sayýyý dönderir.
		
		ColumnNodeMll temp = head;

		while (!column.equals(temp.getColumn())) {
			temp = temp.getRight();
		}
		
		NumberNodeMll temp2 = temp.getDown();
		
		for (int i = 0; i < y-2; i++) {
			temp2 = temp2.getNext();
		}
		
		return String.valueOf(temp2.getNumber());
	}
	
	public NumberNodeMll selectNumber(String column, int x, int y) {
		
		ColumnNodeMll temp = head;
		boolean flag = true;

		while (!column.equals(temp.getColumn())) { // Kullanýlacak kolona gidiliyor.
			temp = temp.getRight();
		}
		
		NumberNodeMll temp2 = temp.getDown();
		
		
		for (int i = 0; i < y-2; i++) { // seçilen numbera gidiliyor.
			temp2 = temp2.getNext();
		}
		
		NumberNodeMll temp3 = temp2;
		int differenceBetween;
		
		if (temp3.getNext() != null) {
			
			differenceBetween = temp3.getNumber() - temp3.getNext().getNumber();
			
			if (differenceBetween == 1 || differenceBetween == -1 || differenceBetween == 0) {
				
				while(temp3.getNext() != null ) {
					
					if (temp3.getNumber() - temp3.getNext().getNumber() != differenceBetween) {

						flag = false;
						break;
					}
					
					temp3 = temp3.getNext();
				}
			}
			else {
				flag = false;
			}
		} 
		
		if (flag) {
			return temp2;
		}
		else {
			return null;
		}
	}

	public int lastValue (String column) {
		
		int value;
		
		ColumnNodeMll temp = head;
		
		while (!column.equals(temp.getColumn()) ) { // Kolon bulunur
			
			temp = temp.getRight();
		}
		
		NumberNodeMll temp2 = temp.getDown();
		if (sizeNumber(column) > 0) {
			
			while (temp2.getNext() != null) {
				temp2 = temp2.getNext();
			}
		
			value = temp2.getNumber();
		}
		else {

			value = -1; // column boþsa -1 dönecek
		}
		
		
		return value;
				
	}
	
	public int firstValue (String column) {
		
		int value;
		
		ColumnNodeMll temp = head;
		
		while (!column.equals(temp.getColumn()) ) { // Kolon bulunur
			
			temp = temp.getRight();
		}
		
		NumberNodeMll temp2 = temp.getDown();
		if (sizeNumber(column) > 0) {
			
			value = temp2.getNumber();
		}
		else {

			value = -1; // column boþsa -1 dönecek
		}
		
		
		return value;

	}
	
	public void addNumberNode (String column, NumberNodeMll node) {
		
		ColumnNodeMll temp = head;
		
		while (!column.equals(temp.getColumn())) {
			temp = temp.getRight();
		}
		
		NumberNodeMll temp2 = temp.getDown();
		
		if (temp.getDown() == null) {
			
			temp.setDown(node);;
		}
		else {
			
			while (temp2.getNext() != null) { // column da ilerliyoruz
				
				temp2 = temp2.getNext(); 
			}
			
			temp2.setNext(node);
		}
	}
	
	public void deletionProcess (String column, int y) {
		
		ColumnNodeMll temp = head;
		
		while (!column.equals(temp.getColumn())) { // column a gidiliyor
			
			temp = temp.getRight();
		}
		
		NumberNodeMll temp2 = temp.getDown();
		
		for (int i = 1; i < y-2; i++) { // seçilen numbera gidiliyor.
			temp2 = temp2.getNext();
		}
		
		if (y == 2) {
			temp.setDown(null);
		} 
		else {
			temp2.setNext(null);
		}
	}
	
	public boolean columnCheckingAndDeletion(String column, int difference) {
		
		ColumnNodeMll temp = head;
		boolean flag = true;
		
		while (!column.equals(temp.getColumn())) { // column a gidiliyor
			
			temp = temp.getRight();
		}
		
		NumberNodeMll temp2 = temp.getDown();
		
		while (temp2.getNext() != null) {
			
			if (temp2.getNumber() - temp2.getNext().getNumber() != difference) { // kurala uymama durumu
				
				flag = false;
				break;
			}
			
			temp2 = temp2.getNext();
		}
		
		if (flag) {
			temp.setDown(null);
		}
		
		return flag;
	}
}

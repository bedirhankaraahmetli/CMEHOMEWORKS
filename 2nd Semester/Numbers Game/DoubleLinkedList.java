public class DoubleLinkedList {

	private NodeDll head = null; // Starting point.
	private NodeDll tail = null; // Variable point.
	private NodeDll temp = null; // Last point.
	
	public void addNode(Object data_to_add) { // Adds a value.
		
		NodeDll new_node = new NodeDll(data_to_add); 
		
		if (head == null) { // If list is empty.
			
			head = tail = new_node; // Both head and tail will point to newNode.
		} 
		else { //If the list is not empty.

			tail.setNext(new_node);
			new_node.setPrev(tail);
			tail = new_node;
		}
	}
	
	public int size() {
		
		if(head == null) {
			
			return 0;
		}
		else {
			
			int count = 0;
			
			temp = head;
			
			while (temp != null) { // It navigates through the LinkedList.
				temp = temp.getNext();
				count++;
			}
			
			return count;
		}
	}
	
	public void display() {
		
		if(head == null) {
			
			System.out.println("List is empty!");
		}
		else {
			
			temp = head;
		
			while (temp != null) {
				System.out.print(temp.getData() + " ");
				temp = temp.getNext();
			}
		}
	}
	
	public void remove(Object data_to_delete) {
		
		if (head == null) {
			
			System.out.println("Linked list is empty.");
		} 
		else {

			while (data_to_delete.equals(head.getData())) { // Deleting the element located at the starting point.
				head = head.getNext();
				head.setPrev(null);
			}
			
			temp = head;
			NodeDll previous = null;
			NodeDll subsequent = null;
			
			while (temp != null) { // It navigates through the LinkedList.
				
				if (data_to_delete.equals(temp.getData())) {
					
					subsequent = temp.getNext();
					subsequent.setPrev(previous);
					previous.setNext(subsequent);
					temp = previous;
				}
			
				previous = temp;
				temp = temp.getNext();
			}
		}
	}

	public boolean search (Object data) { // Looking for an element on LinkedList.
		
		if (head == null) {
			System.out.println("List is empty!");
			return false;
		} 
		else {
			
			temp = head;
			
			while (temp != null) { // It navigates through the LinkedList.
				
				if (data.equals(temp.getData())) {
					
					return true;
				}
				
				temp = temp.getNext();
			}
			
			return false;
		}
	}

	public void sortAddDll(Player p) {
		
		NodeDll new_node = new NodeDll(p);
		
		if (head == null) { // inserting first element
			
			head = new_node;
		} 
		else if (p.getPlayer_score() > ((Player) head.getData()).getPlayer_score()) { // Inserting to the front
			
			new_node.setNext(head);
			head.setPrev(new_node);
			head = new_node;
		} 
		else { // inserting in between two nodes and inserting to the last
			
			NodeDll temp = head;
			NodeDll previous = null;
			
			while (temp != null && p.getPlayer_score() < ((Player) temp.getData()).getPlayer_score()) {
				previous = temp;
				temp = temp.getNext() ;
			}
			
			if (temp == null) { // inserting to the last
				new_node.setPrev(previous);
				previous.setNext(new_node);
			}
			else { // inserting in between two nodes
				new_node.setNext(temp);
				new_node.setPrev(previous);
				previous.setNext(new_node);
			}
		}
	}

	public void displayHST() {
		
		Player p;
		
		if(head == null) {
			
			System.out.println("List is empty!");
		}
		else {
			
			temp = head;
			int count = 0;
		
			while (temp != null) {
				
				count++;
				p = (Player) temp.getData();
				System.out.println(count + "- " + p.player_name + " => " + p.player_score);
				temp = temp.getNext();
			}
		}
	}
	
	public String preparationForWriteFile () {
		
		String s = "";
		Player p;
		
		NodeDll temp = head;
		
		while (temp != null) {
			
			p = (Player) temp.getData();
			
			if (temp == head) {
				
				s = p.getPlayer_name() +"#"+ String.valueOf(p.getPlayer_score());
			}
			else {
				
				s += "\n" + p.getPlayer_name() +"#"+ String.valueOf(p.getPlayer_score());
			}
			
			temp = temp.getNext();
		}
		
		return s;
		
	}
}

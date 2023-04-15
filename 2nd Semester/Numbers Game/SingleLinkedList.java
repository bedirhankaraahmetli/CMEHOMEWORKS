
public class SingleLinkedList {

	private NodeSll head = null; // Starting point.
	
	public void addNode(Object data_to_add) { // Adds a value.
		
		NodeSll new_node = new NodeSll(data_to_add);
		
		if (head == null) { // If list is empty.
			
			head = new_node; //head will point to newNode.
		}
		else { // If the list is not empty.
			
			NodeSll temp = head; // Use the variable point so you don't lose the starting point.
			
			while (temp.getLink() != null) { // It navigates through the LinkedList.
				temp = temp.getLink();
			}
			
			temp.setLink(new_node);
		}
	}
	
	public int size() { // Finds the size of the LinkedList.
		
		if(head == null) {
			
			return 0;
		}
		else {
			
			int count = 0;
			
			NodeSll temp = head;
			
			while (temp != null) { // It navigates through the LinkedList.
				temp = temp.getLink();
				count++;
			}
			
			return count;
		}
	}
	
	public void display() { // Prints the LinkedList elements.
		
		if (head == null) {
			
			System.out.println("List is empty!");
		}
		else {
			
			NodeSll temp = head;
			
			while (temp != null) { // It navigates through the LinkedList.
				System.out.print(temp.getData() + " ");
				temp = temp.getLink();
			}
		}
	}
	
	public void remove (Object data_to_delete) { // Deletes element from the LinkedList.
		
		if (head == null) {
			
			System.out.println("Linked list is empty.");
		} 
		else {

			while (data_to_delete.equals(head.getData())) { // Deleting the element located at the starting point.
				head = head.getLink();
			}
			
			NodeSll temp = head;
			NodeSll previous = null;
			
			while (temp != null) { // It navigates through the LinkedList.
				
				if (data_to_delete.equals(temp.getData())) {
					
					previous.setLink(temp.getLink());
					temp = previous;
				}
			
				previous = temp;
				temp = temp.getLink();
			}
		}
	}
	
	public boolean search (Object data) { // Looking for an element on LinkedList.
		
		if (head == null) {
			System.out.println("List is empty!");
			return false;
		} 
		else {
			
			NodeSll temp = head;
			
			while (temp != null) { // It navigates through the LinkedList.
				
				if (data.equals(temp.getData())) {
					
					return true;
				}
				
				temp = temp.getLink();
			}
			
			return false;
		}
	}
	
	public Object subtructInitialValue () { // Subtracts the first element from sll.
		
		Object result = null;
		
		if (head == null) {
			
			System.out.println("Linked list is empty.");
		} 
		else {

			result = head.getData();
			head = head.getLink();
		}
		
		return result;
	}
	
	public Object initialValue () { // Subtracts the first element from sll.
		
		Object result = null;
		
		if (head == null) {
			
			System.out.println("Linked list is empty.");
		} 
		else {

			result = head.getData();
		}
		
		return result;
	}

	public boolean isEmpty() {
		
		boolean flag = false;
		
		if (head == null) {
			flag = true;
		}
		
		return flag;
	}
	
	public SingleLinkedList cutOff(int break_point) {
		
		SingleLinkedList sll = new SingleLinkedList();
		
		if (size() == break_point) {
			
			sll.head = head; 
			head = null;
		}
		else {
			
			NodeSll temp = head, prev = temp;
			
			for (int i = 0; i < size() - break_point; i++) {
				
				prev = temp;
				temp = temp.getLink();
			}
			
			sll.head = temp;
			prev.setLink(null);
		}
		
		return sll;
	}
}

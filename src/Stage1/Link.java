package Stage1;
import java.util.LinkedList;

public class Link {

	
	Node head = null;
	int size = 0;
	
	
	public void add(Linked l,int[] r){
		Node newNode = new Node();
		LinkedListNodes u = l.head;
		
		while(u != null){
			
			newNode.arr.add(u.minterm, u.Binary);
			
			u = u.next;
		}
		
		
		if(this.head==null){
			newNode.next = this.head ;
			this.head = newNode;
		}
		else{
			Node i = this.head;
			
			while (i.next != null) {
				i = i.next;
			}
			
			i.next = newNode;
			newNode.next = null;
		
		}
		size++;
		
		
	}
	
	public int size(){
		return size;
	}
	
	public void clear(){
		this.head=null;
		size=0;
	}
	
}

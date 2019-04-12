package Stage1;
import java.util.LinkedList;



public class Linked {

	LinkedListNodes head = null;
	private int size=0;
	
	public void add(LinkedList l,int[] binary){
		LinkedListNodes newNode = new LinkedListNodes(binary.length);
		
		for(int i=0;i<l.size();i++){
			newNode.minterm.add(l.get(i));
		}
		for(int i=0;i<binary.length;i++){
			newNode.Binary[i]=binary[i];
		}
		
		
		if(this.head==null){
			newNode.next = this.head ;
			this.head = newNode;
		}
		else{
			LinkedListNodes i = this.head;
			
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
		size = 0;
	}
	
}

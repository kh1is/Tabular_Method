package Stage2;

import java.util.LinkedList;

public class SinglyLinkedList {

								/* Attributes */
	
	public ListNode head;
	private ListNode tail;
	public int size;

/* _________________________________________________________________________________ */

								/* constructor */

	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
								/* methods */
/* _________________________________________________________________________________ */

	public void add(int [] arr, LinkedList mintermsParticipated) {
		
		ListNode implicant = new ListNode();
		implicant.setBinaryRep(arr);
		implicant.mintermsParticipated = (LinkedList) mintermsParticipated.clone();
		
		size++;
		if(tail == null)  // tail == null >>> means empty List
		{ 
			implicant.setNext(null);
			tail = implicant;
			head = implicant; 
 
		}else
		{
			//take care of the order of these 3 lines of code
			implicant.setNext(null);
			tail.setNext(implicant);
			tail = implicant;
		}
	}
	
	public void clear(){
		this.head = null;
		this.tail = null;
		this.size = 0;
		
	}
	
	public boolean contains(int [] arr) {
		if(this.head == null){
			return false;
		}
		
		ListNode i = this.head;
		while(i != null)
		{
			int counter = 0;
			for(int j = 0 ; j < arr.length ; j++){
				if(i.binaryRep[j] == arr[j])
				{
					counter++;
				}
			}
			if(counter == arr.length){
				return true;
			}else{
				i = i.getNext();
			}	
		}
		return false;
	}
	
}



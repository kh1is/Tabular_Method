package Stage2;

import java.util.LinkedList;

public class ListNode {
	
									// Attributes
	private ListNode next;
	public int[] binaryRep;
	public boolean checked;
	public LinkedList <Object> mintermsParticipated = new LinkedList <Object>();
	
	
									//methods
	public ListNode getNext()
	{
		return this.next;
	}
	
	public void setNext(ListNode newNext)
	{
		this.next = newNext;
	}
	
	public void setBinaryRep(int [] arr){
		binaryRep = new int[arr.length];
		for(int i = 0 ; i < arr.length ; i++){
			binaryRep[i] = arr[i];
		}
	}
}

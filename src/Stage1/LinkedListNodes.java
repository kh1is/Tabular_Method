package Stage1;
import java.util.LinkedList;

public class LinkedListNodes {

	LinkedListNodes next ;
	LinkedList minterm ;
	int[] Binary ;
	boolean check ;
	int counter ;
	public  LinkedListNodes(int length){
		next = null;
		minterm = new LinkedList();
		Binary = new int[length];
		check = false;
		counter = 0;
		
	}
}
package Stage1;


import Stage2.ListNode;

import Stage2.SinglyLinkedList;
import Stage2.groupingModule;
import java.util.LinkedList;

public class Answer {


	public Linked l = new Linked();
	
	public SinglyLinkedList ll = new SinglyLinkedList();
	
	
	public String nameOfStep1 = "";
	public String nameOfStep2 = "";
	public String nameOfStep3 = "";
	public String nameOfStep4 = "";
	public String nameOfStep5 = "";
	public String nameOfStep6 = "";
	public String nameOfStep7 = "";
	public String nameOfSolution = "";
	
	public String minterms = "";
	public String dontCares = "";
	
	public groupingModule g;
	public Class c;
	public  Answer(String s1,String s2){
		
		minterms=s1;
		dontCares=s2;
		
		g =  new groupingModule(minterms,dontCares);
		
		ll = g.getPrimeImplicants(minterms, dontCares);
		
		ListNode f = ll.head;
		
		for(int i=0; i<ll.size;i++){
			l.add(f.mintermsParticipated, f.binaryRep);
			f=f.getNext();
		}
		
		c = new Class(g.mintermsArr.length, l.size());
		
		operate();
	}
	
	
	
	
	
	public void operate(){
		
		c.setChart(g.mintermsArr, l);
		
		nameOfStep1 = c.nameOfChart(g.mintermsArr, l);
		
		c.markEssential(l);
		
		nameOfStep2 = c.nameOfEssentialPrimes();
		
		nameOfStep3 = c.nameOfMarkEssential(g.mintermsArr, l);
		
		c.newArray(g.mintermsArr);
		
		c.newLinked(l);
		
		c.setNewChart();
		
		nameOfStep4 = c.nameOfNewChart();
		
		c.setFunction();
		
		nameOfStep5 = c.nameOfFunction();
		
		c.Kolh();
		
		nameOfStep6 = c.nameOfFunction();
		
		c.minimumPrimes();
		
		nameOfStep7 = c.nameOfMinimumPrimes();
		
		c.solutions();
	
		nameOfSolution = c.nameOfSolution();
		
	}
	
	public String Step1(){
		return nameOfStep1;
	}
	
	public String Step2(){
		return nameOfStep2;
	}
	
	public String Step3(){
		return nameOfStep3;
	}
	
	public String Step4(){
		return nameOfStep4;
	}
	
	public String Step5(){
		return nameOfStep5;
	}
	
	public String Step6(){
		return nameOfStep6;
	}
	
	public String Step7(){
		return nameOfStep7;
	}
	
	public String Solution(){
		return nameOfSolution;
	}
}

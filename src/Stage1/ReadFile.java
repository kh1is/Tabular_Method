package Stage1;

import java.io.File;
import java.util.Scanner;

public class ReadFile {
	
	public String word[]=new String[2];

	private Scanner x ;
	
	public Answer a ;
	public ReadFile(String s1){
		 
		openFile(s1);
		readFile();
		closeFile();
		a = new Answer(word[0],word[1]);
		 
	 }
	 
	 
	 
	public void openFile(String fileName){
			try{
				File sajed = new File(fileName);
				x = new Scanner(sajed);
			}
			catch(Exception e){
				System.out.println("error");
			}
		}
		
	public void readFile(){
			int counter=0;
			while (x.hasNext()){
				
				word[counter] = x.next();
				
				counter++;
				}
		}
		
	public void closeFile(){

			x.close();
	}

	public String Step1(){
		return a.Step1();
	}
	
	public String Step2(){
		return a.Step2();
	}
	
	public String Step3(){
		return a.Step3();
	}
	
	public String Step4(){
		return a.Step4();
	}
	
	public String Step5(){
		return a.Step5();
	}
	
	public String Step6(){
		return a.Step6();
	}
	
	public String Step7(){
		return a.Step7();
	}
	
	public String Solution(){
		return a.Solution();
	}
		
}

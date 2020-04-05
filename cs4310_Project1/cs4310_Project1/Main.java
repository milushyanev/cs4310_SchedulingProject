
//package cs4310_Project1;

import java.util.*; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.*; 
import java.io.*; 
public class Main {
static int listElementCount=60;
public static List<String> readFileInList(String fileName) 
{ 
	//read from file using Collections to check if empty and if throw an error,else display data
	List<String> lines = Collections.emptyList(); 
	try
		 { 	
			 lines =Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
		     
		 } 
		
		 	catch (IOException e) 
		 { 
		 		e.printStackTrace(); 
		 } 
	return lines; 
}
static ArrayList<Double> ParsedList(List<String>l) {
	ArrayList<Double>parsedList=new ArrayList<Double>();
	 for(int i=0;i<l.size();i++) {
		 if(i%2==1) {
			 parsedList.add(Double.parseDouble(l.get(i)));
		 }
	 }
	 
	return parsedList;
}
static ArrayList<Double> SortedList( ArrayList<Double>l) {
	
	//sort the current list for SJF use
	 Collections.sort(l);
	 
	return l;
}
static void FCFS(List<String>TextList) {
	 ArrayList<Double>calcTimeList=ParsedList(TextList);
	 double runningTimes=0;
	 double finalAddResult=0;
	 double calcListTime=0;
	 //for loop to calculate new Times accordingly
	 for(int i=0;i<ParsedList(TextList).size();i++) {
			 runningTimes+=ParsedList(TextList).get(i);
			 finalAddResult+=runningTimes;
			 
	 }
	 //get average by using the formula
	 double avgMeanTime=finalAddResult/ParsedList(TextList).size();
	 System.out.print("\n  ");
	 //get all even from file
	 for(int i=0;i<TextList.size();i++) {
		 if (i%2==0) {
			 System.out.print("| "+TextList.get(i)+" | "); 
		 }
		 
	 }
	 System.out.print("\n0  ");
	 //properly display the data for the Time for each job
	 for(int i=0;i<calcTimeList.size();i++) {
		
		 	calcListTime+=ParsedList(TextList).get(i);
			System.out.print("   "+calcListTime+"  ");
		}
	 //display result
	 System.out.println("\n\n FCFS Mean process turn‐around time = "+avgMeanTime);

}
static void SJF(List<String>l) {
	 //create 3 lists in order to get and display data fast
	 ArrayList<Double>calcTimeList=ParsedList(l);
	 ArrayList<String>getIndexOfTime=new ArrayList<String>();
	 ArrayList<String>getIndexOfJobs=new ArrayList<String>();
	 ArrayList<String>sortJobs=new ArrayList<String>();
	 
	 //create variables to handle Time manipulation
	 double runningTimes=0,finalAddResult=0;
	 double calcListTime=0;

	//get even and odd Objects from list
	for(int i=0;i<l.size();i++) {
		 if (i%2==0) {
			getIndexOfJobs.add(l.get(i)); 
		 }else if(i%2==1) {
			 getIndexOfTime.add(l.get(i));
		 }
	}
		//sort time and get appropriate Job using indexing from original file
	 	for(int i=0;i<SortedList(ParsedList(l)).size();i++) {
		double dValue=SortedList(ParsedList(l)).get(i);
		int value=(int)dValue;
		int index = getIndexOfTime.indexOf(Integer.toString(value));
		sortJobs.add(getIndexOfJobs.get(index));
		
	}
	 	for(int i=0;i<sortJobs.size();i++) {
	 		System.out.print("     "+sortJobs.get(i));
	 	}
	 //time calculation using list and the variables on top
	 for(int i=0;i<SortedList(ParsedList(l)).size();i++) {
		 runningTimes+=SortedList(ParsedList(l)).get(i);
		 finalAddResult+=runningTimes;
	 }
	 double avgMeanTime=finalAddResult/ParsedList(l).size();
	 System.out.print("\n0  ");
	 for(int i=0;i<calcTimeList.size();i++) {
	
	 	calcListTime+=SortedList(ParsedList(l)).get(i);
		System.out.print("   "+calcListTime+"  ");
	} 
	
	 System.out.println("\n\n SJF Mean process turn‐around time = "+avgMeanTime+"\n\n\n"+"\n\n\n");
	
}
static String getPath() {
	Scanner path=new Scanner(System.in);
	System.out.println("Please Enter your Path");
	System.out.println("Ex: C:\\Users\\Yanev\\eclipse-workspace\\cs4310_Project1\\job.txt");
	System.out.println("\n\nEnter Path");
	String userPath=path.nextLine();
	path.close();
	return userPath;
}
static void RoundRobin(List<String>l,int timeSlice) {
	//create 3 lists to manipulate data faster and a counter to count
	int count=0;
	List<String> getJobs=new ArrayList<String>();
	List<Double> getTime=new ArrayList<Double>();
	List<Double> getWaitingTime=new ArrayList<Double>();
	
	double result=0;
	//get even and odd Objects from original file
	for(int i=0;i<l.size();i++) {
		if(i%2==0) {
			getJobs.add(l.get(i));
		}else if(i%2==1) {
			getTime.add(Double.parseDouble(l.get(i)));
		}
	}
	//create a for loop to check logic
	for(int i=0;i<getJobs.size();i++) {
		//if calculation >0, just add the timeSlice to the Overall time
		//only here we are adding new/existing Jobs to the List, until 0 is reached
		if(getTime.get(i)-timeSlice>0) {
			getJobs.add(getJobs.get(i));
			getTime.add(getTime.get(i)-timeSlice);
			//check if the current elements is in 0 index and if it is add 0 to the current Slice
			//if not at 0 add the current position Slice plus the previous
			if(i==0) {
				getWaitingTime.add((double)timeSlice+0);
			}else {
				getWaitingTime.add((double)timeSlice+getWaitingTime.get(i-1));}
		}//if calculation is 0, we are using the same logic as above but we have to count result 
		//and counter to properly do calculations for end result
		else if(getTime.get(i)-timeSlice==0) {
			if(i==0) {
				getWaitingTime.add((double)timeSlice+0);
				result+=(double)timeSlice+0;
				count++;
			}else {
					getWaitingTime.add((double)timeSlice+getWaitingTime.get(i-1));
					result+=(double)timeSlice+getWaitingTime.get(i-1);
					count++;}
		}//same logic as above
		else if(getTime.get(i)-timeSlice<0) {
			if(i==0) {
				getWaitingTime.add(getTime.get(i)+0);
				result+=getTime.get(i)+0;
				count++;
			}else {
					getWaitingTime.add(getTime.get(i)+getWaitingTime.get(i-1));
					result+=getTime.get(i)+getWaitingTime.get(i-1);
					count++;}
		}
		
	}//several for loop to print result
	double calcMeanProcessTimeAvg=result/count;
	System.out.print("|");
	for(int i=0;i<getJobs.size();i++) {
			 System.out.print(getJobs.get(i)+" | "); 
		 
	}
	System.out.print("\n     ");
	for(int i=0;i<getWaitingTime.size();i++) {
			 System.out.print(getWaitingTime.get(i)+"   "); 
		 
	}
	System.out.print("\n\n Round Robin with "+timeSlice+" minute slices Mean process turn‐around time = "+calcMeanProcessTimeAvg+"\n\n\n");
};
public static int InputValidation(int validationOne,int validationTwo) {
	//simple verification that checks if input is between 2 values 
	//and makes sure that input is integer
	Scanner sc=new Scanner(System.in);
	int number;
	do {
	  	    while (!sc.hasNextInt()) {
	        System.out.println("That's not a number!");
	        sc.next(); sc.next(); 
	    }
	    number = sc.nextInt();
	} while (number <validationOne || number>validationTwo);
	sc.close();//if used more than one time, we have to delete sc.close();
	return number;
	
}
public static void main(String[] args) {
	
	List<String> l = readFileInList("C:\\Users\\Yanev\\eclipse-workspace\\cs4310_Project1\\job.txt");	
	
	//if user doesnt add a proper path, the program will crash and not execute. 
	//Please use the example provided or modify the above path with your path to save you time for testing purposes.
	//Make sure that you comment out List<String> l = readFileInList(getPath()); if you want to use static path
	//List<String> l = readFileInList(getPath());
	
	//modify Slice for testing purposes
	int roundRobinSlice=5;
	List<String> fixedList= new ArrayList<String>();
	
	for(int i=0;i<l.size();i++) {
		if(l.size()<listElementCount){
			fixedList.add(l.get(i));
		}
	}

	System.out.println("\nPlease Choose 1. RoundRobin , 2. FCFS , 3.SJF");
	System.out.print("\nOption->");
	 int userInput=InputValidation(1,3);
	 System.out.print("\n"+fixedList+"\n\n");
		if (userInput==1) {
		RoundRobin(fixedList,roundRobinSlice);	
		}else if (userInput==2) {
			FCFS(fixedList);
		}else if(userInput==3) {
			SJF(fixedList);
		}
	}

} 

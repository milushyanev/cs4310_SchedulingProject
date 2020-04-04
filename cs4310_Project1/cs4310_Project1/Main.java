package cs4310_Project1;

//Java program to illustrate reading data from file 
//using nio.File 
import java.util.*; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.*; 
import java.io.*; 
public class Main {

public static List<String> readFileInList(String fileName) 
{ 

 List<String> lines = Collections.emptyList(); 
 try
 { 
   lines = 
    Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
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
	
	 Collections.sort(l);
	 
	return l;
}

static void FCFS(List<String>l) {
	 ArrayList<Double>calcTimeList=ParsedList(l);
	 double result=0;
	 double calcListTime=0;
	 
	 for(int i=0;i<ParsedList(l).size();i++) {
			 result+=ParsedList(l).get(i);
			 
		
	 }
	 double avgMeanTime=result/ParsedList(l).size();
	 for(int i=0;i<l.size();i++) {
		 if (i%2==0) {
			 System.out.print("| "+l.get(i)+" | "); 
		 }
		 
	 }
	 System.out.print("\n0  ");
	 for(int i=0;i<calcTimeList.size();i++) {
		
		 	calcListTime+=ParsedList(l).get(i);
			System.out.print("   "+calcListTime+"  ");
		} 

	 System.out.println("\n\nMean process turn‐around time = "+avgMeanTime);

}

static String getPath() {
	Scanner path=new Scanner(System.in);
	System.out.println("Please Enter your Path");
	System.out.println("Ex: C:\\Users\\Yanev\\eclipse-workspace\\cs4310_Project1\\job.txt");
	System.out.println("\n\nEnter Path");
	String userPath=path.nextLine();
	return userPath;
}

public static void main(String[] args) {
	List<String> l = readFileInList("\\Users\\Yanev\\eclipse-workspace\\cs4310_Project1\\job.txt");	
	 ArrayList<Double>calcTimeList=ParsedList(l);
	ArrayList<String>testing=new ArrayList<String>();
	ArrayList<String>testing2=new ArrayList<String>();
	
	//System.out.println(SortedList(ParsedList(l)));

	for(int i=0;i<l.size();i++) {
		 if (i%2==0) {
			testing.add(l.get(i)); 
		 }
	}
	ArrayList<String>testing1=new ArrayList<String>();
	 for(int i=0;i<l.size();i++) {
		 if(i%2==1) {
testing1.add(l.get(i));
		 }
		 
	 }
	// System.out.print(testing1);
	 	for(int i=0;i<SortedList(ParsedList(l)).size();i++) {
		double dValue=SortedList(ParsedList(l)).get(i);
		int value=(int)dValue;
		//System.out.print(value+" ");
		//Collections.sort(testing1);
		
		int index = testing1.indexOf(Integer.toString(value));
		//System.out.print(Integer.toString(value) +" ");
		testing2.add(testing.get(index));
		
	}
	 	//System.out.print(index);
	 	//int index = Collections.binarySearch(testing1, "7");
		//System.out.print(index +" ");
System.out.print(testing2);
System.out.println("\n"+SortedList(ParsedList(l)));
double result=0;
double calcListTime=0;

for(int i=0;i<SortedList(ParsedList(l)).size();i++) {
		 result+=SortedList(ParsedList(l)).get(i);
		 
	
}
double avgMeanTime=result/ParsedList(l).size();
System.out.print("\n0  ");
for(int i=0;i<calcTimeList.size();i++) {
	
	 	calcListTime+=SortedList(ParsedList(l)).get(i);
		System.out.print("   "+calcListTime+"  ");
	} 

System.out.println("\n\nMean process turn‐around time = "+avgMeanTime);
//System.out.print(ParsedList(l).toString());
	
	//Collections.sort(arrList);
	//System.out.println(arrList);
	//int index = Collections.binarySearch(testing1, "4.0");
	//System.out.println("The element exists at index = " + index);
 //List<String> l = readFileInList(getPath());	
 //FCFS(l);
	}
} 

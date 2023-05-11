package Collections;

import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class CollectionsProject {	
	
	static void enhancedForLoop(List<String> arrayList){
		//Enhanced For loop
		for (String name : arrayList) {
			System.out.println(name);
		}
	}	
	
	static void shuffle(List<String> arrayList) {
		//Randomize order of elements using shuffle
		Collections.shuffle(arrayList);
		System.out.println(arrayList);
	}
	
	static void order(List<String> arrayList) {
		//Alphabetize elements using sort
		Collections.sort(arrayList);
		System.out.println(arrayList);
	}
	
	
	public static void main(String args[]){ 
		//Create ArrayList object. 
		List<String> arrayList = new ArrayList<String>();   
		arrayList.add("Fox"); 
		arrayList.add("Cat"); 
		arrayList.add("Dolphin"); 
		arrayList.add("Dog"); 
		arrayList.add("Gorilla"); 
        arrayList.add("Turtle"); 
		//method call 
		enhancedForLoop(arrayList);
		System.out.println("Index of Cat:");
		System.out.println(arrayList.indexOf("Cat"));
		System.out.println("Index of Snake:");
		System.out.println(arrayList.indexOf("Snake")); //Snake not found, indexOf will be -1
		System.out.println("Suffled arrayList:");
		shuffle(arrayList);
		System.out.println("Alphabetized arrayList");
		order(arrayList);
	    System.out.println("Converting ArrayList to Array:" );
	    String[] stringArray = arrayList.toArray(new String[arrayList.size()]);
	    for (int i = 0; i < stringArray.length; i++)
			System.out.println(stringArray[i]);
	    System.out.println("Converting Array back into ArrayList:");
		List<String> newArrayList = Arrays.asList(stringArray);
		System.out.println(newArrayList);
	}
}


//The difference between a class and an interface is the Collection interface
//provides the standard functionality of data structure to List, Set, and Queue, 
//while, the Collections class is to sort and synchronize the collection elements.
//The difference between a class and an interface is that a class is a reference
//type which is a blueprint to instantiate an object and interface is a reference
//type which cannot be used to instantiate an object.
//Java Collections Interview Questions (2023) - javatpoint. (n.d.). Www.javatpoint.com. 
	//Retrieved February 4, 2023, from https://www.javatpoint.com/java-collections-interview-
	//questions#:~:text=The%20Collection%20is%20an%20interface%20whereas%20Collections%20is

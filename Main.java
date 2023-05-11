package ThreadsProject;

public class Main {
	  public static void main(String[] args) {
	        Food food = new Food();
	        Animal hare = new Animal("Hare", 0, 9, 220, food);
	        Animal tortoise = new Animal("Tortoise", 0, 5, 165, food);
	        Thread hareThread = new Thread(hare);
	        Thread tortoiseThread = new Thread(tortoise);
	        hareThread.start();
	        tortoiseThread.start();
	    }
	}
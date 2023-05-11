package ThreadsProject;

import java.util.Random;

public class Animal implements Runnable {
    private String name;
    private int position;
    private int speed;
    private int restMax;
    private Food food;
    public static boolean winner = false;

    public Animal(String name, int position, int speed, int restMax, Food food) {
        this.name = name;
        this.position = position;
        this.speed = speed;
        this.restMax = restMax;
        this.food = food;
    }

    public void run() {
        System.out.println(name + " starts running.");
        Random rand = new Random();
        while (position < 120 && !winner) {
            try {
                Thread.sleep(rand.nextInt(restMax));
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception caught");
            }
            position += speed;
            System.out.println(name + " is at position " + position);
            if (position >= 120 || Animal.winner) {
                Animal.winner = true;
                System.out.println(name + " wins the race!");
                return;
            }
            food.eat(name, restMax);
        }
    }
}

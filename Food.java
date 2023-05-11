package ThreadsProject;

class Food {
    public synchronized void eat(String name, int restMax) {
        System.out.println(name + " starts eating");
        try {
            Thread.sleep(restMax/2);
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception caught");
        }
        System.out.println(name + " finishes eating");
    }
}

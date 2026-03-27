import java.util.*;

class Main{
    public static void main(String[] args){
        Set<String> bogieIds = new HashSet<>();
        LinkedList<String> train = new LinkedList<>();
        train.add("Engine");
        train.add("Sleeper");
        train.add("AC");
        train.add("Cargo");
        train.add("Guard");
        System.out.println("Initial Train: " + train);
        train.add(2, "Pantry Car");
        System.out.println("After adding Pantry Car: " + train);
        train.removeFirst();
        train.removeLast();
        System.out.println("Final Train Consist: " + train);
    }
}
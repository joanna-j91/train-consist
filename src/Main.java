import java.util.*;
import java.util.stream.Collectors;

class Bogie {
    String name;
    int capacity;
    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
    public String toString() {
        return name + "Capacity: " + capacity;
    }
}

class Main{
    public static void main(String[] args){
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 40));
        bogies.add(new Bogie("General", 90));
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));

        System.out.println("\nAll Bogies:");
        for (Bogie b : bogies) {
            System.out.println(b);
        }
        Map<String, List<Bogie>> groupedBogies = bogies.stream().collect(Collectors.groupingBy(b -> b.name));
        System.out.println("\nGrouped Bogies By Type:");
        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println("\nBogie Type: " + entry.getKey());
            for (Bogie b : entry.getValue()) {
                System.out.println("  " + b);
            }
    }
}
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

        bogies.add(new Bogie("First Class", 40));
        bogies.add(new Bogie("General", 90));
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));

        System.out.println("\nBogies in Train:");
        for (Bogie b : bogies) {
            System.out.println(b);
        }
        int totalSeats = bogies.map(b -> b.capacity).reduce(0, Integer::sum);

        System.out.println("\nTotal Seating Capacity of Train: " + totalSeats);

        }
    }
}
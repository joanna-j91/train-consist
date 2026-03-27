import java.util.*;

class Main{
    public static void main(String[] args){
        List<String> bogies = new ArrayList<>();

        System.out.println("Initial Bogie Count: " + bogies.size());
        bogies.add("Sleeper");
        bogies.add("AC Chair");
        bogies.add("First Class");
        System.out.println("Bogies after adding: " + bogies);
        bogies.remove("AC Chair");
        System.out.println("After removing AC Chair: " + bogies);
        System.out.println("Is Sleeper present? " + bogies.contains("Sleeper"));
    }
}
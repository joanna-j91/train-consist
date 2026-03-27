import java.util.*;

class Main{
    public static void main(String[] args){
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        System.out.println("Unique Bogie IDs: " + bogieIds);
    }
}
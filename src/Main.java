import java.io.*;
import java.util.*;

/* Find top 3 most active users in chats.txt
Strategy :
1. Build a HashMap with usernames as key and their message count as value
2. Write a custom comparator to sort the hashmap in descending order
3. Pick top three entries from the resulting map.
*/
public class Main {

    public static void main(String[] args) {
        File chatFile = new File("res/chats.txt");
        HashMap<String,Integer> userMessageCount = new HashMap<>();
        TreeMap<String, Integer> sortedUserMessageCount;
        try {
            FileReader fReader = new FileReader(chatFile);
            BufferedReader reader = new BufferedReader(fReader);
            String line;
            while((line = reader.readLine()) != null){
                String name = line.substring(line.indexOf("<") + 1, line.indexOf(">"));
                if (userMessageCount.containsKey(name)) {
                    userMessageCount.put(name, userMessageCount.get(name) + 1); //If user exists then increment message count
                } else {
                    userMessageCount.put(name, 1); //First occurrence of a user, set message count to 1
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sortedUserMessageCount = sortMapByValues(userMessageCount); //Put unsorted map through Value Comparator to sort map.

        System.out.println("Top 3 active users with message count : ");
        for(int i=0; i<3; i++){
            System.out.println(sortedUserMessageCount.pollFirstEntry());
        }
    }

    public static TreeMap<String, Integer> sortMapByValues(HashMap<String, Integer> userMessageCount){
        ValueComparator valueComparator = new ValueComparator(userMessageCount);
        TreeMap<String, Integer> sortedUserMessageCount = new TreeMap<>(valueComparator);
        sortedUserMessageCount.putAll(userMessageCount);
        return sortedUserMessageCount;
    }

    static class ValueComparator implements Comparator<String> {
        Map<String, Integer> map;

        public ValueComparator(Map<String, Integer> map) {
            this.map = map;
        }

        public int compare(String a, String b) {
            if (map.get(a) >= map.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}

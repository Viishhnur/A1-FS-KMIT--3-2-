import java.time.Instant;
import java.util.*;
import java.util.stream.*;

public class FS_72_Kavya {
    // Domain class
    static class Message {
        private final String userId;
        private final String content;
        private final Instant timestamp;
        
        //WRITE YOUR CODE HERE
        
        Message(String userId,String content,Instant timestamp)
        {
            this.userId = userId;
            this.content = content;
            this.timestamp = timestamp;
        }
        
        
        public String getUID(){ return this.userId;}
        public String content() { return this.content;}
        public Instant getTS(){ return this.timestamp;}
        
    }

    public static void main(String[] args) {
        Instant now = Instant.now();
        List<Message> messages = List.of(
            new Message("alice", "Hi there!",          now),
            new Message("bob",   "Hello!",             now.plusSeconds(5)),
            new Message("alice", "How are you?",       now.plusSeconds(10)),
            new Message("carol", "Good morning",       Instant.parse("2025-06-17T09:30:00Z")),
            new Message("bob",   "I'm fine.",          Instant.parse("2022-06-17T09:31:00Z")),
            new Message("dave",  "Hey!",               now.plusSeconds(15)),
            new Message("alice", "Let's meet up",      Instant.parse("2024-06-17T09:32:30Z")),
            new Message("bob",   "Sure!",              now.plusSeconds(20))
        );

        //WRITE YOUR CODE HERE
        
        Map<String, Optional<Instant>> map = messages.stream().collect(
            Collectors.groupingBy(
            Message::getUID,
            Collectors.mapping(Message::getTS, Collectors.maxBy(Comparator.naturalOrder()))
        ));
        
        
        List<Map.Entry<String, Optional<Instant>>> sorted_arr = map.entrySet().stream()
            .sorted((entry1, entry2) -> {
                Instant time1 = entry1.getValue().orElse(Instant.MIN);
                Instant time2 = entry2.getValue().orElse(Instant.MIN);
                return time2.compareTo(time1); 
            }).limit(3)
            .collect(Collectors.toList());
            
        List<String> li = new ArrayList<>();
            
        System.out.println("Top 3 active users: ");
        sorted_arr.forEach(entry -> {
            String user = entry.getKey();
            li.add(user);
        });
        System.out.println(li);
                
    }
}
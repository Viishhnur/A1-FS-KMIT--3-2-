// A chat application stores each message with:
//     • user ID (String)
//     • message content (String)
//     • timestamp (Instant)

// Write a program that:
//     1. Counts how many messages each user has sent.
//     2. Determines each user’s last-seen time (the most recent timestamp).
//     3. Sorts all users by:
//          a. last-seen timestamp, newest first
//          b. then by message count, highest first
//     4. Prints the top three users by this combined ordering.
    
    
// Expected Output:
// ----------------
// Top 3 active users: [bob, alice, carol]


import java.time.Instant;
import java.util.*;
import java.util.stream.*;

public class FS_72_Social_Media_Analytics_Streams {
    // Domain class
    static class Message {
        private final String userId;
        private final String content;
        private final Instant timestamp;
        
        //WRITE YOUR CODE HERE
        Message(String userId, String content, Instant timestamp)
        {
            this.userId = userId;
            this.content = content;
            this.timestamp = timestamp;
        }
        
        public String getUserId(){
            return userId;
        }
        
        public String getContent(){
            return content;
        }
        
        public Instant getTimestamp(){
            return timestamp;
        }
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
        Map<String, Long> messageCount = messages.stream()
            .collect(Collectors.groupingBy(Message::getUserId, Collectors.counting()));

        Map<String, Instant> lastSeen = messages.stream()
            .collect(Collectors.groupingBy(Message::getUserId,
            // Message::getTimestamp, (t1, t2)->t1.isAfter(t2)? t1: t2 --> this 1 line replaces below 3 lines
                Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparing(Message::getTimestamp)),
                    optionalMessage -> optionalMessage.map(Message::getTimestamp).orElse(Instant.EPOCH)
                )
            ));

            
        List<String> topUsers = messageCount.keySet().stream()
            .sorted(Comparator.comparing(lastSeen::get, Comparator.reverseOrder())
                .thenComparing(messageCount::get, Comparator.reverseOrder()))
            .limit(3)
            .collect(Collectors.toList());
        System.out.println("Top 3 active users: " + topUsers);
        
        // grouping
        // sorting
        // filtering 
        
    }
}
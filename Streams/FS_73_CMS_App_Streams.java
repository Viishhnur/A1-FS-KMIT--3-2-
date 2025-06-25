// You are given a list of Article objects. Each Article has three fields:
// 	int articleID – a unique identifier
// 	String authorName – the name of the article’s author
// 	int wordCount – the number of words in that article

// Task: Write a Java program that:
// 	1. Computes the average word count for each author across all their articles.
// 	2. Determines which author has the highest average word count.
// 	3. Prints the author’s name along with their average word count (formatted to two decimal places).

// 	If there are no articles, the program should print “No data available.”
	
// Expected Output:
// ----------------
// Top author: Alice Smith (avg words: 636.67)


// import java.util.*;
// import java.util.stream.*;

// public class FS_73_CMS_App_Streams {
//     public static void main(String[] args) 
//     {
//         // Sample data: list of articles
//         List<Article> articles = List.of(
//             new Article(101, "Alice Smith",   520),
//             new Article(102, "Bob Johnson",   330),
//             new Article(103, "Alice Smith",   780),
//             new Article(104, "Carol Davis",   450),
//             new Article(105, "Bob Johnson",   620),
//             new Article(106, "Alice Smith",   610)
//         );

//         //WRITE YOUR CODE HERE
//         List<Integer> avg = articles.average()
        
//     }
// }

// // Non-public helper class in the same file
// class Article {
//     private final int    articleID;
//     private final String authorName;
//     private final int    wordCount;

//    //WRITE YOUR CODE HERE
// }



import java.util.*;
import java.util.stream.*;

public class FS_73_CMS_App_Streams {
    public static void main(String[] args) 
    {
        // Sample data: list of articles
        List<Article> articles = List.of(
            new Article(101, "Alice Smith",   520),
            new Article(102, "Bob Johnson",   330),
            new Article(103, "Alice Smith",   780),
            new Article(104, "Carol Davis",   450),
            new Article(105, "Bob Johnson",   620),
            new Article(106, "Alice Smith",   610)
        );

        //WRITE YOUR CODE HERE
        Map<String,Double> averages = articles.stream().collect(Collectors.groupingBy(Article::getAuthorName, Collectors.averagingInt(Article::getWordCount)));
                                             
        Double topAverage = 0.0;
        String topAuthor = "";
        
        for(Map.Entry<String, Double> entry: averages.entrySet())
        {
            if(entry.getValue()>topAverage)
            {
                topAuthor = entry.getKey();
                topAverage = entry.getValue();
            }
        }
        System.out.printf("Top Author: %s (avg words: %.2f)%n", topAuthor, topAverage);
    }
}

// Non-public helper class in the same file
class Article 
{
    private final int    articleID;
    private final String authorName;
    private final int    wordCount;

   //WRITE YOUR CODE HERE
   Article(int articleID, String authorName, int wordCount)
   {
       this.authorName = authorName;
       this.articleID = articleID;
       this.wordCount = wordCount;
   }
   
   public String getAuthorName()
   {
       return authorName;
   }
   
   public int getWordCount()
   {
       return wordCount;
   }
   
   public int getArticleID()
   {
       return articleID;
   }
}
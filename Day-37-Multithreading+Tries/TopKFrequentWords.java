/*
Sridhar brought his latest Apple iPhone 12 pro. He started his conversation 
with one of his friend on WhatsApp with list of words.

Now it’s our task to find and return what are the most common words 
in the list of words he used in sorted order based on occurrence from 
largest to smallest. If any of words he used are having same occurrence 
then consider the lexicographic order.

You will be given a list of words, you need to print top P number of 
most common used words as described in the statement. 

Input Format:
-------------
Line-1: comma separated line of words, list of words.
Line-2: An integer P, number of words to display. 

Output Format:
--------------
Print P number of most common used words.


Sample Input-1:
---------------
ball,are,case,doll,egg,case,doll,egg,are,are,egg,case,are,egg,are,case
3

Sample Output-1:
----------------
[are, case, egg]

Sample Input-2:
---------------
ball,are,case,doll,egg,case,doll,egg,are,are,egg,case,are,egg,are
3

Sample Output-2:
----------------
[are, egg, case]
 */

import java.util.PriorityQueue;
import java.util.Scanner;
class TrieNode{
    TrieNode[] children;
    boolean eow;
    String word;
    int freq;

    public TrieNode(){
        this.children = new TrieNode[26];
        this.eow = false;
        this.word = "";
        this.freq = 0;
    }
}

class Trie{
    private final TrieNode root;

    public Trie(){
        this.root = new TrieNode(); // create a new trieNode
    }

    public TrieNode getRoot(){
        return root;
    }

    public void insert(String word){
        TrieNode crawl = root;

        for(char ch : word.toCharArray()){
            int idx = ch - 'a';

            if(crawl.children[idx] == null){
                crawl.children[idx] = new TrieNode();
            }

            crawl = crawl.children[idx];
        }

        crawl.eow = true;
        crawl.freq++;
        crawl.word = word; // store the frequency
    }
}
public class TopKFrequentWords {

    private void dfs(TrieNode crawl,PriorityQueue<TrieNode> pq){
        if(crawl == null){
            return;
        }

        if(crawl.eow){
            pq.offer(crawl);
        }
        for(int i = 0 ; i < 26 ; i++){
            dfs(crawl.children[i], pq);
        }
    }
    private void printTopKWords(String[] words,final int k){
        
        // first create a trie and insert words into it
        Trie trie = new Trie();
        for(String word : words){
            trie.insert(word);
        }

        //Now traverse throguh the trie
        PriorityQueue<TrieNode> pq = new PriorityQueue<>((a,b)->{
            // if freq is same
            if(a.freq == b.freq){
                // return lexicographically smallest one if same freq
                return a.word.compareTo(b.word);
            }

            return b.freq - a.freq; // reverse order to get top k words 
        });
        
        
        // Now do a dfs on trie 
        TrieNode crawl = trie.getRoot();
        dfs(crawl,pq);

        // Now print top k elements
        int cnt = 0;
        while(cnt++ < k){
            System.out.print(pq.poll().word + " ");
        }
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String[] words = sc.nextLine().split(",");
            int k = sc.nextInt();
            
            new TopKFrequentWords().printTopKWords(words,k);
        }
        
    }
}

import java.util.Scanner;

class TrieNode {
    TrieNode[] children;
    boolean eow;

    public TrieNode() {
        this.children = new TrieNode[26];
        this.eow = false;
    }

    public void setEnd() {
        eow = true;
    }

    public void unsetEnd() {
        eow = false;
    }

    public boolean isEnd() {
        return eow;
    }

    public boolean isEmpty() {
        for (TrieNode link : children) {
            if (link != null)
                return false;
        }
        return true;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String word) {
        TrieNode crawl = root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';

            if (crawl.children[idx] == null) {
                crawl.children[idx] = new TrieNode();
            }

            crawl = crawl.children[idx];
        }

        crawl.eow = true;

    }

    public boolean search(String word) {
        TrieNode crawl = root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';

            if (crawl.children[idx] == null) {
                return false;
            }

            crawl = crawl.children[idx];
        }

        return crawl.eow;

    }

    public boolean startsWith(String prefix) {
        TrieNode crawl = root;

        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';

            if (crawl.children[idx] == null) {
                return false;
            }

            crawl = crawl.children[idx];
        }

        return true;

    }

    private boolean deleteHelper(int idx, TrieNode curr_node, String word) {
        if (idx == word.length()) {
            curr_node.eow = false;
            return curr_node.isEmpty(); // If no children, node can be removed
        }

        char ch = word.charAt(idx);
        TrieNode next = curr_node.children[idx];

        if(next == null) return false;
        
        /*
         !curr_node.isEnd() → this node is not the end of any other word.

        curr_node.isEmpty() → it has no other children.
         */
        boolean shouldDeleteNextNode = deleteHelper(idx + 1, curr_node.children[idx], word);
        if (shouldDeleteNextNode) {
            curr_node.children[ch - 'a'] = null;
            return !curr_node.isEnd() && curr_node.isEmpty();
        }
        return false;
    }

    public boolean delete(String word) {
       
        return deleteHelper(0, root, word);
    }

}


public class Trie_Imp{
    public static void main(String[] args) {
        Trie trie = new Trie();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter strings to insert (space-separated):");
        String[] words = sc.nextLine().split(" ");
        for (String word : words) {
            trie.insert(word);
        }
        System.out.println("Strings are inserted in Trie.");

        System.out.print("Enter a word to search: ");
        String searchWord = sc.nextLine();
        System.out.println(trie.search(searchWord));

        System.out.print("Enter a prefix to check: ");
        String prefix = sc.nextLine();
        System.out.println(trie.startsWith(prefix));

        System.out.print("Enter a word to delete: ");
        String deleteWord = sc.nextLine();
        if (trie.delete(deleteWord)) {
            System.out.println("Deleted successfully.");
        } else {
            System.out.println("Word not found in Trie.");
        }

        sc.close();
    }
}
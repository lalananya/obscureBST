package BinaryTree;

import java.util.HashMap;

/*
 * we have an array of words
 * then we try to built a tree out of it
 * "the" " that"
 *  t
 *  h
 * e  a
 *      t
 * each node can have max of 26 children (a to z)
 * stored alphabetically
 */
public class Trie {
    
    TrieNode root;

    Trie(){
        root = null;
        root.children = new HashMap<Character, TrieNode>();
    }
    class TrieNode {
        private HashMap<Character, TrieNode> children; /* there are different ways to use it, hashmap / arrays anythingh */
        TrieNode(){
            this.children = new HashMap<Character, TrieNode>();
        }
    }

    void insert(String word){
        TrieNode current = root;
        for(int i=0; i<word.length(); i++){
            char currentChar = word.charAt(i);
            TrieNode node = current.children.get(currentChar);
            if(node == null){
                node = new TrieNode();
                current.children.put(currentChar, node);
            }
            current = node;
        }
    }

    public static void main(String[] args) {
        Trie obj = new Trie();
        String words[] = {"the", "a", "there", "answer", "any", "by", "bye", "their"};

        for (int i = 0; i < words.length ; i++) {
            obj.insert(words[i]);
        }
    }
}

/*
Mansill Smith
ID: 1341291
*/

import java.io.IOException;
import java.util.ArrayList;

/*
Compresses a file using the LZW algorithm
*/
public class LZWencode{

    //The data structure which adaptively records the patterns
    private class Trie{
        //Stores the value of the current node
        private byte value;
        //Stores the next nodes of the Trie
        private ArrayList<Trie> next;
        //Stores the index of the pattern
        private int index;

        //Constructs a Trie
        public Trie(byte value, int index){
            this.value = value;
            this.next = new ArrayList<Trie>();
            this.index = index;
        }

        //Gets the value of the node
        public byte GetValue(){
            return value;
        }

        //Gets the index of the node
        private int GetIndex(){
            return index;
        }

        //Gets the next node which the pattern exists in
        public Trie PatternFound(byte pattern){
            for (Trie curr : this.next){
                if (curr.GetValue() == pattern){
                    return curr;
                }
            }
            return null;
        }

        //Adds a new trie to the current trie
        public void AddTrie(byte pattern, int index){
            this.next.add(new Trie(pattern, index));
        }

        @Override
        public String toString(){
            return this.GetValue() + "," + Integer.toString(this.GetIndex());
        }
    }

    //Gets the next byte from the the standard input
    private static int nextByte(){
        int nextValue = -1;
        try{
            nextValue = System.in.read();
        }
        catch (IOException e){
            System.err.print(e);
        }
        return nextValue;
    }
    public static void main(String[] args){
        //Initialise the trie with the possible starting values
        //The values of this node of the trie doesn't matter
        LZWencode lzw = new LZWencode();
        Trie thisTrie = lzw.new Trie((byte)0,0);
        int index = 0;
        for (int i = 0; i < 256; i++){
            thisTrie.AddTrie((byte)i, index);
            index ++;
        }

        Trie prevTrie = null;
        Trie nextTrie = null;
        int b = nextByte();
        while(true){
            if(b == -1){
                System.out.println(nextTrie.GetIndex());
                break;
            }

            //Enter the trie
            prevTrie = thisTrie;
            nextTrie  = thisTrie.PatternFound((byte)b);

            // System.out.println(nextTrie + "\n");
            
            while(true){
                b = nextByte();
                if(b == -1){
                    //Should hopefully break out of the previous loop too
                    break;
                }

                //Go down to the next layer of the trie
                prevTrie = nextTrie;
                nextTrie = nextTrie.PatternFound((byte)b);

                // System.out.println(prevTrie);
                // System.out.println(nextTrie);

                //If the pattern wasn't found
                if (nextTrie == null){
                    //Add the new pattern to the prevTrie
                    prevTrie.AddTrie((byte)b, index);
                    index++;

                    //Print the phrase number
                    System.out.println(prevTrie.GetIndex());

                    //Phrase b is then inserted back into the top of the trie
                    break;
                }
            }
        }
    }

}
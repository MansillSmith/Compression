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

        //Adds a new pattern into the trie
        public int AddPattern(int pattern, int index){
            //Tests if the pattern already exists
            for (Trie curr : this.next){
                //If the pattern already exists, go down the tree
                if (curr.GetValue() == pattern){
                    //Read the next value
                    int nextValue = nextByte();

                    if (nextValue != -1){
                        return curr.AddPattern(nextValue, index);
                    }
                    else{
                        //TODO: return this index?
                        //Does that mean the file has ended?
                        //What happens when the file ends?
                        return -1;
                    }
                }
            }
            //The pattern doesn't exists, so add it to the trie
            this.next.add(new Trie(pattern, index));
            return this.GetIndex();
        }
    }

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
            thisTrie.AddPattern((byte)i, index);
            index ++;
        }

        while(true){
            int b = nextByte();
            if(b == -1){
                break;
            }

            //Add the pattern to the trie
            int num = thisTrie.AddPattern(b, index);
            //Print the index
            System.out.println(num);
            index ++;
            break;
        }
    }

}
/*
Mansill Smith
ID: 1341291
*/

import java.util.ArrayList;

/*
Compresses a file using the LZW algorithm
*/
class LZWencode{

    private class Trie{
        //The structure which stores the patterns in the file
        private class TrieNode{
            //Stores the value of the current node
            private byte value;
            //Stores the next nodes of the Trie
            private ArrayList<TrieNode> next;
            //Stores the index of the pattern
            private int index;
            //Stores the current index count;
            //private static int indexCount = 0;

            //Constructs a Trie
            public TrieNode(byte value, int index){
                this.value = value;
                this.next = new ArrayList<TrieNode>();
                this.index = index;
            }

            //Gets the value of the node
            public byte GetValue(){
                return value;
            }
        }

        private int index;
        private TrieNode head;
    }

    public static void main(String[] args){
        while(true){
            int b = -1;
            try{
                b = System.in.read();
            }
            catch (Exception e){
                System.out.print(e);
                break;
            }
            if(b == -1){
                break;
            }
            System.out.print(b + "\n");
        }
    }

}
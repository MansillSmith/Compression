import java.io.BufferedInputStream;
import java.util.*;

class LZWunpack{
    //Calculates the number of bits needed to represent the phrase number
    public static int numBitsOfNumber(int x){
        return (int)Math.ceil(Math.log(x) / Math.log(2));
    }

    public static int bitsInNum = 0;
    public static int outputNumber = 0;

    public static void main(String [] args){
        int numberOfPhrases = 256;       

        try{
            BufferedInputStream reader = new BufferedInputStream(System.in);
            int currentByte;
            int remainingBits = 0;
            int numBits = numBitsOfNumber(numberOfPhrases);

            while((currentByte = reader.read()) != -1){
                if(numBits >= 8){
                    addToNumber(currentByte, 8);
                    numBits -= 8;
                    if(numBits == 0){
                        System.out.println(outputNumber);
                        outputNumber = 0;
                        bitsInNum = 0;
                    }
                }
                else{
                    int mask = (2^numBits - 1);
                    int newBits = currentByte & mask;
                    addToNumber(newBits, numBits);
                    System.out.println(outputNumber);
                    currentByte = currentByte >>> 3;
                    outputNumber = 0;
                    bitsInNum = 0;

                    remainingBits = 8 - numBits;
                    numBits = numBitsOfNumber(numberOfPhrases);
                    numBits -= remainingBits;
                    addToNumber(currentByte, remainingBits);
                }
                numberOfPhrases++;
            }

        }catch(Exception e){

        }
        
    }

    private static void addToNumber(int x, int numBits){
        int leftShift = x << bitsInNum;
        outputNumber = outputNumber | leftShift;
        bitsInNum += numBits;
    }
}
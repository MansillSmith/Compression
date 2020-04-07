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

            //Read one byte at a time
            while((currentByte = reader.read()) != -1){
                //If you need to use the whole byte
                if(numBits >= 8){
                    //Add the byte to the current output number
                    addToNumber(currentByte, 8);
                    //Remove 8 from
                    numBits -= 8;
                    //If there are no more bits in the number
                    if(numBits == 0){
                        System.out.println(Integer.toString(outputNumber));
                        //Reset the output number and bits currently in the number
                        outputNumber = 0;
                        bitsInNum = 0;

                        //Increase the number of phrases
                        numberOfPhrases++;
                        //Get the number of bits in the next number
                        numBits = numBitsOfNumber(numberOfPhrases);
                    }
                }
                else{
                    //Create a mask for the number of bits needed to read
                    int mask = (2^numBits - 1);
                    //Get only the bits required
                    int newBits = currentByte & mask;
                    //Add to the output number
                    addToNumber(newBits, numBits);
                    //Output the number
                    System.out.println(outputNumber);
                    //Right shift the current byte by the number of bits already read
                    currentByte = currentByte >>> numBits;
                    //Reset variable
                    outputNumber = 0;
                    bitsInNum = 0;

                    //Get the number of bits remaining in the byte
                    remainingBits = 8 - numBits;
                    //Get the number of bits in the next number
                    numBits = numBitsOfNumber(numberOfPhrases);
                    //Remove the number of bits read from the number of bits in the number
                    numBits -= remainingBits;
                    //Add to the output number
                    addToNumber(currentByte, remainingBits);

                    //Increase the number of phrases
                    numberOfPhrases++;
                }
                
            }

        }catch(Exception e){
            System.err.println(e);
        }
        
    }

    private static void addToNumber(int x, int numBits){
        //Left shift the bytes to be added to the number by the number of bits already in the number
        int leftShift = x << bitsInNum;
        //OR them together to add the new bits to the output number
        outputNumber = outputNumber | leftShift;
        //Increase the number of bits in the number
        bitsInNum += numBits;
        System.err.println(outputNumber);
    }
}
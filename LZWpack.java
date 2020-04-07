/*
Mansill Smith
ID: 1341291
*/

import java.util.Scanner;

/*
Packs the output of LWZencod into the minimal number of bits possible
*/
public class LZWpack{

    //Calculates the number of bits needed to represent the phrase number
    public static int numBitsOfNumber(int x){
        return (int)Math.ceil(Math.log(x) / Math.log(2));
    }

    //Calculates if a byte should be written
    public static Boolean ConditionToWrite(Boolean clearBuffer, int pointerInBuffer){
        if (clearBuffer){
            return pointerInBuffer >= 0;
        }
        else{
            return pointerInBuffer >= 8;
        }
    }

    public static void main(String [] args){
        //Creates a scanner to read the standard input
        Scanner scanner = new Scanner(System.in);

        //Stores the buffer
        int buffer = 0;
        int pointerInBuffer = 0;
        int numberOfPhrases = 256;

        while (true){
            //Reads a line from standard input
            String line = "";
            Boolean clearBuffer = false;
            try{
                line = scanner.nextLine();

                //Add the number to the buffer
                int value = Integer.parseInt(line);
                int numBits = numBitsOfNumber(numberOfPhrases);
            
                //Left shift the new value by the number of bits currently in the buffer
                value = value << pointerInBuffer;

                //Add the value to the buffer
                buffer = buffer | value;
                pointerInBuffer += numBits;

            }
            catch (Exception e){
                clearBuffer = true;
            }

            //If there is more than a byte of information to write
            //While incase there is multiple bytes to write
            while (ConditionToWrite(clearBuffer, pointerInBuffer)){
                //Outputs a byte to standard output
                byte[] b = {(byte)buffer};
                try{
                    System.out.write(b);
                }
                catch (Exception e){
                    System.err.println(b);
                }
                //Removes the byte which was just written
                buffer = buffer >>> 8;
                pointerInBuffer -= 8;
            }

            //Exits the loop if there was nothing more to read
            if(clearBuffer){
                break;
            }

            numberOfPhrases++;
        }
        scanner.close();
    }
}
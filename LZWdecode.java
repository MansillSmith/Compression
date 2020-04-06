import java.io.*;
import java.util.*;

//The LZW dictionary stores the entire output for each phrase, rather than a reference to another phrase. It trades memory for speed
class LZWdecode{
    /*public static void main(String[] args) {
        int inputPhrase;

        List<List<Byte>> storedPhrases = new ArrayList<>();
        for(int i = 0; i <= 255; i++){
            storedPhrases.add(new ArrayList<Byte>(Arrays.asList((byte)i)));
        }

        String line;
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while((line = reader.readLine()) != null){
                inputPhrase = Integer.parseInt(line);
                int lastIndex = storedPhrases.size() - 1;

                //If the phrase number references a phrase that is not yet in the dictionary then the encoding is not correct
                if(inputPhrase > (lastIndex)){
                    throw new Exception("Incorrect Encoding");
                }
                else{
                    //If the phrase is not the first new phrase (It is not the 256 index)
                    if((storedPhrases.size() > 256) || (inputPhrase == lastIndex)){
                        //Get the first byte of the input phrase
                        Byte firstByte = storedPhrases.get(inputPhrase).get(0);
                        //Add the first byte to the bytes of the most recently added phrase (Completing the phrase value)
                        storedPhrases.get(lastIndex).add(firstByte);
                    }

                    //Get the bytes specified by the input phrase
                    List<Byte> copyBytes = new ArrayList<Byte>(storedPhrases.get(inputPhrase));
    
                    //Add new phrase to list
                    storedPhrases.add(copyBytes);

                    byte[] byteArray = new byte[copyBytes.size()];
    
                    for(int i = 0; i < copyBytes.size(); i++){
                        byteArray[i] = copyBytes.get(i);
                    }

                    for(byte b : byteArray){
                        System.out.print(Character.toString((char)b));
                    }
                }
            }
            reader.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }*/

    static List<phraseClass> storedPhrases = new ArrayList<>();

    public static void main(String[] args){
        

        for(int i = 0; i <= 255; i++){
            storedPhrases.add(new phraseClass(-1, (byte)i));
        }

        String line;
        int inputPhrase;
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while((line = reader.readLine()) != null){
                inputPhrase = Integer.parseInt(line);
                int lastIndex = storedPhrases.size() - 1;

                //If the phrase number references a phrase that is not yet in the dictionary then the encoding is not correct
                if(inputPhrase > (lastIndex)){
                    throw new Exception("Incorrect Encoding");
                }
                else{
                    //If the phrase is not the first new phrase (It is not the 256 index)
                    if((storedPhrases.size() > 256) || (inputPhrase == lastIndex)){
                        //Get the first byte of the input phrase
                        byte firstByte = getFirstByte(inputPhrase);
                        //Add the first byte to the bytes of the most recently added phrase (Completing the phrase value)
                        storedPhrases.get(lastIndex).byteValue = firstByte;
                    }

                    storedPhrases.add(new phraseClass(inputPhrase, (byte)0));

                    printPhrase(inputPhrase);
                }
            }
            reader.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }

    private static byte getFirstByte(int phraseNumber){
        int nextPhrase = phraseNumber;
        phraseClass temp = null;

        while (nextPhrase != -1){
            temp = storedPhrases.get(nextPhrase);
            nextPhrase = temp.previousPhrase;
        }

        return temp.byteValue;
    }

    private static void printPhrase(int phraseNumber){
        if(phraseNumber != -1){
            printPhrase(storedPhrases.get(phraseNumber).previousPhrase);
            byte[] b = {storedPhrases.get(phraseNumber).byteValue};
            try{
                System.out.write(b);
            }
            catch (IOException e){
                System.err.println(e);
            }
            //System.out.print(Character.toString((char)storedPhrases.get(phraseNumber).byteValue));
        }
    }
}

class phraseClass{

    public int previousPhrase;
    public byte byteValue;

    public phraseClass(int prev, byte b){
        previousPhrase = prev;
        byteValue = b;
    }
}
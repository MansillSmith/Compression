import java.io.*;
import java.util.*;

//The LZW dictionary stores the entire output for each phrase, rather than a reference to another phrase. It trades memory for speed
class LZWdecode{
    public static void main(String[] args) {
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

                    System.out.print(new String(byteArray));
                }
            }
            reader.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
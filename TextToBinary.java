import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextToBinary {

    public static void main(String[] args) {
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));

        try{
            String line = "";
            while((line = inputStream.readLine()) != null){
                byte[] b = {(byte)Integer.parseInt(line)};
                //System.err.println((char)b);
                //System.out.wr
                System.out.write(b);
            }
        }
        catch(IOException e){
            System.err.println(e);
        }

    }
}
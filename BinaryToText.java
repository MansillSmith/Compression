import java.io.IOException;

public class BinaryToText{
    public static void main(String[] args){

        while (true){
            int i = -1;
            try{
                i = System.in.read();
            }
            catch(IOException e){
                System.err.println(e);
            }
            if (i == -1){
                break;
            }
            System.out.println(i);
        }
    }
}
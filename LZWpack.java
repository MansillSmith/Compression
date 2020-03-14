import java.util.Scanner;

public class LZWpack{

    public static void main(String [] args){
        //Creates a scanner to read the standard input
        Scanner scanner = new Scanner(System.in);

        //Stores the buf
        int buffer = 0;
        while (true){
            //Reads a line from standard input
            String line = "";
            try{
                line = scanner.nextLine();
            }
            catch (Exception e){
                break;
            }
            System.out.println(line);
        }

        scanner.close();
    }
}
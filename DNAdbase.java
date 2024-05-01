import java.io.File;
import java.io.IOException;
/*
∗ @file: DNAdbase. java
∗ @description: This program implements the class DNAdbase. It parses in a text file and a command string, and calls the DNAparser class.
∗ @author: Julia Jiang
∗ @date: April 21 , 2024
∗ @acknowledgement : N/A
 */
public class DNAdbase {

    private static int hashSize;
    private static File command;
    private static DNAparser parse;

    //main function
    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            command = new File(args[0]);
            hashSize = Integer.parseInt(args[1]);
            parse = new DNAparser(command, hashSize);
            parse.parse();
        }
        else {
            System.out.println("Please input a correctly formatted command");
        }
    }
    //get the parser
    public DNAparser getParser() {
        return parse;
    }
}

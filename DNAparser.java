import java.io.BufferedWriter;
import  java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
∗ @file: DNAparser. java
∗ @description: This program implements the class DNAparser. It parses commands from a file and writes to the results.txt file with correct formats.
∗ @author: Julia Jiang
∗ @date: April 21 , 2024
∗ @acknowledgement : N/A
 */
public class DNAparser {

    private File com;
    private HashFunction hashTable1;

    //constructor:
    public DNAparser(File c, int size) throws IOException {
        com = c;
        this.hashTable1 =  new HashFunction(size);
    }

    //Parses commands from a file and writes to the results.txt with correct formats.
    public boolean parse() throws IOException {


        Scanner scanner = new Scanner(com);

//****************** Your Codes Here ******************/
        //this looks ok so far
        //for debugging, i would add some print statements just to make sure that you are parsing correctly
        String line;
        ArrayList<String> list = new ArrayList<>();

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            //removes the empty lines, removes trailing spaces, and replaces consecutive spaces with single space.
            if (!line.trim().isEmpty()){
                line = line.trim().replaceAll("\\s+"," ");
                list.add(line);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            line = list.get(i);
            System.out.println("The line is: " + line);
            String[] command = line.split(" "); //splits the commands into a string arraylist


            if (command[0].equals("insert")){
                String l = list.get(i+1);
                command = Arrays.copyOf(command, 4);
                command[3] = l;
                operate_HT(command);
            }

            if (command[0].equals("print")) {
                operate_HT(command);
            }

            if (command[0].equals("remove")) operate_HT(command);
            if(command[0].equals("search")) operate_HT(command);

        }

        return false;
    }
    //This method takes a string array list and gives command to different methods.
    public void operate_HT(String[] command) throws IOException {
        switch(command[0]){
            case "insert":
                String id = command[1];
                String sequence = command[3];
                hashTable1.insert(id, sequence);
                break;

            case "remove":
                String id2 = command[1];
                HashObject ho = hashTable1.remove(id2);
                if (ho!=null) writeToFile( "results.txt","Sequence Removed "+ho.getId()+":"+ho.getSeq());
                break;

            case "search":
                String id3 = command[1];
                HashObject hos = hashTable1.search(id3);
                if (hos!=null) writeToFile( "results.txt","Sequence Found: "+hos.getSeq());
                else writeToFile( "results.txt","SequenceID "+ id3 + " not found");
                break;

            case "print":
                HashObject[] hop = hashTable1.print();
                writeToFile( "results.txt","BREAK DOWN START");
                for(int i = 0; i < hashTable1.getHashTableSize(); i++){

                    if (hop[i] != null && hop[i].getTombstone() == false) {
                        writeToFile("results.txt", hop[i].getId() + ": hash slot [" + i+ "]");
                    }
                }
                writeToFile( "results.txt","END");
                break;
        }
    }
    //This method writes to a file.
    public void writeToFile(String filePath, String content) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

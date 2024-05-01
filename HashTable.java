/*
∗ @file: HashTable. java
∗ @description: This program implements the interface HashTable. It defines five methods in HashTable.
∗ @author: Julia Jiang
∗ @date: April 21 , 2024
∗ @acknowledgement : N/A
 */
public interface HashTable<T extends Comparable<T>, K> {
    //Inserts a generic data type named id and returns an integer data type.
    public int insert(T id, T sequenceID);

    //Removes a generic data type sequenceID and returns a generic data type.
    // It would return a complete sentence with the removed sequence ID, if sequenceID exists;
        //or a complete sentence with error message, if it doesn't exist.
    public K remove(T sequenceID);

    //Hashes a generic data type sequenceID to the HashTable and returns an integer value the index of the table.
    public int hash(T sequenceID);

    //Searches a generic sequenceID data type and prints out the sequence in HT with the sequenceID, if found.
    public K search(T id);

    //Prints out a list of all sequence IDs in the data type.
        //For each sequence ID, the message will indicate which slot of the HT it's stored in.
    public K[] print();
}

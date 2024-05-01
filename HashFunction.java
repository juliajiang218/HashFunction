/*
∗ @file: HashFunction. java
∗ @description: This program implements the class HashFunction.
* It is a subtype of the interface HashTable and implements all the methods defined in the interface.
∗ @author: Julia Jiang
∗ @date: April 21 , 2024
∗ @acknowledgement : Many thanks to Lily & professor Chen for explaining the high level concept of Hash, sfold relationship and search function.
 */

public class HashFunction implements HashTable<String, HashObject> {
    private HashObject[] hashes;
    private int hashTableSize;


    // Constructor: takes an integer value hashSize, which defines the size of the hashtable.
    public HashFunction(int hashSize) {
        this.hashTableSize = hashSize;
        this.hashes = new HashObject[hashSize];

    }

    //This method converts a string key into an integer index (based on tableSize and string key) for hashing.
    private long sfold(String s, int m) {
        long sum = 0, mul = 1;
        for (int i = 0; i < s.length(); i++) {
            mul = (i % 4 == 0) ? 1 : mul * 256; //uses ternary operator to assign values & interprets each of the four-byte chunk as a single long integer value
            sum += s.charAt(i) * mul; //four-byte chunks are added together & charAt(i) produces a unicode long value.
        }
        return (int) (Math.abs(sum) % m); //resulting sum converted to the range 0 -( m-1 )using the modulus operator
    }


    //takes in the sequence ID (value from before) and produces an index value related to hashTable.
    //helper function
    public int hash(String id) {
        //call sfold in here, cast sfold to an int
        // because the value you place the long into the hash table has to be an int (ex: (int)....)
        int index = (int) sfold(id, this.hashTableSize);
        return index;
    }

    //This method takes a key-value pair and inserts it into the Hash Table.
    //it hashes the key(id) into an integer value, tests the right position in HT, and inserts the value(seq) into the HT(an array).
    public int insert(String id, String sequence) {
        int pos = hash(id); //original hash value (pos)
        int bucket = pos / 32;
        int reset = 32 * bucket;
        //tests if it's the right position in HT
        for (int i = 0; i < 32; i++){
            if (hashes[pos] == null){
                 hashes[pos] = new HashObject(id, sequence);
                 System.out.println("The object inserted is "+ hashes[pos].getId() + " at "+ pos);
                 return pos;
            }
            if ((pos+1)%32 == 0){
                pos = reset;
            }
            else{
                pos++;
            }
        }
        return pos;
    }


    //This method removes a given id and returns the removed HashObject, if exists.
    public HashObject remove(String id) {
        int pos = hash(id);
        int bucket = pos / 32;
        int reset = 32 * bucket;
        //tests if it's the right position in HT
        for (int i = 0; i < 32; i++){
            if (hashes[pos] == null){
                System.err.println("Not found");
            }
            if (hashes[pos].getId().equals(id)){
                hashes[pos].changeTombstone();
                return hashes[pos];
            }
            if ((pos+1)%32 == 0){
                pos = reset;
            }
            else{
                pos++;
            }
        }
        return null;
    }

    //This method returns an array of HashObjects.
    public HashObject[] print() {

        return hashes;
    }

    //This method searches the HashObject associated with the string id and returns the related HashObject, if found.
    //If not found, it prints an error message and returns null.
    public HashObject search(String id) {
        int pos = hash(id); //original hash value (pos)
        int bucket = pos / 32;
        int reset = 32 * bucket;
        //tests if it's the right position in HT
        for (int i = 0; i < 32; i++){
            if (hashes[pos] == null){
                continue;
            }
            else if (hashes[pos].getId().equals(id) && hashes[pos].getTombstone() == false){
                return hashes[pos];
            }
            if ((pos+1)%32 == 0){
                pos = reset;
            }
            else{
                pos++;
            }
        }
        System.err.println(id+ " Not found");
        return null;
    }
    //This method returns the hashTableSize
    public int getHashTableSize(){
        return this.hashTableSize;
    }
}

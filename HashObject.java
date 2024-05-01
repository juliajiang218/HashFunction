/*
∗ @file: HashObject. java
∗ @description: This program implements the class HashObject.
* It supports several basic methods of the HashObject class.
∗ @author: Julia Jiang
∗ @date: April 21 , 2024
∗ @acknowledgement : N/A
 */
public class HashObject {

    private String id;
    private String seq;
    private boolean tombstone;


    // Constructor takes a string id and a string seq.
    public HashObject(String id, String seq) {
        this.id = id;
        this.seq = seq;
        this.tombstone = false;
    }
    
    // Get the ID from the HashObject
    public String getId() {
        return this.id;
    }
    
    // Get the sequence from the HashObject
    public String getSeq() {
        return this.seq;
    }
    
    // change the tombstone value to true
    public void changeTombstone(){
        this.tombstone = true;
    }
    //get the tombStone value
    public boolean getTombstone(){
        return this.tombstone;
    }

    //print the HashObject
    public void print(){
        System.out.println(this.id + ": hash slot [ "+this.seq+" ]");
    }
}

Implemented a simple database system for DNA sequences, which includes a hash table using a bucket hash to query searches by a sequence identifier.
Stored data records consisting of two parts corresponding to the key and value of the hashtable:
  1. The first part (key) will be the identifier (sequenceIDs), which is a relatively short character string from the DNA alphabet (A, C, G, T).
  2. The second part (value) is the DNA sequence, which is a relatively long string (could be thousands of characters) from the DNA alphabet (A, C, G, T).

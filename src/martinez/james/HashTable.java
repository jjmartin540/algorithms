package martinez.james;

import java.math.BigInteger;
import java.util.LinkedList;

/**
 * Division method based hashTable with linear chaining
 * Created by james on 4/18/16.
 */
public class HashTable {
    HashTableNode[] elements;
    private int randomHugePrime = 79999;

    public HashTable(int maxCollisions, int capacity) {
        int minimum = (int) Math.floor(capacity / maxCollisions);
        while (minimum < capacity) {
            // Uses some Miller-Rabin test, it's no AKS (2002) but it
            // is a pretty darn good approximation
            // Also skip the even numbers because duh...
            // Note: I could have loaded all 100MB+ of primes less than 2^32
            //       But that felt slow and a waste of memory/CPU/space
            if (minimum % 2 != 0 && BigInteger.valueOf(minimum).isProbablePrime(64)) {
                randomHugePrime = minimum;
                break;
            } else {
                minimum = minimum + 1;
            }
        }
        elements = new HashTableNode[randomHugePrime];
    }

    public String getElement(int key) {
        HashTableNode node = elements[key % randomHugePrime];
        if (node.key == key && node.value != null) {
            return elements[key % randomHugePrime].value;
        } else {
            if (node.collisions == null) {
                return null;
            }
            HashTableNode hit = null;
            for (HashTableNode n : node.collisions) {
                if (n.key == key) {
                    hit = n;
                    break;
                }
            }
            return hit != null ? hit.value : null;
        }
    }

    public void setElement(int key, String value) {

        if (elements[key % randomHugePrime] == null) {
            elements[key % randomHugePrime] = new HashTableNode(key, value);
        } else {
            elements[key % randomHugePrime].setValue(key, value);
        }
    }

    public void deleteElement(int key) {
        HashTableNode node = elements[key % randomHugePrime];
        if (node.key == key && node.value != null) {
            elements[key % randomHugePrime] = null;
        } else {
            if (node.collisions != null) {
                for (HashTableNode n : node.collisions) {
                    if (n.key == key) {
                        node.collisions.remove(n);
                        break;
                    }
                }
            }
        }
    }
    //k mod m where m > n
    // linear chain after

    //multiplicative method


    //universal perfect hashing
    private class HashTableNode {
        public int key;
        public String value;
        LinkedList<HashTableNode> collisions;

        public HashTableNode(int key, String value) {
            this.key = key;
            this.value = value;
            this.collisions = null;
        }

        public void setValue(int key, String value) {
            if (this.value == null) {
                this.key = key;
                this.value = value;
            } else {
                if (collisions == null) {
                    collisions = new LinkedList<>();
                    collisions.add(new HashTableNode(key, value));
                }
            }
        }
    }
}

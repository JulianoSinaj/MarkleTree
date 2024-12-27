package it.unicam.cs.asdl2425.mp1;

/**
 * Rappresenta un nodo di un albero di Merkle.
 * 
 * @author Luca Tesei, Marco Caputo (template) 
 * **JULIANO ,SINAJ, juliano.sinaj@studenti.unicam.it** (implementazione)
 */
public class MerkleNode {
    private final String hash; // Hash associato al nodo.
    private final MerkleNode left; // Figlio sinistro del nodo.
    private final MerkleNode right; // Figlio destro del nodo.

    /**
     * Costruisce un nodo Merkle foglia con un valore di hash, quindi,
     * corrispondente all'hash di un dato.
     *
     * @param hash
     *                 l'hash associato al nodo.
     */
    public MerkleNode(String hash) {
        this(hash, null, null);
    }

    /**
     * Costruisce un nodo Merkle con un valore di hash e due figli, quindi,
     * corrispondente all'hash di un branch.
     *
     * @param hash , l'hash associato al nodo.
     * @param left, il figlio sinistro.
     * @param right, il figlio destro.
     */
    public MerkleNode(String hash, MerkleNode left, MerkleNode right) {
        this.hash = hash;
        this.left = left;
        this.right = right;
    }

    /**
     * Restituisce l'hash associato al nodo.
     *
     * @return l'hash associato al nodo.
     */
    public String getHash() {
        return hash;
    }

    /**
     * Restituisce il figlio sinistro del nodo.
     *
     * @return il figlio sinistro del nodo.
     */
    public MerkleNode getLeft() {
        return left;
    }

    /**
     * Restituisce il figlio destro del nodo.
     *
     * @return il figlio destro del nodo.
     */
    public MerkleNode getRight() {
        return right;
    }

    /**
     * Restituisce true se il nodo è una foglia, false altrimenti.
     *
     * @return true se il nodo è una foglia, false altrimenti.
     */
    public boolean isLeaf() {
        if (this.left == null && this.right == null) {
            return true; //if both children are null.
        } else {
            return false;//if the node has at least one child.
        }
    }
    @Override
    public String toString() {
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false; //if the object is a null
        }
        if (!(obj instanceof MerkleNode)) {
            return false; //if the object is not a MerkleNode
        }
        MerkleNode other = (MerkleNode) obj;
        if (this.hash.equals(other.hash)) {
            return true; //if the hash values are equal
        } else {
            return false; //if the hash values are not equal 
        }
    }

    @Override
    public int hashCode() {
        if (this.hash != null) {
            return this.hash.hashCode();
        } else {
            return -1; // -1 if the hash is null
        }
    }
}
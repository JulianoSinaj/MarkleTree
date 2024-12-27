package it.unicam.cs.asdl2425.mp1;

import java.util.ArrayList;
import java.util.Iterator;

//TODO inserire gli import della Java SE che si ritengono necessari

import java.util.ConcurrentModificationException;
/**
 * Una classe che rappresenta una lista concatenata con il calcolo degli hash
 * MD5 per ciascun elemento. Ogni nodo della lista contiene il dato originale di
 * tipo generico T e il relativo hash calcolato utilizzando l'algoritmo MD5.
 *
 * <p>
 * La classe supporta le seguenti operazioni principali:
 * <ul>
 * <li>Aggiungere un elemento in testa alla lista</li>
 * <li>Aggiungere un elemento in coda alla lista</li>
 * <li>Rimuovere un elemento dalla lista in base al dato</li>
 * <li>Recuperare una lista ordinata di tutti gli hash contenuti nella
 * lista</li>
 * <li>Costruire una rappresentazione testuale della lista</li>
 * </ul>
 *
 * <p>
 * Questa implementazione include ottimizzazioni come il mantenimento di un
 * riferimento all'ultimo nodo della lista (tail), che rende l'inserimento in
 * coda un'operazione O(1).
 *
 * <p>
 * La classe utilizza la classe HashUtil per calcolare l'hash MD5 dei dati.
 *
 * @param <T>
 *                il tipo generico dei dati contenuti nei nodi della lista.
 * 
 * @author Luca Tesei, Marco Caputo (template) 
 * **JULIANO, SINAJ, juliano.sinaj@studenti.unicam.it** (implementazione)
 * 
 */
public class HashLinkedList<T> implements Iterable<T> {
    private Node head; // Primo nodo della lista
    private Node tail; // Ultimo nodo della lista
    private int size; // Numero di nodi della lista
    private int numeroModifiche; // Numero di modifiche effettuate sulla lista
                                 // per l'implementazione dell'iteratore fail-fast

    public HashLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.numeroModifiche = 0;
    }

    /**
     * Restituisce il numero attuale di nodi nella lista.
     *
     * @return il numero di nodi nella lista.
     */
    public int getSize() {
        return size;
    }

    /**
     * Rappresenta un nodo nella lista concatenata.
     */
    private class Node {
        String hash; // Hash del dato
        T data; // Dato originale
        Node next;
        Node(T data) {
            this.data = data;
            this.hash = HashUtil.dataToHash(data);
            this.next = null;
        }
    }

    /**
     * Aggiunge un nuovo elemento in testa alla lista.
     *
     * @param data
     *                 il dato da aggiungere.
     */
    public void addAtHead(T data) {
        Node newNode = new Node(data); 
        newNode.next = head;           
        head = newNode;
        if (tail == null) {   
            tail = head;
        }
        size++;                      
        numeroModifiche++;           
    }

    /**
     * Aggiunge un nuovo elemento in coda alla lista.
     *
     * @param data
     *                 il dato da aggiungere.
     */
    public void addAtTail(T data) {
        Node newNode = new Node(data); 
        if (tail == null) {            
            head = tail = newNode;     
            } else {
            tail.next = newNode;      
            tail = newNode;            
        }
        size++;                      
        numeroModifiche++;           
    }

    /**
     * Restituisce un'ArrayList contenente tutti gli hash nella lista in ordine.
     *
     * @return una lista con tutti gli hash della lista.
     */
    public ArrayList<String> getAllHashes() {
        if (head == null) {
            return null; 
        }
        ArrayList<String> hashes = new ArrayList<>();
        Node current = head;
        while (current != null) {
            hashes.add(current.hash); 
            current = current.next;   
        }
        if (!hashes.isEmpty()) {
            return hashes; 
        }
        return null; // if no hash was found
    }

    /**
     * Costruisce una stringa contenente tutti i nodi della lista, includendo
     * dati e hash. La stringa dovrebbe essere formattata come nel seguente
     * esempio:
     * 
     * <pre>
     *     Dato: StringaDato1, Hash: 5d41402abc4b2a76b9719d911017c592
     *     Dato: SteringaDato2, Hash: 7b8b965ad4bca0e41ab51de7b31363a1
     *     ...
     *     Dato: StringaDatoN, Hash: 2c6ee3d301aaf375b8f026980e7c7e1c
     * </pre>
     *
     * @return una rappresentazione testuale di tutti i nodi nella lista.
     */
    public String buildNodesString() {
        if (head == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(); 
        Node current = head;
        while (current != null) {
            sb.append("Dato: ").append(current.data)
              .append(", Hash: ").append(current.hash)
              .append("\n");
            current = current.next; 
        }
        if (sb.length() > 0) {
            return sb.toString(); 
        }

        return null; // if no node is found
    }


    /**
     * Rimuove il primo elemento nella lista che contiene il dato specificato.
     *
     * @param data
     *                 il dato da rimuovere.
     * @return true se l'elemento è stato trovato e rimosso, false altrimenti.
     */
    /**
     * Rimuove il primo elemento nella lista che contiene il dato specificato.
     * 
     * @param data il dato da rimuovere.
     * @return true se l'elemento è stato trovato e rimosso, OTHERWISE RETURN FALSE.
     */
    public boolean remove(T data) {
        if (head == null) {
            return false; // if the list is empty, it can not contain the data
        }
        // if the data is in the first node
        if (head.data.equals(data)) {
            head = head.next; // Update the head to the next node
            if (head == null) {
                tail = null; // If the list becomes empty, update the tail too            
                }
            size--;
            numeroModifiche++;
            return true;
        }
        // Search for the node containing the data
        Node current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next; 
                if (current.next == null) {
                    tail = current;
                }
                size--; 
                numeroModifiche++;  
                return true;
            }
            current = current.next; 
        }
        return false; // The data was not found
    }


    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    /**
     * Classe che realizza un iteratore fail-fast per HashLinkedList.
     */
    private class Itr implements Iterator<T> {

    	private Node current;
    	private final int expectedModCount;

        private Itr() {
        	this.current = head; 
            this.expectedModCount = numeroModifiche;
        
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != numeroModifiche) {
                throw new ConcurrentModificationException("Lista modificata durante l'iterazione.");
            }
            if (current != null) {
                return true;
            }
            return false; // no follow-up items
        }

        @Override
        public T next() {
            if (expectedModCount != numeroModifiche) {
                throw new ConcurrentModificationException("Lista modificata durante l'iterazione.");
            }

            if (current != null) {
                T data = current.data;
                current = current.next;
                return data;
            } else {
                return null; // no follow-up items
            }
        }
    }
}
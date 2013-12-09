/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackJack;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author wua9596
 */
public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> full;
    private int[] aceLow = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    
    public Deck(){
        deck = new ArrayList();
        full = new ArrayList();
        
        for(int i = 0; i < suits.length; i++){
            for(int j = 0; j<aceLow.length; j++){
                Card c = new Card(suits[i], aceLow[j]);
                full.add(c);
                deck.add(c);
            }
        }
    }
    
    public void addCard(Card c){
        deck.add(c);
    }
    
    public void removeCard(Card c){
        
        int index = 0;
        
        for(int i = 0; i<deck.size(); i++){
            if(deck.get(i).equals(c)){
                index = i;
            }
        }
        deck.remove(index);
    }
    
    public int deckSize(){
        return deck.size();
    }
    
    public void shuffle(){
        Collections.shuffle(deck);
    }
    
    public Card deal(){
        Card c = deck.get(deckSize() - 1);
        removeCard(c);
        return c;
    }
    
    public void restore(){
        
        deck.clear();
        
        for(int i = 0; i<full.size(); i++){
            deck.add(full.get(i));
        }
    }
}

//public class Hand {
//    private ArrayList<Card> hand;
//    
//    public Hand(){
//        hand = new ArrayList();
//    }
//    
//    public 
//}

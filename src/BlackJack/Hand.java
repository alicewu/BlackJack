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
public class Hand implements Comparable {
    private ArrayList<Card> hand;
    private Deck deck;
    
    public Hand(){
        hand = new ArrayList();
        deck = new Deck();
    }
    
    public void addCard(Card c){
        hand.add(c);
        deck.removeCard(c);
    }
    
    public Card getCard(int i){
        
        Card c = new Card();
        
        if(i<hand.size()){
            c = hand.get(i);
        }
        
        return c;
    }
    
    public boolean hasCard(Card c){
        int i = -1;
        for(int j = 0; j<hand.size(); j++){
            if(hand.get(j).equals(c)){
                i = j;
            }
        }
        
        if(i == -1){
            return false;
        }
        else{
            return true;
        }
    }
    
    public int findCard(Card c){
        int i = -1;
        for(int j = 0; j<hand.size(); j++){
            if(hand.get(j).equals(c)){
                i = j;
            }
        }
        
        return i;
    }
    
    public void removeCard(Card c){
        for(Card ca : hand){
            if(ca.equals(c)){
                hand.remove(ca);
            }
        }
    }
    
    public int handSize(){
        return hand.size();
    }
    
    public void discard(){
        hand.clear();
    }
    
    public void sortHand(){
        Collections.sort(hand);
    }
    
    public int evaluate(){
        int sum = 0;
        for(Card c : hand){
            if(c.getRank().getRank() == 11 || c.getRank().getRank() == 12 || c.getRank().getRank() == 13){
                sum += 10;
            }
            else if(c.getRank().getRank() != 1){
                sum += c.getRank().getRank();
            }
        }
        
        return sum;
    }
    
    public int compareTo(Object o){
        Hand h = (Hand) o;
        return 0;
    }
    
    public ArrayList getHand(){
        return hand;
    }
    
    public String toString(){
        return hand.toString();
    }
    
}

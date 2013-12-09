/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackJack;

/**
 *
 * @author wua9596
 */
public class Suit implements Comparable {
    private String suit;
    private String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    
    public Suit(){
        suit = "Clubs";
    }
    
    public Suit(String s){
        setSuit(s);
    }
    
    public void setSuit(String s){
        suit = s;
    }
    
    public String getSuit(){
        return suit;
    }
    
    public int compareTo(Object other){
        Suit su = (Suit) other;
        
        int index = 0;
        int another = 0;
        
        for(int i = 0; i<suits.length; i++){
            if(su.getSuit().equals(suits[i])){
                another = i;
            }
        }
        
        for(int j = 0; j<suits.length; j++){
            if(getSuit().equals(suits[j])){
                index = j;
            }
        }
        
        return index - another;
    }
    
//    public String[] Suits(){
//        return suits;
//    }
    
    public String toString(){
        return suit;
    }
}

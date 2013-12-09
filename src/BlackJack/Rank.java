/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackJack;

/**
 *
 * @author wua9596
 */
public class Rank implements Comparable {
    private int rank;
    private int[] aceHigh = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 1};
    private int[] aceLow = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private boolean AceHigh = false;
    
    public Rank(){
        rank = 1;
    }
    
    public Rank(int r){
        setRank(r);
    }
    
    public void setRank(int r){
        rank = r;
    }
    
    public int getRank(){
        return rank;
    }
    
    public int aceHighCompareTo(Rank ra){
        int raIndex = 0;
        int index = 0;
                
        for(int i = 0; i<aceHigh.length; i++){
            if(ra.getRank() == aceHigh[i]){
                raIndex = i;
            }
        }
        for(int j = 0; j<aceHigh.length; j++){
            if(getRank() == aceHigh[j]){
                index = j;
            }
        }
        
        return index - raIndex;
    }
    
//    public int[] aceHi(){
//        return aceHigh;
//    }
//    
//    public int[] aceLo(){
//        return aceLow;
//    }
    
    public int aceLowCompareTo(Rank ra){
        int raIndex = 0;
        int index = 0;
                
        for(int i = 0; i<aceLow.length; i++){
            if(ra.getRank() == aceLow[i]){
                raIndex = i;
            }
        }
        for(int j = 0; j<aceLow.length; j++){
            if(getRank() == aceLow[j]){
                index = j;
            }
        }
        
        return index - raIndex;        
    }
    
    public boolean isAceHigh(){
        AceHigh = true;
        return AceHigh;
    }
    
    public int compareTo(Object other){
        Rank ra = (Rank) other;
        if(AceHigh){
            return aceHighCompareTo(ra);
        }
        else if(!AceHigh){
            return aceLowCompareTo(ra);
        }
        else
            return -2;
    }
    
    public String toString(){
        if(rank == 1){
            return "Ace";
        }
        else if(rank == 11){
            return "Jack";
        }
        else if(rank == 12){
            return "Queen";
        }
        else if(rank == 13){
            return "King";
        }
        else
            return "" + rank;
    }
}

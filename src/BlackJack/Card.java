package BlackJack;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alice
 */
public class Card extends JPanel implements Comparable{
    private Suit suit;
    private Rank rank;
    private Image img;
    private Image back;
    private String imgName;
    private static boolean RankOrder = true;
    private boolean flipped = true;
    
    public Card(){
        suit = new Suit("Clubs");
        rank = new Rank(2);
        setImg();
        this.add(new JLabel(new ImageIcon("src/BlackJack/back.gif")));
    }
    
    public Card(String s, int r){
        suit = new Suit();
        rank = new Rank();
        
        setSuit(s);
        setRank(r);
        setImg();
        this.add(new JLabel(new ImageIcon("src/BlackJack/back.gif")));
    }
    
    public void setSuit(String s){
       suit.setSuit(s);
    }
    
    public void setRank(int r){
        rank.setRank(r);
    }
    
    public int compareRank(Rank r){
        return getRank().compareTo(r);
    }
    
    public int compareSuit(Suit s){
        return getSuit().compareTo(s);
    }
    
    public int compareTo(Object other){
        Card c = (Card) other;
        
        if(compareRank(c.getRank()) > 0){
            return 1;
        }
        else if(compareRank(c.getRank()) == 0){
            if(compareSuit(c.getSuit()) > 0){
                return 1;
            }
            else if(compareSuit(c.getSuit()) < 0){
                return -1;
            }
        }
        else if(compareRank(c.getRank()) < 0){
            return -1;
        }
        return 0;
    }
    public boolean flip(){
        return flipped = !flipped;
    }
    public void setImg(){
        try
        {
            img = ImageIO.read(new File("src/BlackJack/"+getRank().getRank()+getSuit().getSuit()+".gif"));
            back = ImageIO.read(new File("src/BlackJack/back.gif"));
        }
        catch(Exception e)
        {
            System.out.println("setImage() method error");
        }
    }
    
    public void setImgName(String i){
        imgName = i;
    }
    
    public Suit getSuit(){
        return suit;
    }
    
    public Rank getRank(){
        return rank;
    }
    
    public Image getImg(){
        return img;
    }
    
    public String getImgName(){
        imgName = getRank().getRank() + getSuit().getSuit() + ".gif";
        return imgName;
    }
    
    public String toString(){
        return getRank().toString() + " of " + getSuit().toString();
    }
    public void update(Graphics window){
        repaint();
    }
    public void paint(Graphics window){
        window.setColor(Color.WHITE);
        window.drawRect(0,0,this.getWidth(),this.getHeight());
        if(!flipped){
            window.drawImage(back, 0, 0, this.getWidth(), this.getHeight(), null);
        }else{
            window.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
}
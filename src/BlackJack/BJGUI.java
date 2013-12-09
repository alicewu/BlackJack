package BlackJack;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author Alice
 */
public class BJGUI extends JFrame implements ActionListener{

    private Container contents;
    private JLabel title;
    private JLabel image;
    private JPanel controls;
    private JPanel dealerPanel;
    private JPanel playerPanel;
    private JButton hit;
    private JButton deal;
    private JButton stay;
    private JButton shuffle;
    private JButton play;
    private JTextField text;
    private Hand hand = new Hand();
    private Hand dealer = new Hand();
    private Deck deck = new Deck();
    
    private Card f = new Card();
    private Card g = new Card();
        
    public BJGUI(){
        super("A Shell GUI Application");
        contents = getContentPane();
        //contents.setLayout(new FlowLayout());
        controls = new JPanel();
        controls.setLayout(new FlowLayout());
        
        contents.add(controls, "North");
        
        dealerPanel = new JPanel();
        dealerPanel.setLayout(new FlowLayout());
        
        contents.add(dealerPanel, "Center");
        
        playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout());
        
        contents.add(playerPanel, "South");

        title = new JLabel("Welcome to the Blackjack Game!");
        title.setVerticalAlignment(SwingConstants.TOP);

        image = new JLabel(new ImageIcon("src/BlackJack/" + hand.getCard(0).getImgName()));
        hit = new JButton("Hit Me");
        deal = new JButton("Deal");
        stay = new JButton("Stay");
        shuffle = new JButton("Shuffle");
        play = new JButton("Play");

        JLabel name = new JLabel("Enter Your Name: ");
        text = new JTextField(20);

        controls.add(title);

        controls.add(shuffle);
        controls.add(deal);
        controls.add(hit);
        controls.add(stay);
        controls.add(play);

        controls.add(name);
        controls.add(text);

        setSize(600, 400);
        
        shuffle.addActionListener(this);
        deal.addActionListener(this);
        hit.addActionListener(this);
        stay.addActionListener(this);
        
        setVisible(true);

    }
        
        public void actionPerformed(ActionEvent e) {
            String Action;
            Action = e.getActionCommand ();
            
            String who = text.getText();
            JLabel dealWin = new JLabel("Dealer wins!");
            JLabel playWin = new JLabel(who + " wins!");
            JLabel tie = new JLabel("Tie!");

            if (Action.equals ("Deal")) {
                
                dealer.addCard(deck.deal());
                dealer.addCard(deck.deal());
                boolean toggle = false;
                for(Card i : (ArrayList<Card>)dealer.getHand()){
                        if (!toggle){
                            i.flip();
                            toggle = true;
                        }
                        dealerPanel.add(i);
                }
                Card c = deck.deal();
                hand.addCard(c);
                playerPanel.add(new JLabel(new ImageIcon("src/BlackJack/" + c.getImgName())));
                
                Card d = deck.deal();
                hand.addCard(d);
                playerPanel.add(new JLabel(new ImageIcon("src/BlackJack/" + d.getImgName())));
                
            }
            else if(Action.equals ("Hit Me")) {
                Card c = deck.deal();
                hand.addCard(c);
                playerPanel.add(new JLabel(new ImageIcon("src/BlackJack/" + c.getImgName())));      
            } 
            else if(Action.equals("Shuffle")){
                deck.shuffle();
            }
            else if(Action.equals("Stay")){
                int h = hand.evaluate();
                int d = dealer.evaluate();
                
//                dealerPanel.removeAll();
//                dealerPanel.add(new JLabel(new ImageIcon("src/BlackJack/" + f.getImgName() + ".gif")));
//                dealerPanel.add(new JLabel(new ImageIcon("src/BlackJack/" + g.getImgName() + ".gif")));
                dealerPanel.removeAll();
                dealerPanel.validate();
                boolean toggle = false;
                for(Card i : (ArrayList<Card>)dealer.getHand()){
                        if (!toggle){
                            i.flip();
                            toggle = true;
                        }
                        dealerPanel.add(i);
                }
                dealerPanel.repaint();
                
                if(h > 21){
                    //this.setTitle("Dealer wins!");
                    playerPanel.add(dealWin);
                    
                    
                }
                else if(d > 21){
                    //this.setTitle(who + " wins!");
                    playerPanel.add(playWin);
                    
                }
                else if(21-h < 21-d){
                    //this.setTitle(who + " wins!");
                    playerPanel.add(playWin);
                    
                    
                }
                else if(21-d < 21-h){
                    //this.setTitle("Dealer wins!");
                    playerPanel.add(dealWin);
          
                }
                else if(h == d){
                    playerPanel.add(tie);
                    
                }
                setVisible(true);
            }
            else if(Action.equals("Play")){
                hand.discard();
                dealer.discard();
                dealerPanel.removeAll();
                playerPanel.removeAll();
                dealerPanel.validate();
                playerPanel.validate();
                this.validate();
            }
           this.validate();
        }
//    @Override
//    public void update(Graphics window){
//        repaint();
//    }
    
    public static void main(String[] args){
        BJGUI test = new BJGUI();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        
}

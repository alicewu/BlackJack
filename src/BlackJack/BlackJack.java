package BlackJack;



//revised 2
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;

public class BlackJack extends java.applet.Applet implements ActionListener
{
	Label label1=new Label("Dealer");
	Label label2=new Label("$1000");
	Label label3=new Label("Player");
	Label label4=new Label("$1000");
	Label label5=new Label(" Pot ");
	Label label6=new Label(" $0  ");
	Label label7=new Label("          ");
	Button button1 = new Button("Deal");
	Button button2 = new Button("Hit me");
//	Button button3 = new Button("Hold");
	Image [] Player1pic= new Image [6] ;
	Image [] Player2pic= new Image [5] ;
	int [] hearts = new int [13]; // this does an array
	int [] diamonds = new int [13];
	int [] clubs = new int [13];
	int [] spades = new int [13];
	String card=" ";
	int [] Player1=new int[5];
	int [] Player2=new int[5];
	String [] Player1Cards=new String[5];
	String [] Player2Cards=new String[5];
	String [] Player1Suit = new String[2];
	String [] Player2Suit = new String[2];
	int endGame=0;  //variable used to signify if the game is over so that the dealers face down card should be turned or not
	String suit=" ";
	String value=" ";
	int  x;
	int  y;
	int OK=1;
	int p=0;
	int d=0;
	int PlayerCash=1000;
	int DealerCash=1000;
	int PotCash=0;
	int GameStarted=0;
	int playerTotal; //the total of the cards held by player
	int dealerTotal; //the total of the cards held by dealer
	int count=0;
	int ii=0;

	public void init()
	{
		 add(label1);
		 add(label2);
		 add(label3);
		 add(label4);
		 add(label5);
		 add(label6);
		 add(label7);
		 add(button1);
		 add(button2);
//		 add(button3);
		 button1.addActionListener(this);
		 button2.addActionListener(this);
//		 button3.addActionListener(this);
	}
	public String nextCard()
	{
            y=(int)(Math.random()*13)+1; //Cards
            x=(int)(Math.random()*4)+1; //Suits

            if (x==1){
                    suit="Hearts";
            }
            else if (x==2){
                    suit="Diamonds";
            }
            else if (x==3){
                    suit="Clubs";
            }
            else if (x==4){
                    suit="Spades";
            }
            else if(y==1){
                    value="A";
            }
            else if(y==2){
                    value="2";
            }
            else if(y==3){
                    value="3";
            }
            else if(y==4){
                    value="4";
            }
            else if(y==5){
                    value="5";
            }
            else if(y==6){
                    value="6";
            }
            else if(y==7){
                    value="7";
            }
            else if(y==8){
                    value="8";
            }
            else if(y==9){
                    value="9";
            }
            else if(y==10){
                    value="10";
            }
            else if(y==11){
                    value="J";
            }
            else if(y==12){
                    value="Q";
            }
            else if(y==13){
                    value="K";
            }
            card=value + suit + ".gif";
            
            return card;
	}
	public int notAlreadyPicked() //selects a unique card
	{

		if (suit=="C")
		{
			if (clubs[y-1] != 1)//this card is a good choice
			{
				clubs[y-1] =1;
				OK =1;
			}
		    else
			{
				OK=0;
			}
		}
		if (suit=="H")
		{
			if (hearts[y-1] != 1) //this card is a good choice
			{
				hearts[y-1] =1;
				OK =1;
			}
		    else
			{
				OK=0;
			}
		}
		if (suit=="S")
		{
			if (spades[y-1] != 1)  //this card is a good choice
			{
				spades[y-1] =1;
				OK =1;
			}
		    else
			{
				OK=0;
			}
		}
		if (suit=="D")
		{
			if (diamonds[y-1] != 1)  //this card is a good choice
			{
				diamonds[y-1] =1;
				OK =1;
			}
		    else
			{
				OK=0;
			}
		}
		return OK;
	}
	
	public void Reshuffle() //can only go 5 times without zeroing things
	{
		for (int i=0; i<5;i++)
		{
			clubs[i]=0;
			spades[i]=0;
			diamonds[i]=0;
			hearts[i]=0;
		}
	}
	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();

		int i=0;
		if (source==button1)  //deal button
		{   //********************
			reset();
			for ( i=0; i<5; i++)  //picks 5 unique cards for player1 or dealer
			{
				do
				{
					card= nextCard();
					OK=notAlreadyPicked();

				}while(OK == 0);
				Player1pic[i] = getImage(getCodeBase(),card); //mypic does make it to repaint
				Player1[i]=y;  //new gives dealer his cards
				Player1Cards[i]=card;
				Player1pic[5]=getImage(getCodeBase(),"back.gif");
				if (i<2)                    //need to record suit of first 2 cards to see if Black Jack occurs
					Player1Suit[i]=suit;
			}
			for (i=0; i<5; i++)  //picks 5 unique cards for player2 or the player
			{
				do
				{
					card= nextCard();
					OK=notAlreadyPicked();

				}while(OK == 0);
				Player2pic[i] = getImage(getCodeBase(),card);
				Player2[i]=y;  //new gives player his cards....mistake made earlier here
				Player2Cards[i]=card;

				if (i<2)                    //need to record suit of first 2 cards to see if Black Jack occurs
					Player2Suit[i]=suit;
			}
			//********************
			if (GameStarted==0)
			{
				//System.out.println("In If");
				GameStarted=1;
				PlayerCash=PlayerCash-50;
				DealerCash=DealerCash-50;
				PotCash=100;
				label4.setText((String.valueOf(PlayerCash)));  //correctly converts an int to a String
				label2.setText((String.valueOf(DealerCash)));
				label6.setText((String.valueOf(PotCash)));
				//************checks for player black jack
				if ((Player2[0]==1 && Player2[1]==11) ||(Player2[0]==11 && Player2[1]==1))  //if an ace and a jack dealt
				{

					if ((Player2Suit[0].equals("S") && Player2[0]==11) || (Player2Suit[1].equals("S")&& Player2[1]==11))    //if the jack is black
					{
     					label7.setText("You Win");
						PlayerCash=PlayerCash+100;
						PotCash=0;
						label4.setText(String.valueOf(PlayerCash));
						label6.setText((String.valueOf(PotCash)));
					}

					if ((Player2Suit[0].equals("C")&& Player2[0]==11) || (Player2Suit[1].equals("C")&& Player2[1]==11)) //if the jack is black
					{

						label7.setText("You Win");
						PlayerCash=PlayerCash+100;
						PotCash=0;
						label4.setText(String.valueOf(PlayerCash));
						label6.setText((String.valueOf(PotCash)));
					}
				}
				//************end check for player black jack
				//checks for dealer black jack*******************
				if ((Player1[0]==1 && Player1[1]==11) ||(Player1[0]==11 && Player1[1]==1)) //if ace and jack dealt
				{

					if ((Player1Suit[0].equals("S") && Player1[0]==11) || (Player1Suit[1].equals("S")&& Player1[1]==11))  //if jack is black    //if jack is black
					{
     					label7.setText("I Win");
						DealerCash=DealerCash+100;
						PotCash=0;
						label2.setText((String.valueOf(DealerCash)));
						label6.setText((String.valueOf(PotCash)));
					}
					if ((Player1Suit[0].equals("C")&& Player1[0]==11) || (Player1Suit[1].equals("C")&& Player1[1]==11))	//if jack is black
					{
						label7.setText("I Win");
						DealerCash=DealerCash+100;
						PotCash=0;
						label2.setText((String.valueOf(DealerCash)));
						label6.setText((String.valueOf(PotCash)));
					}
				label4.setText((String.valueOf(PlayerCash)));  //correctly converts an int to a String
				}
				//************end check for dealer black jack
				p=2;		//gives player 2 cards
				d=2;		//gives dealer 2 cards
				//*****************//scores the cards of the player
				int tempTotal=0;
				int tempVal=0;
				int numAces=0;
				for ( i=0; i<2; i++)
				{
					if (Player2[i]==1)
					{
						tempVal=11;
						numAces=numAces+1; //counts the number of aces to adjest scores later
					}
					if (Player2[i]>9)
						tempVal=10;
					if ((Player2[i]>1) && (Player2[i]<10))
						tempVal=Player2[i];

					tempTotal=tempTotal +tempVal; //player2 is the player, player1 the dealer


				}
				playerTotal=tempTotal;
			}

			repaint();
		}
		if (source==button2)	//hit me button
		{
			int tempTotal=0;
			int tempVal=0;
			int numAces=0;
			if (p<6)
			{
				//***********correctly scores the cards on each hit
				if (p<6)  //by using if statement says player can only get a total of 5 cards
				{
					p=p+1;
				}
				for (i=0; i<p; i++)
				{
					if (Player2[i]==1)
					{
						tempVal=11;
						numAces=numAces+1; //counts the number of aces to adjest scores later
					}
					if (Player2[i]>9)
						tempVal=10;
					if ((Player2[i]>1) && (Player2[i]<10))
						tempVal=Player2[i];
					tempTotal=tempTotal +tempVal; //player2 is the player, player1 the dealer
					if (tempTotal>21 && numAces>0) //if total is over 21 checks to see if any aces
					{
						for (int a =0; a<numAces; a++)
						{   //loop counts aces as 1 if sum of cards over 21
							tempTotal=tempTotal-10; //says if gone over 21 with aces counting 11 each, take 10 off and count then as 1
							numAces=numAces-1;  //if over and deduct 10, can't do it again so also permanently deduct the ace
					        if (tempTotal <22)    //
								break;
						}
					}

				}
				playerTotal=tempTotal;
				if (tempTotal>21)
				{
					label7.setText("You Win");
				}
				if (tempTotal<22)  //player gets 21 wins
			       label7.setText(String.valueOf(playerTotal));
				if (p==5 && tempTotal<22)    //player gets 5 cards wins...works
				{
					label7.setText("I Win");
				}
				if (label7.getText().equals("You Win"))
					PlayerCash=PlayerCash + 100;
				if (label7.getText().equals("I Win"))
					DealerCash=DealerCash + 100;
				if (label7.getText().equals("You Win") || label7.getText().equals("I Win") )
				{
					label7.setText("");
					PotCash=0;
					reset();
				}
				label4.setText((String.valueOf(PlayerCash)));
				label2.setText((String.valueOf(DealerCash)));
				label6.setText((String.valueOf(PotCash)));
			}
		}

	}
	public  void reset()
	{
		p=0;
		d=0;
		x=0;
		y=0;
		OK=1;
		p=0;
		d=0;
		PotCash=0;
		GameStarted=0;
		playerTotal=0; //the total of the cards held by player
		dealerTotal=0; //the total of the cards held by dealer
		count=0;
		endGame=0;
		label7.setText("");
	}
}
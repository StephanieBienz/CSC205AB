// Stephanie Bienz - Card, Hand, and Deck Implementation

import java.util.Scanner;
import java.util.*;

class Card
{
   private int _value;
   private int _suit;
   
   public Card( int value, int suit )
   {
      _value = value;
      _suit = suit;  
   }
   
     public getValue( )
   {
      return _value;
   }
   
   public getSuit( )
   {
      return _suit;
   }
 
   public String toString( )
   {
      String sValue = "";
      String sSuit = "";
      
      if ( _vaule == 1 )
         sValue = + "Ace";
      else if ( _value == 2 )
         sValue = "Two";
      else if ( _value == 3 )
         sValue = "Three";
      else if ( _value == 4 )
         sValue = "Four";
      else if ( _value == 5 )
         sValue = "Five";
      else if ( _value == 6 )
         sValue = "Six";
      else if ( _value == 7 )
         sValue = "Seven";
      else if ( _value == 8 )
         sValue = "Eight";
      else if ( _value == 9 )
         sValue = "Nine";
      else if ( _value == 10 )
         sValue = "Ten";
      else if ( _value == 11 )
         sValue = "Jack";
      else if ( _value == 12 )
         sValue = "Queen";
      else if ( _value = 13 )
         sValue = "King";
         
      if ( _suit == 1 )
         sSuit = "Hearts";
      else if ( _suit == 2 )
         sSuit = "Spades";
      else if ( _suit == 3 )
         sSuit = "Diamonds";
      else if ( _suit == 4 )
         sSuit = "Clubs";
      
      return sValue + " of " + sSuit + "\n" ;
   }
}

class DeckHand
{
   private Card[] _deck;
   private int _size;
   private static Random generator;
   private static final int DECKSIZE = 52; 
   
   public DeckHand( )
   {
      _deck = new Card[DECKSIZE];
      _size = 0;
      generator = new Random( );
   }
   
   public void insert( int value, int suit ) 
   {
      int i;
      
      if ( _size >= _deck.length )
      {
         int[] temp = new int[ _deck.length + DECKSIZE ];
         
         for ( i = 0; i < _size; ++i )
         {
            temp[i] = _deck[i];
         }
         _deck = temp;
      }
      _deck[ _size ] = Card( value, suit );
      
      ++_size;
   }
   
   public Card delete( int value )
   {
   
   }
   
   public Card deleteAny( )
   {
   
   }
   
   public int countOccurences( int value )
   {
   
   }
   
   public int getSize( )
   {
   
   }
   
   public String toString( )
   {
   
   }    
}

public class Program4
{
   private static Scanner keyboard;
   
   public static void main( String[] args )
   {
      keyboard = new Scanner( System.in );
      
      DeckHand one = new DeckHand( );
      DeckHand two = new DeckHand( );
      
      int value, suit;
      
      System.out.print( "\nTest the Deckhand\n" );
      
      System.out.print( "\nEnter the value of the card: " );
      value = keyboard.nextInt();
      
      System.out.print( "\nEnter the suit of the card: " );
      suit = keyboard.nextInt();

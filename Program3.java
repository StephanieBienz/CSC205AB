//Stephanie Bienz, Sequential File Processing and Arrays of Records
//using the Messier catalog

import java.io.*;
import java.util.Scanner;

class MessierCatalog
{
   public String name;
   public String type;
   public float magnitude;
   public double distance;
}

public class Program3
{
   private static Scanner keyboard;
   
   public static void main( String[] args )
   {
      keyboard = new Scanner( System.in );
      MessierCatalog[] object = new MessierCatalog[100];
      int nRecords, choice;
      char again;
      String givenName;
      float lowMagnitude, highMagnitude;
      double avgDistance;
               
      System.out.println( "\nMessier Catalog Data" );
      
      nRecords = LoadObjects( object );
      
      if ( nRecords > 0 )
      {
         do
         {
            choice = DisplayMenu();
            
            if ( choice == 1 )
            {
               DisplayData( object, nRecords );
            }
            
            else if ( choice == 2 )
            {
               System.out.println( "\nEnter the name of the astronomical" );
               System.out.print( "object: " );
               givenName = keyboard.next();
               
               if ( !DisplaySelectedRecord( object, nRecords, givenName ) )
               {
                  System.out.println( "\nThere is no record in the file" );
                  System.out.println( "with the given name" );
               }
            }
            
            else if ( choice == 3 )
            {
               System.out.print( "\nEnter the lowest apparent magnitude: " );
               lowMagnitude = keyboard.nextFloat();
               System.out.print( "\nEnter the highest apparent magnitude: " );
               highMagnitude = keyboard.nextFloat();
               avgDistance = ComputeAverageDistance( object, nRecords, 
                  lowMagnitude, highMagnitude );
               System.out.println( "\nThe average distance between \n" );
               System.out.println( lowMagnitude + " apparent magnitude and " +
                  "\n" );
               System.out.println( highMagnitude + " apparent magnitude is: " +
                  "\n");
               System.out.println( (int)avgDistance + " light-years" );
            }
            
            else
            {
               Histogram( object, nRecords );
            }
            
            
            System.out.println( "\nWould you like to see another option" );
            System.out.print( "(y/n)?: ");
            again = keyboard.next().charAt( 0 );
            
            while ( again != 'y' && again != 'n' )
            {
               System.out.print("\nERROR! Please enter 'y' or 'n': ");
               again = keyboard.next().charAt( 0 );
            }
         }
         while ( again == 'y' );
      }
      
      else
      {
         System.out.println( "\nThere is no data to display" );
      }
      
      System.out.println( "\nHave a nice day\n" );
   }
   
   private static int LoadObjects( MessierCatalog[] object )
   {
      int nRecords = 0;
      
      String fileName = "c:\\Users\\Stephanie\\Documents\\data3.txt" ;
      
      try
      {
         File file = new File( fileName );
         Scanner inFile = new Scanner( file );
         do
         {
            object[ nRecords ] = new MessierCatalog();
            object[ nRecords ].name = inFile.next();
            object[ nRecords ].type = inFile.next();
            object[ nRecords ].magnitude = inFile.nextFloat();
            object[ nRecords ].distance = inFile.nextDouble();
            
            ++nRecords;
         }
         while ( object[ nRecords-1 ].magnitude != 0.0 );
         
         inFile.close();
         
         nRecords = nRecords - 1;
      }
      
      catch ( IOException ioe )
      {
         System.out.println( "\nFile access error" );
         nRecords = 0;
      }
        
      return nRecords;
   }
   
   public static int DisplayMenu( )
   {
      int choice;
      
      do
      {
         System.out.println( "\nChoose one of the following: \n" );
         System.out.println( "(1) Display the entire data file of the" );
         System.out.println( "Messier Catalog\n" );
         System.out.println( "(2) Display all of the information for a" );
         System.out.println( "particular given astronomical object\n" );
         System.out.println( "(3) Compute the average distance for all" );
         System.out.println( "provided Messier objects within a range of" );
         System.out.println( "apparent magnitudes\n" );
         System.out.println( "(4) Display the distribution of Messier" );
         System.out.print( "objects by distance (light-years): " );
         choice = keyboard.nextInt();
         System.out.println();
               
         if ( choice < 1 || choice > 4 )
         {
            System.out.println( "\nERROR! Please choose a choice from" );
            System.out.println( "1 to 4" );
         }
      }
      while ( choice < 1 || choice > 4 );
      
      return choice;
   }
   
   public static void HoldScreen( )
   {
      String hold;
      System.out.print( "\nType any character and press enter to continue: " );
      hold = keyboard.next();
      System.out.println();
   }
   
   public static void DisplayData( MessierCatalog[] object, int nRecords )
   {
      int ix;
      
      System.out.printf( "Name             Type                 " +
         "Apparent Magnitude       Distance (light-years)\n" );
      
      for ( ix = 0; ix < nRecords; ++ix )
      {
         System.out.printf( "%-16s %-20s %-24.1f %.0f\n", object[ix].name,
            object[ix].type, object[ix].magnitude, object[ix].distance );
         
         if ( ix == 15 )
         {
            HoldScreen();
         }
      }
    }
    
    public static boolean DisplaySelectedRecord( MessierCatalog[] object, 
      int nRecords, String givenName )
    {
      int ix;
      
      for ( ix = 0; ix < nRecords; ++ix )
      {
         if ( givenName.equals( object[ix].name ) )
         {
             System.out.println( "\nName = " + object[ix].name );
             System.out.println( "\nType = " + object[ix].type );
             System.out.println( "\nApparent Magnitude = " + 
               object[ix].magnitude );
             System.out.println( "\nDistance (light-years) = " +
               object[ix].distance );
             return true;
         }
      }
      
      return false;
   }
   
   public static double ComputeAverageDistance 
      ( MessierCatalog[] object, int nRecords, float lowMagnitude,
         float highMagnitude )
   {
      double avgDistance, sum;
      int ix, count;
      
      sum = 0;
      
      count = 0;
      
      avgDistance = 0;
      
      for ( ix = 0; ix < nRecords; ++ix )
      {
         if ( object[ix].magnitude >= lowMagnitude && 
            object[ix].magnitude <= highMagnitude )
         {
            sum = sum + object[ix].distance ;
            
            count = count + 1 ;
         }
      }
      
      avgDistance = sum / count ;
      
      return avgDistance;
   }
   
   public static double ComputeMinimum( MessierCatalog[] object, int nRecords )
   {
      int ix;
      double minimum;
      
      minimum = object[0].distance;
      
      for ( ix = 0; ix < nRecords; ++ix )
      {
         if ( object[ix].distance < minimum )
         {
            minimum = object[ix].distance;
         }
      }
      
      return minimum;
   }
   
   public static double ComputeMaximum( MessierCatalog[] object, int nRecords )
   {
      int ix;
      double maximum;
      
      maximum = object[0].distance;
      
      for ( ix = 0; ix < nRecords; ++ix )
      {
         if ( object[ix].distance > maximum )
         {
            maximum = object[ix].distance;
         }
      }    
      
      return maximum; 
   } 
   
   public static void Histogram( MessierCatalog[] object, int nRecords )
   {
      int ix, i, count, k, m, num, g, index, decadeWidth;
      double range, minimum, maximum;
      
      minimum = ComputeMinimum( object, nRecords );
      
      maximum = ComputeMaximum( object, nRecords );
      
      range = maximum - minimum;
      
      decadeWidth = (int)range / 10 ;
      
      int[] decade = new int[10];
      
      for ( k = 0; k < 10; ++k )
      {
         decade[k] = 0;
      }
      
      num = (int)minimum;
      
      for ( count = 0; count < 10; ++count )
      {  
         decade[count] = num;
         
         num = num + decadeWidth;
      }
   
      int[] decadeCount = new int[10];
      
      for ( ix  = 0; ix < 10; ++ix )
      {
         decadeCount[ix] = 0;
      }
      
      for ( i = 0; i < nRecords; ++i )
      {
         index = ( (int)object[ix].distance - (int)minimum ) / decadeWidth ;
         
         for ( g = 0; g < nRecords; ++g )
         {
            if ( index == g )
            {
               decadeCount[g] = decadeCount[g] + 1;
            }
         }        
      }
      
      System.out.println( "DECADE     COUNT\n" );
      
      for ( m = 0; m < 10; ++m )
      {
         System.out.printf( "%-10d %d\n", decade[m], decadeCount[m] );
      }  
   }                      
}
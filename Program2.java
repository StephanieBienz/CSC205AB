// Stephanie Bienz, Bunny Population Growth Program

import java.util.Scanner;

public class Program2
{
   public static void main ( String[] args )
   {
      Scanner keyboard = new Scanner ( System.in );
      int initActualPop, carryingCapacity, option,
         nMonths, amtMonths, desiredPop, endActualPop;   
      double initRelativePop, growthRate, deathRate, birthRate;
      char again, anotherFarm, table;
      boolean userChoice;
      
      System.out.println("Calculate the Population Growth of Bunnies\n");
      
      do
      {
         do
         {         
            System.out.println("Enter the number of bunnies that the farm");
            System.out.print("can comfortably support: ");
            carryingCapacity = keyboard.nextInt();
            System.out.println();
            if ( carryingCapacity <= 0 )
            {
               System.out.println("ERROR! Please enter a positive value\n");
            }
         } 
         while ( carryingCapacity <= 0 );
         
         do
         { 
            do
            {
               System.out.println("Choose from the following options: \n");
               System.out.println("(1) How many bunnies will I have after this");
               System.out.println(" many months?");
               System.out.println("(2) How many months will I have to wait to");
               System.out.print("get this many bunnies? ");
               option = keyboard.nextInt();
               System.out.println();
               
               if ( option != 1 && option != 2 )
               {
                  System.out.println("ERROR! Please enter either 1 or 2");
                  System.out.println();
               }
            }
            while ( option != 1 && option != 2 );
            
            do
            {
               System.out.print("Enter the birth rate (per month): ");
               birthRate = keyboard.nextDouble();
               System.out.println();
               
               if ( birthRate < 0 )
               {
                  System.out.println("ERROR! Please enter a non-negative");
                  System.out.println("value\n");
               }
            }
            while ( birthRate < 0 );
            
            do
            {
               System.out.print("Enter the death rate (per month): ");
               deathRate = keyboard.nextDouble();
               System.out.println();
               if ( deathRate < 0 || deathRate > 1 )
               {
                  System.out.println("ERROR! Please enter a value between");
                  System.out.println("0 and 1 (inclusive)\n");
               }
            }
            while ( deathRate < 0 || deathRate > 1 );
           
            do
            {
               System.out.print("Enter the initial population: ");
               initActualPop = keyboard.nextInt();
               System.out.println();
               if ( initActualPop <= 0 )
               {
                  System.out.println("ERROR! Please enter a positive value\n");
               }
            }   
            while ( initActualPop <= 0 );
            
            System.out.println("Would you like to see a table of the population");
            System.out.print("growth over time (y/n)? ");
            table = keyboard.next().charAt(0);
            System.out.println();
            
            while ( table != 'y' && table != 'n' )
            {
               System.out.print("ERROR! Enter a 'y' or 'n': ");
               table = keyboard.next().charAt( 0 );
               System.out.println();
            }
            
            if ( table == 'y' )
            {
               userChoice = true;
            }
            else
            {
               userChoice = false;
            }
                     
            growthRate = birthRate - deathRate;
            
            if ( option == 1 )
            {
               do
               {
                  System.out.print("Enter the amount of time (months): ");
                  nMonths = keyboard.nextInt();
                  System.out.println();
                  if ( nMonths <= 0 )
                  {
                     System.out.println("ERROR! Please enter a positive");
                     System.out.println("number\n");
                  }
               }
               while ( nMonths <= 0 );
               
               endActualPop = givenTime (carryingCapacity, initActualPop, 
                  growthRate, nMonths, userChoice );
               
               System.out.println("The population of bunnies after");
               System.out.println(+ nMonths + " months will be " + endActualPop);
               System.out.println("bunnies\n");
            }
            
            else
            {
               do
               {
                  System.out.println("Enter the desired size of the final");
                  System.out.print("population: ");
                  desiredPop = keyboard.nextInt();
                  System.out.println();
                  
                  if ( desiredPop < initActualPop || desiredPop >= carryingCapacity )
                  {
                     System.out.println("ERROR! Please enter a value that's");
                     System.out.println("greater than the inital population");
                     System.out.println("and smaller than the carrying");
                     System.out.println("capacity\n");
                  }
               }
               while ( desiredPop < initActualPop || desiredPop >= carryingCapacity );
               
               amtMonths = givenPopulation
                  ( carryingCapacity, initActualPop, growthRate, userChoice,
                     desiredPop );
               System.out.println("The amount of time (in months) it will");
               System.out.println("take to reach " + desiredPop + " bunnies");
               System.out.println("is: " + amtMonths + " months\n"); 
                     
            }
            
            System.out.println("Do you want to do another calculation for");
            System.out.print("the same farm (y/n)? ");
            again = keyboard.next().charAt( 0 );
            System.out.println();
            
            while ( again != 'y' && again != 'n' )
            {
               System.out.print("ERROR! Enter a 'y' or 'n': ");
               again = keyboard.next().charAt( 0 );
               System.out.println();
            }

         }
         while ( again == 'y' );
         
         System.out.print("Do you want to do try another farm (y/n)? ");
         anotherFarm = keyboard.next().charAt( 0 );
         System.out.println();
         
         while ( anotherFarm != 'y' && anotherFarm != 'n' )
         {
            System.out.print("ERROR! Enter a 'y' or 'n': ");
            anotherFarm = keyboard.next().charAt( 0 );
            System.out.println();
         }

      }
      while ( anotherFarm == 'y' );
      System.out.println("Have a nice day");
   }
   
   public static int givenTime 
      ( int carryingCapacity, int initActualPop, double growthRate, int nMonths,
         boolean userChoice )
   {
      int i, endActualPop;
      double endRelativePop, initRelativePop;
      if ( userChoice )
      {
         System.out.println("MONTH\tPOPULATION\n");
         System.out.println("0\t" + initActualPop);
      }
      
      endActualPop = initActualPop;
       
      for ( i = 1; i <= nMonths; ++i )
      {
         initRelativePop = endActualPop / (double)carryingCapacity;
         endRelativePop = ( initRelativePop + ( growthRate * initRelativePop *
            ( 1 - initRelativePop ) ) );
         endActualPop = (int)Math.round ( endRelativePop * carryingCapacity );
      
         if ( userChoice )
         {
            System.out.println( i + "\t" + endActualPop );
         }
         
      }
      
      System.out.println();
      
      return endActualPop;  
   } 
   
   public static int givenPopulation
      ( int carryingCapacity, int initActualPop, double growthRate,
         boolean userChoice, int desiredPop )
   {
      int amtMonths, endActualPop;
      double endRelativePop, initRelativePop;
      
      if ( userChoice )
      {
         System.out.println("MONTH\tPOPULATION\n");
         System.out.println("0\t" + initActualPop);
      }
      
      amtMonths = 1;
      
      endActualPop = initActualPop;
      
      do
      {
         initRelativePop = endActualPop / (double)carryingCapacity;
         endRelativePop = ( initRelativePop + ( growthRate * initRelativePop * 
            ( 1 - initRelativePop ) ) );
         endActualPop = (int)Math.round ( endRelativePop * carryingCapacity );
  
         if ( userChoice )
         {
            System.out.println( amtMonths + "\t" + endActualPop );
         }
         
         amtMonths = amtMonths + 1;
      }
      while ( endActualPop < desiredPop );
      
      System.out.println();
      
      amtMonths = amtMonths - 1;
      
      return amtMonths;
   }       
}
     


/*      Code by: Nik Herron CarBrand.java -- This program takes input from the standard input with data validation and stores the variable to memory. This value is then tested to see if the array contains it. If it's found, the programs outputs the location of the element. 
 This is done 3 times before the program closes. Lastly, the program outputs the array of car makes.

        INPUT: String from terminal three times.
        PROCESSING: Validates String to make sure it is non-zero in length and reprompts input is input is invalid. This data is then compared to our "exhaustive database" (2d Array) that checks membership of the element in the main array. If the element exists in the array, then the program outputs the row the element
        resides at. Finally, the program outputs our exhaustive list of car makes. 
        OUTPUT: If car make exists in the 2d table,  it outputs the make along with its row in the array. If make doesnt exist then outputs the brand was not found. Lastly, the program cycles through the first column of the array to output each brand in our database.
*/
import java.util.Scanner;

public class CarBrand{

    public static void main(String[] args){
        final int NUM_TIMES = 3;
        int numCounter = 0;
        int foundRow;
        boolean isFound = false;
        String[][] fullMakes = new String[2][8];
        String make;
        String carBrand;
        fullMakes = CarBrand.makeMakes();
        
       
       
        for (int i = 0; i < NUM_TIMES; i++){
           System.out.println("Buyer number " + (i + 1) + ".");
           make = CarBrand.getInput();
           System.out.println("We are searching inventory for a " + make + ".");
           foundRow = searchMakes(make, fullMakes);
           if (foundRow != 99){
              vendCar(fullMakes, foundRow);
           }
         } //end for 
    
         System.exit(0);
    }//end main
    
   
    public static String getInput(){
        /*
       Input: This method takes in no parameters but gets input from keyboard via scanner
       Process: This method validates the input data to make sure it is not zero in length, if data is invalid, scanner will reprompt
       Output:This method outputs a string that should represent the make of the car, input by the human 
       */
 
      Scanner scanner;
      boolean isValid = false;
      String errorMSG = "Invalid Input please try again. (Enter String with length greater than 0, Make can not contain any numbers)";
      scanner = new Scanner(System.in);
      String input = new String("default");
      
      while (!isValid) {
         
         System.out.println("What model car do you want to buy?");
         input = scanner.nextLine();
         if (input.length() > 0 && !(isNumeric(input))){
            isValid = true;
         } else {
            System.out.println(errorMSG);
         } //end if
      } //end while
    
      return input;
    } // end method
    
    //this static method given in part 5
    public static Boolean isNumeric(final String str){
      for (char c : str.toCharArray()){
         if(Character.isDigit(c)){
            return true;
         }//end if
      }//end for 
      return false;
    }
    
    /* Input: This method has no input
       Process: This method creates a nested array containing the car makes along with a further index for later use.
       Output: This method outputs a nested array containing a list of Makes with 6 spaces.
    */
    
    public static String[][] makeMakes(){
      int numMakes = 8;
      int cols = 2;
      String[] makes = {"Chevy", "Ford", "BMW", "GMC", "Lexus", "Audi", "Honda", "Acura"};
      String[][] fullMakes = new String[numMakes][cols];
       for (int counter = 0; counter < fullMakes.length; counter++){
            fullMakes[counter][0] = makes[counter];
            fullMakes[counter][1] = "      ";
        } // end for

      return fullMakes;
    } //end method
    
    public static int searchMakes(String userInput, String[][] fullMakes){
           /*
       Input: This method takes in a nested array containing the makes of cars along with six spaces which we will prosumable use for later, a user input string is also input, this is the search term
       Process: This method iterates and searches the nested array for the make that the user has input.
       Output: If the make is found, the method will print to standard output, the location of where the make was in the array starting at 1.
       */   
      String foundMSG = "Make is in stock.";
      String notFoundMSG = "We do not have that make in stock.";
      boolean isFound = false;
      int row = 99;
      String make;
      
      for (int makeCounter = 0; makeCounter < fullMakes.length; makeCounter++){
         make = fullMakes[makeCounter][0];
         if (make.equals(userInput)) {
            isFound = true;
            row = makeCounter;
            System.out.println(foundMSG);
            break;
         }//end if 
         
      }// end for
      
      if (!isFound) {
         System.out.println(notFoundMSG);
      }//end if
      return row;
   }//end method
    
    

   public static void printMakes(String[][] fullMakes) {
          /*
       Input: This method takes in a nested array of makes
       Process: This method parses through the array and prints every sing make in the array
       Output:This method prints to standard output all of the makes contained in the provided nested array.
       */
      String fullListMSG = "The full list of makes are:  ";
      System.out.println();
      System.out.println(fullListMSG);
      for (int makeCounter = 0; makeCounter < fullMakes.length; makeCounter++){
            System.out.println(fullMakes[makeCounter][0]);
      }

   
   }//end method
   
   public static void printVendingMachine(String[][] fullMakes){
   
      int numCars = fullMakes.length;
      
      System.out.println("   Car Vending Machine   ");
      System.out.println("-------------------------");
      System.out.println("    Make      Your Car   ");
      System.out.println("-------------------------");
      
      for (int i = 0; i < numCars; i++){
         System.out.print(i + 1);
         System.out.println("   " + fullMakes[i][0] +"    " + fullMakes[i][1]);
      }
   
      return;
   }
   
   public static void vendCar(String[][] fullMakes, int row) {
      swapCar(fullMakes, row);
      printVendingMachine(fullMakes);
      for (int i = row; i < fullMakes.length - 1; i++) {
         lowerCar(fullMakes, i);
         printVendingMachine(fullMakes);
      }
      lowerCar(fullMakes, fullMakes.length - 1);
      return;
   }
   
   public static void swapCar(String[][] fullMakes, int row){
      String temp = fullMakes[row][0];
      fullMakes[row][0] = fullMakes[row][1];
      fullMakes[row][1] = temp;
      
      return;
   }
   
   public static void lowerCar(String[][] fullMakes, int row){
      if (fullMakes.length <= row + 1) {
         fullMakes[row][1] = "      ";
      } else {
         String temp = fullMakes[row][1];
         fullMakes[row][1] = fullMakes[row + 1][1];
         fullMakes[row + 1][1] = temp;
      } // endif 
      
      return;
   }

} //end class
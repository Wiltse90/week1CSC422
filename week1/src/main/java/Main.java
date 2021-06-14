/*
 * Steven Wiltse
 * Assignment 2, Part 2
 * Concordia St. Paul - SUMMER 100 CSC 422 Software Engineering
 * 
 * written: 05/20/2021
 */

 /**
  * Pet database part 2 - Allow for saving and add error handling 
  */

import java.util.Scanner; 
import java.util.ArrayList;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException; 

public class Main {
  public static void main(String[] args) {
  
  /***********************************************************
   * Initialize database
   ***********************************************************/
  
  // Create petDB.txt if it does not exist. 
  // Used to save and load pet database data
  // w3 tutorial for error handling: https://www.w3schools.com/java/java_files_create.asp
  try {
      File myObj = new File("petDB.txt");
      myObj.createNewFile();
  } catch (IOException e) {
      System.out.println("An error has occurred while creating the database.");
      e.printStackTrace();
  }

  
  // Class Attributes
  boolean run = true; // Tracks user interface menu to continue 
  Scanner select = new Scanner(System.in);  // Create a Scanner object
  ArrayList<Pet> pets = loadDB(); // Load DB text file into ArrayList

  
  
  // User interface menu
  while(run){
    System.out.println("What would you like to do?");
    System.out.println("1) View all pets");
    System.out.println("2) Add more pets");
    System.out.println("3) Remove an existing pet");
    System.out.println("4) Exit program");
    
    // Read user input selection
    int userChoice = select.nextInt();
    System.out.println("\nYour choice: " + userChoice);
    
    
    // Execute action based on user input
    switch(userChoice) {
        
        // Display all pets in pets arraylist
        case 1:
            int searchCount = 0; // Tracks size of DB
            System.out.println("+-------------------------+"); // Table header
            System.out.printf("| %-3s | %-10s | %-4s |%n","ID","NAME","AGE");
            System.out.println("+-------------------------+");
            for (int i = 0; i < pets.size(); i++) {
                System.out.printf("| %3s | %-10s | %4s |%n",i, pets.get(i).getName(), pets.get(i).getAge());
                searchCount++;
            }
            System.out.println("+-------------------------+"); // End table
            System.out.println(searchCount + " row(s) in the set.");
            searchCount = 0;
            break;
        
        // Add new Pet oject to pets array list
        case 2:
            Pet pet;
            String petName;
            int petAge;
            int trackNewPets = 0;
            boolean addPet = true;
            Scanner add = new Scanner(System.in);  // Create a Scanner object

            // Loop to add new Pet objects to pets arrayList
            while (addPet){
                System.out.println("add pet (name age): ");
                String data = add.nextLine();

                
                String [] petData = data.split(" ");
                // Error handling 
               
                // Checks if user is done adding Pets
                if (petData[0].toLowerCase().trim().equals("done")){
                    addPet = false;
                    System.out.println(trackNewPets + " pets added.");
                    continue;
                }
                
                
                // Checks that second value is an integer between 1 and 20
                try {
                    petName = petData[0];
                    petAge = Integer.parseInt(petData[1]);
                    if(petAge < 1 || petAge > 20) {
                        System.out.println("Error: " + petAge + " is not a valid age.");
                        continue;
                    }
                    
                } catch (Exception e) {
                    System.out.println("Error: " + data + " is not a valid age.");
                    continue;
                }
                
                // Checks that the database will not have more than 5 entries
                if (pets.size() >= 5) {
                    System.out.println("Error: Database is full.");
                    addPet = false;
                    continue;
                }
                
                // Checks that the entry has only 2 values
                if (petData.length != 2){
                    System.out.println("Error: " + data + " is not a valid input.");
                    continue;
                }
                
                // Create pet object and adds it to pets ArrayList
                try{
                    trackNewPets++;
                    pet = new Pet(petName, petAge);
                    pets.add(pet);
                } catch (Exception e){
                    System.out.println("Error: Failed to add pet to database.");
                }
                
            }
            
            break;

        // Remove existing Pet in pets arraylist
        case 3:
            // Get pet ID to delete
            System.out.println("Enter the pet ID to remove: ");
            
            int petID = select.nextInt();
            
            if(checkIndex(petID, pets)){
                // Display string for user
                String oldEntry = pets.get(petID).getName() + " " + pets.get(petID).getAge();
                // Delete pet data
                pets.remove(petID);
                System.out.println(oldEntry + " was removed.");
            } else {
                System.out.println("ID " + petID + " does not exist.");
            }

            break;
        
        // Exit program
        case 4:
            run = false; // Check for while loop
            System.out.println("Goodbye!");
            saveDB(pets);
            break;
        // If input is not an int between 1-4 direct user
        default:
            System.out.println("Please enter a number from 1-4.");
    }
    // Newline before menu
    System.out.println();
  } // End of while loop
 
 }
  
  // Checks if index is in arraylist
  public static boolean checkIndex(int id, ArrayList list){
      
      try {
          list.get(id);
          return true;
      } catch ( IndexOutOfBoundsException e ) {
          return false;
      }
  }
  
  // i/o methods for database file (petDB.txt)
  
  //***********************************************************
  // saveDB(): Sava Pet object arraylist to petDB text file
  // Parameters: pets: ArrayList of Pet objects
  //***********************************************************
  public static void saveDB(ArrayList<Pet> pets){
      try {
          FileWriter myWriter = new FileWriter("petDB.txt");
          for(Pet p: pets){
              myWriter.write(p.toString() + "\n");
          }
          myWriter.close();
          /* 
          System.out.println("DEBUG: Successfully wrote to the file.");
            */
      } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
      }
  }

  //**************************************************************************
  // loadDB(): Loads pet data from text file. Creates Pet objects from data 
  //            and loads them into an ArrayList   
  //**************************************************************************
  public static ArrayList<Pet> loadDB(){
      
      // Holds Pet objects created from text file
      ArrayList<Pet> petDB = new ArrayList<Pet>();
      
      try {
          // Load text file for pet DB
          File myObj = new File("petDB.txt");
          Scanner myReader = new Scanner(myObj);
          
          // Create Pet object from each line of pet data and load to petDB arr
          while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              // Split data line into name(string), age(int) values for Pet object
              String[] petVals = data.split(" ");
              Pet pet = new Pet(petVals[0], Integer.parseInt(petVals[1]));
              petDB.add(pet);
          }
          myReader.close();
      } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
      }
      return petDB;
  }
}
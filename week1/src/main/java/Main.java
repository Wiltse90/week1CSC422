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
  
  // Create petDB.txt if it does not exist. 
  // Used to save and load pet database data
  // w3 tutorial for error handling: https://www.w3schools.com/java/java_files_create.asp
  try {
      File myObj = new File("petDB.txt");
      myObj.createNewFile();
      /* DEBUG
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }*/
  } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
  }

  
  // Class Vars
  boolean run = true; // Tracks user interface menu to continue 
  int searchCount = 0; // Tracks size of DB
  Scanner select = new Scanner(System.in);  // Create a Scanner object
  ArrayList<Pet> pets = loadDB(); // Load DB text file into ArrayList
  
  
  // User interface menu
  while(run){
    System.out.println("What would you like to do?");
    System.out.println("1) View all pets");
    System.out.println("2) Add more pets");
    System.out.println("3) Update an existing pet");
    System.out.println("4) Remove an existing pet");
    System.out.println("5) Search pets by name");
    System.out.println("6) Search pets by age");
    System.out.println("7) Exit program");
    
    // Read user input selection
    int userChoice = select.nextInt();
    System.out.println("\nYour choice: " + userChoice);
    
    // Execute action based on user input
    switch(userChoice) {
        
        // Display all pets in pets arraylist
        case 1:
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
            boolean addPet = true;
            int petAge;
            String petName;
            
            // Loop to add new Pet objects to pets arrayList
            while (addPet){
                System.out.println("add pet (name age): ");
                petName = select.next();
                petAge = select.nextInt();
                pet = new Pet(petName, petAge);
                
                // Checks if user is done adding Pets
                if (petName.toLowerCase().trim().equals("done")){
                    addPet = false;
                } else {
                    pets.add(pet);
                }
                
            }
            System.out.println("pets added.");
        break;
        
        // Update existing Pet in pets arraylist
        case 3:
            // Get pet to update
            System.out.println("Enter the pet ID to update: ");
            int petID = select.nextInt();
            
            // Get new info
            System.out.println("Enter new name and new age (name age): ");
            petName = select.next();
            petAge = select.nextInt();
            
            // Display strings for user
            String oldEntry = pets.get(petID).getName() + " " + pets.get(petID).getAge();
            String newEntry = petName + " " + petAge;
            
            // Update pet data
            pets.get(petID).setName(petName);
            pets.get(petID).setAge(petAge);
            System.out.println(oldEntry + " was changed to " + newEntry + ".");
        break;
        
        // Remove existing Pet in pets arraylist
        case 4:
            // Get pet ID to delete
            System.out.println("Enter the pet ID to remove: ");
            petID = select.nextInt();
            
            // Display string for user
            oldEntry = petID + " " + pets.get(petID).getName() + " " + pets.get(petID).getAge();
            
            // Delete pet data
            pets.remove(petID);
            System.out.println(oldEntry + " was removed.");
            
        break;
        
        // Search for existing Pets by name in pets arraylist
        case 5:
            
            System.out.println("Enter a name to search: ");
            String searchName = select.next();
            System.out.println();
            
            // Search pets arraylist for pets by name
            for (int i = 0; i < pets.size(); i++) {
                if(pets.get(i).getName().toLowerCase().equals(searchName.toLowerCase())){
                    if (searchCount < 1){
                        System.out.println("+-------------------------+");
                        System.out.printf("| %-3s | %-10s | %-4s |%n","ID","NAME","AGE");
                        System.out.println("+-------------------------+");
                        
                    }
                    System.out.printf("| %3s | %-10s | %4s |%n",i, pets.get(i).getName(), pets.get(i).getAge());
                    searchCount++;
                }
            }
            System.out.println("+-------------------------+"); // End of table
            
            // Print number of search results 
            System.out.println(searchCount + " row(s) in the set.");
            searchCount = 0;
            
        break;
        
        // Search for existing Pets by age in pets arraylist
        case 6:
            System.out.println("Enter an age to search: ");
            int searchAge = select.nextInt();
            System.out.println();
            
            // Search pets arraylist for pets by age
            for (int i = 0; i < pets.size(); i++) {
                if(pets.get(i).getAge() == searchAge){
                    if (searchCount < 1){
                        System.out.println("+-------------------------+");
                        System.out.printf("| %-3s | %-10s | %-4s |%n","ID","NAME","AGE");
                        System.out.println("+-------------------------+");
                    }
                    System.out.printf("| %3s | %-10s | %4s |%n",i, pets.get(i).getName(), pets.get(i).getAge());
                    searchCount++;
                }
            }
            System.out.println("+-------------------------+"); // End of table
            
            // Print number of search results 
            System.out.println(searchCount + " row(s) in the set.");
            searchCount = 0;
            
        break;
        
        // Exit program
        case 7:
            run = false; // Check for while loop
            System.out.println("Goodbye!");
            saveDB(pets);
        break;
        // If input is not an int between 1-7 direct user
        default:
            System.out.println("Please enter a number from 1-7.");
    }
    // Newline before menu
    System.out.println();
  } // End of while loop
 
 }
  
  // i/o methods for database
  
  // saveDB(): Sava Pet object arraylist to petDB text file
  // Parameters: pets: ArrayList of Pet objects
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


  // loadDB(): Loads pet data from text file. Creates Pet objects from data 
  //            and loads them into an ArrayList       
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
/*
 * Steven Wiltse
 * Assignment 1, Part 2
 * Concordia St. Paul - SUMMER 100 CSC 422 Software Engineering
 * 
 * written: 05/12/2021
 * revised: 05/14/2021
 *          05/15/2021
 */

 /**
  * Pet database
  */

import java.util.Scanner; 
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
  boolean run = true;
  ArrayList<Pet> pets = new ArrayList<Pet>();
  int searchCount = 0;
  
  // Populate default pet data
  Pet pet;
  pet = new Pet("Kitty", 8);
  pets.add(pet);
  pet = new Pet("Bruno", 7);
  pets.add(pet);
  pet = new Pet("Boomer", 8);
  pets.add(pet);
  pet = new Pet("Boomer", 3);
  pets.add(pet);
  pet = new Pet("Fiesty", 3);
  pets.add(pet);

  Scanner select = new Scanner(System.in);  // Create a Scanner object
  
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
        break;
        // If input is not an int between 1-7 direct user
        default:
            System.out.println("Please enter a number from 1-7.");
    }
    // Newline before menu
    System.out.println();
  } // End of while loop
 }
}
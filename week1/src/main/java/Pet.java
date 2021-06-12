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
  * Pet Class for pet database
  */
public class Pet {
    private String petName;
    private int petAge;

    public Pet(){} // Default Constructor
    
    public Pet(String name, int age){
        petName = name;
        petAge = age;
    }
    // Getter Methods
    public String getName(){
        return petName;
    }
    public int getAge(){
        return petAge;
    }
    // Setter Methods
    public void setName(String name){
        petName = name;
    }
    public void setAge(int age){
        petAge = age;
    }
    // toString 
    public String toString(){
        return  petName + " " + petAge;
    }
}

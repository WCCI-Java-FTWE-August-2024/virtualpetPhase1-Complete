package wcci.org.virtualpet.Models;

import jakarta.persistence.Entity;
import wcci.org.virtualpet.Enums.*;

/**
 * The RoboticDogModel class represents a specific type of pet, a robotic dog, which extends the PetModel class.
 * It includes an implementation of the Speak method specific to dogs.
 */
@Entity
public class RoboticDogModel extends RoboticPetModel {
    /**
     * Default Constructor
     */
    public RoboticDogModel() {
    }

    /**
     * 
     * @param name name of dog
     * @param age age of dog
     */
    public RoboticDogModel(String name, int age) {
        super(name, PetType.ROBOTIC_DOG, age);
    }

    /**
     * Returns the sound the dog makes based on its current state.
     *
     * @return A string representing the dog's speech.
     */
    @Override
    public String speak() {
        if (this.isDead()) { // Check if the dog is dead
            return "I am Dead"; // Return dead message
        } else if (this.getPowerLevel() <= 20) { // Check if the dog's happiness is 20 or less
            return "Grrrrr"; // Return grumpy sound
        }
        return "Bark"; // Return barking sound for happy dogs
    }

   

}

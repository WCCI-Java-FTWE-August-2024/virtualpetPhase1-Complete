package wcci.org.virtualpet.Models;

import jakarta.persistence.Entity;
import wcci.org.virtualpet.Enums.PetType;
/**
 * The RoboticCatModel class represents a specific type of robotic pet, a cat, which extends the PetModel class.
 * It includes an implementation of the Speak method specific to cats.
 */
@Entity
public class RoboticCatModel extends RoboticPetModel {
    /**
     * Default Constructor
     */
    public RoboticCatModel() {
    }

    /**
     * Constructor with Parameters
     * @param name name of Cat
     * @param age age of Cat
     */
    public RoboticCatModel(String name, int age) {
        super(name, PetType.ROBOTIC_CAT, 1);
    }

    /**
     * Returns the sound the cat makes based on its current state.
     *
     * @return A string representing the Cat's speech.
     */
    @Override
    public String speak() {
        if (this.isDead()) { // Check if the Cat is dead
            return "I am Dead"; // Return dead message
        } else if (this.getPowerLevel() <= 20) { // Check if the Cat's happiness is 20 or less
            return "Grrrrr"; // Return grumpy sound
        }
        return "Beep Purr Beep"; // Return barking sound for happy Dogs
    }

   

}


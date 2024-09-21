package wcci.org.virtualpet.Models;

import jakarta.persistence.Entity;
import wcci.org.virtualpet.Enums.*;


/**
 * The CatModel class represents a specific type of pet, a cat, which extends the PetModel class.
 * It includes an implementation of the Speak method specific to cats.
 */
@Entity
public class CatModel extends PetModel {

    /**
     * Constructor to initialize a new CatModel instance.
     *
     * @param name The name of the cat.
     * @param age  The age of the cat.
     */
    public CatModel(String name, int age) {
        super(name, PetType.CAT, age);
    }

    /**
     * Returns the sound the cat makes based on its current state.
     *
     * @return A string representing the cat's speech.
     */
    @Override
    public String speak() {
        if (this.isDead()) { // Check if the cat is dead
            return "I am Dead"; // Return dead message
        } else if (this.getHappiness() <= 20) { // Check if the cat's happiness is 20 or less
            return "Grrrrr"; // Return grumpy sound
        }
        return "Pur"; // Return purring sound for happy cats
    }

    
}

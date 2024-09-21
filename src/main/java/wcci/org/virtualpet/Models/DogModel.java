package wcci.org.virtualpet.Models;

import jakarta.persistence.Entity;
import wcci.org.virtualpet.Enums.*;

/**
 * The DogModel class represents a specific type of pet, a dog, which extends the PetModel class.
 * It includes an implementation of the Speak method specific to dogs.
 */
@Entity
public class DogModel extends PetModel{
 
    /**
     * Constructor to initialize a new DogModel instance.
     *
     * @param name The name of the dog.
     * @param age  The age of the dog.
     */
    public DogModel(String name, int age) {
        super(name, PetType.DOG, age);
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
        } else if (this.getHappiness() <= 20) { // Check if the dog's happiness is 20 or less
            return "Grrrrr"; // Return grumpy sound
        }
        return "Bark"; // Return barking sound for happy dogs
    }
    @Override
    public void feed(){
        if (!isDead()) { // Check if the pet is not dead before performing action
            this.setHungery(getHungery() - 20); // Decrease hunger
            this.setHealth(getHealth() + 5); // Increase health
            this.setHappiness(getHappiness() + 10); // Increase happiness
            this.setThirst(getThirst() + 10); // Increase thirst
        }
    }

   
}

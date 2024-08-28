package wcci.org.virtualpet.Models;

import wcci.org.virtualpet.Enums.*;
import wcci.org.virtualpet.Exceptions.ValidateException;

/**
 * The PetModel class represents a virtual pet with various attributes such as
 * name, type, age, health, happiness, and hunger.
 * It includes methods to manipulate and check the state of the pet.
 */
public abstract class PetModel {
    private static long idCounter = 1; // Counter to generate unique IDs for pets
    private long id; // Unique identifier for the pet
    private String name; // Name of the pet
    private PetType type; // Type of the pet (e.g., DOG, CAT)
    private int age; // Age of the pet
    private int health; // Health level of the pet (0-100)
    private int happiness; // Happiness level of the pet (0-100)
    private int hungery; // Hunger level of the pet (0-100)
    private int thirst; // Thirst level of the pet (0-100)
    private DeathBy died; // Reason for the pet's death, if any

    /**
     * Constructor to initialize a new PetModel instance.
     *
     * @param name The name of the pet.
     * @param type The type of the pet.
     * @param age  The age of the pet.
     */
    public PetModel(String name, PetType type, int age) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.health = 0; // Initialize health to 0
        this.happiness = 0; // Initialize happiness to 0
        this.hungery = 0; // Initialize hunger to 0
        this.thirst = 0;
        this.id = idCounter++; // Assign unique ID to the pet
        this.died = DeathBy.None; // Initialize died status to None
    }

    /**
     * Gets the cause of death
     * 
     * @return DeathBy Enum
     */
    public DeathBy getDied() {
        return died;
    }

    /**
     * Gets the unique identifier of the pet.
     *
     * @return The pet's ID.
     */
    public long getId() {
        return id;
    }

    /**
     * Checks if the pet is dead.
     *
     * @return True if the pet is dead, otherwise false.
     */
    public boolean isDead() {
        return !this.died.equals(DeathBy.None);
    }

    /**
     * Gets the name of the pet.
     *
     * @return The pet's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the pet.
     *
     * @param name The new name of the pet.
     * @throws ValidateException
     */
    public void setName(String name) throws ValidateException {
        if (name == null || name.length() == 0) {
            throw new ValidateException("Invalid length for name, please try again\nName can not be empty");
        } else if (name.length() > 50) {
            throw new ValidateException("Invalid length for name, please try again\nmust be less then 50 characters");
        }
        this.name = name;
    }

    /**
     * Gets the type of the pet.
     *
     * @return The pet's type.
     */
    public PetType getType() {
        return type;
    }

    /**
     * Sets the type of the pet.
     *
     * @param type The new type of the pet.
     */
    public void setType(PetType type) {
        this.type = type;
    }

    /**
     * Gets the age of the pet.
     *
     * @return The pet's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the pet.
     *
     * @param age The new age of the pet.
     * @throws ValidateException
     */
    public void setAge(int age) throws ValidateException {
        if (age <= 0 || age > 20) {
            throw new ValidateException("Invalid value for age, please try again\n0 and 20 are the correct age values");
        }
        this.age = age;
    }

    /**
     * Gets the health level of the pet.
     *
     * @return The pet's health level.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health level of the pet, ensuring it stays within bounds (0-100).
     *
     * @param value The new health level.
     */
    public void setHealth(int value) {
        if (value > 100) {
            value = 100; // Ensure health does not exceed 100
        } else if (value <= 0) {
            value = 0; // Ensure health does not go below 0
            this.died = DeathBy.Disease; // Set death reason if health reaches 0
        }
        this.health = value;
    }

    /**
     * Gets the thirst level of the pet.
     * 
     * @return thirst level
     */
    public int getThirst() {
        return thirst;
    }

    /**
     * Sets the thirst level of the pet, ensuring it stays within bounds (0-100).
     * 
     * @param thirst
     */
    public void setThirst(int value) {
        if (value > 100) {
            value = 100; // Ensure thirst does not exceed 100
            this.died = DeathBy.Thirst; // Set death reason if thirst reaches 0
        } else if (value <= 0) {
            value = 0; // Ensure thirst does not go below 0
        }
        this.thirst = value;
    }

    /**
     * Gets the happiness level of the pet.
     *
     * @return The pet's happiness level.
     */
    public int getHappiness() {
        return happiness;
    }

    /**
     * Sets the happiness level of the pet, ensuring it stays within bounds (0-100).
     *
     * @param value The new happiness level.
     */
    public void setHappiness(int value) {
        if (value > 100) {
            value = 100; // Ensure happiness does not exceed 100
        } else if (value <= 0) {
            value = 0; // Ensure happiness does not go below 0
            this.died = DeathBy.Loneliness; // Set death reason if happiness reaches 0
        }
        this.happiness = value;
    }

    /**
     * Gets the hunger level of the pet.
     *
     * @return The pet's hunger level.
     */
    public int getHungery() {
        return hungery;
    }

    /**
     * Sets the hunger level of the pet, ensuring it stays within bounds (0-100).
     *
     * @param value The new hunger level.
     */
    public void setHungery(int value) {
        if (value >= 100) {
            value = 100; // Ensure hunger does not exceed 100
            this.died = DeathBy.Starvation; // Set death reason if hunger reaches 100
        } else if (value < 0) {
            value = 0; // Ensure hunger does not go below 0
        }
        this.hungery = value;
    }

    /**
     * Waters the pet, decreasing thirst and increasing health and happiness.
     */
    public void water() {
        if (!isDead()) { // Check if the pet is not dead before performing action
            this.setThirst(getThirst() - 10); // Decrease thirst
            this.setHealth(getHealth() + 5); // Increase health
            this.setHappiness(getHappiness() + 15); // Increase happiness
        }
    }

    /**
     * Feeds the pet, decreasing hunger and increasing health and happiness.
     */
    public void feed() {
        if (!isDead()) { // Check if the pet is not dead before performing action
            this.setHungery(getHungery() - 10); // Decrease hunger
            this.setHealth(getHealth() + 5); // Increase health
            this.setHappiness(getHappiness() + 15); // Increase happiness
            this.setThirst(getThirst() + 5); // Increase thirst
        }
    }

    /**
     * Plays with the pet, increasing hunger and happiness, and slightly increasing
     * health.
     */
    public void play() {
        if (!isDead()) { // Check if the pet is not dead before performing action
            this.setHungery(getHungery() + 15); // Increase hunger
            this.setHealth(getHealth() + 10); // Increase health
            this.setHappiness(getHappiness() + 20); // Increase happiness
            this.setThirst(getThirst() + 15); // Increase thirst
        }
    }

    /**
     * Heals the pet, increasing hunger, health, and happiness.
     */
    public void heal() {
        if (!isDead()) { // Check if the pet is not dead before performing action
            this.setHungery(getHungery() + 15); // Increase hunger
            this.setHealth(getHealth() + 10); // Increase health
            this.setHappiness(getHappiness() + 20); // Increase happiness
            this.setThirst(getThirst() + 10); // Increase thirst
        }
    }

    /**
     * Abstract method for pet's speech, to be implemented by subclasses.
     *
     * @return A string representing the pet's speech.
     */
    public abstract String speak();

    /**
     * Simulates the passage of time, affecting the pet's hunger, happiness, and
     * health.
     */
    public void passageOfTime() {
        setHungery(getHungery() + getHungery() / 5); // Increase hunger over time
        setHappiness(getHappiness() - getHappiness() / 2); // Decrease happiness over time
        setHealth(getHealth() - getHealth() / 10); // Decrease health over time
        setThirst(getThirst() + getThirst() / 5); // Increase thirst
    }

    /**
     * Checks the health of the pet and returns a status message.
     *
     * @return A string representing the pet's health status.
     */
    public String checkHealth() {
        if (isDead()) { // Check if the pet is dead
            return String.format("%s is Dead from %s", this.name, this.died); // Return death reason
        } else if (this.health < 20) { // Check if health is below 20%
            return String.format("%s is not healthy at %d%%", this.name, this.health); // Return health status
        }

        return this.toString(); // Return full pet details if healthy
    }

    /**
     * Checks the happiness of the pet and returns a status message.
     *
     * @return A string representing the pet's happiness status.
     */
    public String checkHappiness() {
        if (isDead()) { // Check if the pet is dead
            return String.format("%s is Dead from %s", this.name, this.died); // Return death reason
        } else if (this.happiness < 20) { // Check if happiness is below 20%
            return String.format("%s is not happy at %d%%", this.name, this.happiness); // Return happiness status
        }
        return this.toString(); // Return full pet details if happy
    }

    /**
     * Returns a string representation of the pet.
     *
     * @return A string representing the pet's details.
     */
    @Override
    public String toString() {
        if (isDead()) {
            return "PetModel [id=" + id + ", name=" + name + ", type=" + type + ", age=" + age + "Died because of "
                    + died + "]";
        }
        return "PetModel [id=" + id + ", name=" + name + ", type=" + type + ", age=" + age + ", health=" + health
                + "%, happiness=" + happiness + "%, hungery=" + hungery + "%, thirsty=" + thirst + "%]";
    }
}

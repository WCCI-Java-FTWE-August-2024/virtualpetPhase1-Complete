package wcci.org.virtualpet.Models;


import jakarta.persistence.MappedSuperclass;
import wcci.org.virtualpet.Enums.*;
import wcci.org.virtualpet.Interfaces.PetInterface;

/**
 * The PetModel class represents a virtual pet with various attributes such as
 * name, type, age, health, happiness, and hunger.
 * It includes methods to manipulate and check the state of the pet.
 */
@MappedSuperclass
public abstract class PetModel extends CommonModel implements PetInterface {

    private int health; // Health level of the pet (0-100)
    private int happiness; // Happiness level of the pet (0-100)
    private int hungery; // Hunger level of the pet (0-100)
    private int thirst; // Thirst level of the pet (0-100)

    /**
     * Constructor to initialize a new PetModel instance.
     *
     * @param name The name of the pet.
     * @param type The type of the pet.
     * @param age  The age of the pet.
     */
    public PetModel(String name, PetType type, int age) {
        super(name, type, age);
        this.health = 50; // Initialize health to 0
        this.happiness = 40; // Initialize happiness to 0
        this.hungery = 20; // Initialize hunger to 0
        this.thirst = 10; // initialize thrist
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
            this.setDeathBy(DeathBy.Disease); // Set death reason if health reaches 0
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
    public void setThirst(int thirst) {
        if (thirst > 100) {
            thirst = 100; // Ensure thirst does not exceed 100
            this.setDeathBy(DeathBy.Thirst); // Set death reason if thirst reaches 0
        } else if (thirst <= 0) {
            thirst = 0; // Ensure thirst does not go below 0
        }
        this.thirst = thirst;
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
            this.setDeathBy(DeathBy.Loneliness); // Set death reason if happiness reaches 0
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
            this.setDeathBy(DeathBy.Starvation); // Set death reason if hunger reaches 100
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
            return String.format("%s is Dead from %s", this.getName(), this.getDeathBy()); // Return death reason
        } else if (this.health < 20) { // Check if health is below 20%
            return String.format("%s is not healthy at %d%%", this.getName(), this.health); // Return health status
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
            return String.format("%s is Dead from %s", this.getName(), this.getDeathBy()); // Return death reason
        } else if (this.happiness < 20) { // Check if happiness is below 20%
            return String.format("%s is not happy at %d%%", this.getName(), this.happiness); // Return happiness status
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
            return "PetModel [id=" + getId() + ", name=" + this.getName() + ", type=" + getType() + ", age=" + getAge()
                    + "deathBy because of "
                    + getDeathBy() + "]";
        }
        return "PetModel [id=" + getId() + ", name=" + this.getName() + ", type=" + getType() + ", age=" + getAge()
                + ", health=" + health
                + "%, happiness=" + happiness + "%, hungery=" + hungery + "%, thirsty=" + thirst + "%]";
    }
}

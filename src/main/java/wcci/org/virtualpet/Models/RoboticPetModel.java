package wcci.org.virtualpet.Models;

import jakarta.persistence.MappedSuperclass;
import wcci.org.virtualpet.Enums.*;
import wcci.org.virtualpet.Interfaces.PetInterface;

/**
 * Base abstract model for robotic pets
 */
@MappedSuperclass
public abstract class RoboticPetModel extends CommonModel implements PetInterface {
    private int oilLevel; // Health level of the pet (0-100)
    private int powerLevel; // Happiness level of the pet (0-100)

    /**
     * Default Constructor
     */
    protected RoboticPetModel() {
    }

    /**
     * Constructor with Parameters
     * 
     * @param name
     * @param type
     * @param age
     */
    public RoboticPetModel(String name, PetType type, int age) {
        super(name, type, age);
        this.oilLevel = 100;
        this.powerLevel = 100;
    }

    /** gets the oilLevel */
    public int getOilLevel() {
        return oilLevel;
    }

    public void setOilLevel(int oilLevel) {
        this.oilLevel = oilLevel;
        if (this.oilLevel >= 100) {
            this.oilLevel = 100;
        } else if (this.oilLevel <= 0) {
            this.oilLevel = 0;
            this.setDeathBy(DeathBy.NoOil);
        }

    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
        if (this.powerLevel >= 100) {
            this.powerLevel = 100;
        } else if (powerLevel <= 0) {
            powerLevel = 0;
            this.setDeathBy(DeathBy.NoPower);
        }
    }

    /**
     * The Feed method adds to the OilLevel
     */
    @Override
    public void feed() {
        this.setOilLevel(this.getOilLevel() + 15);
    }

    /**
     * Playing with the pet drains some oil and some power
     * But increases the Happniess level
     */
    @Override
    public void play() {
        this.setOilLevel(this.getOilLevel() - 5);
        this.setPowerLevel(this.getPowerLevel() - 5);
    }

    @Override
    public void heal() {
        this.setPowerLevel(this.getPowerLevel() + 15);
    }

    @Override
    public void passageOfTime() {
        this.setOilLevel(this.getOilLevel() - 10);
        this.setPowerLevel(this.getPowerLevel() - 10);
    }

     /**
     * Checks the health of the pet and returns a status message.
     *
     * @return A string representing the pet's health status.
     */
    public String checkHealth() {
        if (isDead()) { // Check if the pet is dead
            return String.format("%s is Dead from %s", this.getName(), this.getDeathBy()); // Return death reason
        } else if (this.getPowerLevel() < 20) { // Check if health is below 20%
            return String.format("%s is not PowerLevel at %d%%", this.getName(), this.getPowerLevel()); // Return health status
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
        } else if (this.getOilLevel() < 20) { // Check if happiness is below 20%
            return String.format("%s is not happy at %d%%", this.getName(), this.getOilLevel()); // Return happiness status
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
            return "RoboticPet [id=" + getId() + ", name=" + this.getName() + ", type=" + getType() + ", age=" + getAge()
                    + "deathBy because of "
                    + getDeathBy() + "]";
        }
        return "RoboticPet [id=" + getId() + ", name=" + this.getName() + ", type=" + getType() + ", age=" + getAge()
                + ", OilLevel=" + this.getOilLevel() + "%, "
                + "%, PowerLevel=" + this.getPowerLevel()+  "%]";
    }

}

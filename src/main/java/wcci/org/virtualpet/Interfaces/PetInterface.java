package wcci.org.virtualpet.Interfaces;

import wcci.org.virtualpet.Enums.PetType;

/**
 * This is an interface which can be uesd by all of the pets
 * Common functions/methods
 */
public interface PetInterface {
    long getId();
    PetType getType();
    String getName();
    void feed();
    void play();
    void heal();
    String speak();
    void passageOfTime();
    boolean isDead();
    String checkHealth();
    String checkHappiness();
}

package wcci.org.virtualpet.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import wcci.org.virtualpet.Enums.DeathBy;
import wcci.org.virtualpet.Enums.PetType;
import wcci.org.virtualpet.Exceptions.ValidateException;


@MappedSuperclass
public abstract class CommonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name = "my_seq", sequenceName = "MY_SEQ", allocationSize = 1)
    private int id;

    private String name; // Name of the pet
    private PetType type; // Type of the pet (e.g., DOG, CAT)
    private int age; // Age of the pet
    private DeathBy deathBy; // Reason for the pet's death, if any
   
    public CommonModel() {
    }

    public CommonModel(String name, PetType type, int age) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.deathBy = DeathBy.None;
    }

public long getId(){
    return id;
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


    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

   /**
     * Gets the age of the pet.
     *
     * @return The pet's age.
     */
    public int getAge() {
        return this.age;
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


    public DeathBy getDeathBy() {
        return deathBy;
    }

    public void setDeathBy(DeathBy deathBy) {
        this.deathBy = deathBy;
    }

     /**
     * Checks if the pet is dead.
     *
     * @return True if the pet is dead, otherwise false.
     */
    public boolean isDead() {
        return !this.deathBy.equals(DeathBy.None);
    }

    /**
     * Abstract method for pet's speech, to be implemented by subclasses.
     *
     * @return A string representing the pet's speech.
     */
    public abstract String speak();

}

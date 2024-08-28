package wcci.org.virtualpet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wcci.org.virtualpet.Enums.PetType;
import wcci.org.virtualpet.Models.DogModel;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the DogModel class.
 * This class tests various scenarios for the DogModel class to ensure its methods
 * behave as expected.
 */
public class DogModelTest {

    private DogModel dog;

    /**
     * Set up a new DogModel instance before each test.
     * This method initializes the DogModel instance with a default name and age.
     */
    @BeforeEach
    public void setUp() {
        dog = new DogModel("Buddy", 3); // Initialize DogModel with name "Buddy" and age 3
    }
@Test
    void testInitialValues() {
        assertEquals("Buddy", dog.getName()); // Check initial name
        assertEquals(PetType.DOG, dog.getType()); // Check initial type
        assertEquals(3, dog.getAge()); // Check initial age
        assertEquals(0, dog.getHealth()); // Check initial health
        assertEquals(0, dog.getHappiness()); // Check initial happiness
        assertEquals(0, dog.getHungery()); // Check initial hunger
        assertFalse(dog.isDead()); // Check initial death status
    }
    /**
     * Test the Speak method when the dog is alive and happy.
     * This test ensures that when the dog's happiness is above 20, it returns "Bark".
     */
    @Test
    public void testSpeakHappy() {
        dog.setHappiness(50); // Set happiness to a value greater than 20
        assertEquals("Bark", dog.speak(), "Expected the dog to bark when happiness is above 20."); // Verify output
    }

    /**
     * Test the Speak method when the dog is alive but unhappy.
     * This test ensures that when the dog's happiness is 20 or less, it returns "Grrrrr".
     */
    @Test
    public void testSpeakUnhappy() {
        dog.setHappiness(15); // Set happiness to a value less than or equal to 20
        assertEquals("Grrrrr", dog.speak(), "Expected the dog to growl when happiness is 20 or less."); // Verify output
    }

    /**
     * Test the Speak method when the dog is dead.
     * This test ensures that when the dog's health is 0 (indicating it is dead), it returns "I am Dead".
     */
    @Test
    public void testSpeakDead() {
        dog.setHealth(0); // Set health to 0 to simulate death
        assertEquals("I am Dead", dog.speak(), "Expected the dog to say 'I am Dead' when health is 0."); // Verify output
    }

    /**
     * Test the Feed method.
     * This test ensures that feeding the dog correctly updates its hunger, health, and happiness.
     */
    @Test
    public void testFeed() {
        dog.setHungery(50); // Set hunger level to 50
        dog.setThirst(50); // Set thirst level to 50
        dog.feed(); // Feed the dog

        // Verify that hunger, thirst decreases, health increases, and happiness increases
        assertEquals(30, dog.getHungery(), "Expected hunger to decrease by 10 after feeding."); 
        assertEquals(60, dog.getThirst(), "Expected thirsty to increase by 5 after feeding."); 
        assertEquals(5, dog.getHealth(), "Expected health to increase by 5 after feeding."); 
        assertEquals(10, dog.getHappiness(), "Expected happiness to increase by 15 after feeding.");
    }

    /**
     * Test the Play method.
     * This test ensures that playing with the dog correctly updates its hunger, health, and happiness.
     */
    @Test
    public void testPlay() {
        dog.setHungery(50); // Set hunger level to 50
        dog.setThirst(50); // Set thirst level to 50
        dog.play(); // Play with the dog

        // Verify that hunger increases, health increases, and happiness increases
        assertEquals(65, dog.getHungery(), "Expected hunger to increase by 15 after playing.");
        assertEquals(65, dog.getThirst(), "Expected thirst to increase by 15 after playing.");
        assertEquals(10, dog.getHealth(), "Expected health to increase by 10 after playing.");
        assertEquals(20, dog.getHappiness(), "Expected happiness to increase by 20 after playing.");
    }

    /**
     * Test the Heal method.
     * This test ensures that healing the dog correctly updates its hunger, health, and happiness.
     */
    @Test
    public void testHeal() {
        dog.setHungery(50); // Set hunger level to 50
        dog.setThirst(50); // Set hunger level to 50
        dog.heal(); // Heal the dog

        // Verify that hunger increases, health increases, and happiness increases
        assertEquals(65, dog.getHungery(), "Expected hunger to increase by 15 after healing.");
        assertEquals(60, dog.getThirst(), "Expected hunger to increase by 15 after healing.");
        assertEquals(10, dog.getHealth(), "Expected health to increase by 10 after healing.");
        assertEquals(20, dog.getHappiness(), "Expected happiness to increase by 20 after healing.");
    }

    /**
     * Test the PassageOfTime method.
     * This test ensures that the passage of time correctly updates the dog's hunger, health, and happiness.
     */
    @Test
    public void testPassageOfTime() {
        dog.setHungery(50); // Set hunger level to 50
        dog.setHealth(50); // Set health level to 50
        dog.setHappiness(50); // Set happiness level to 50
        dog.setThirst(50); // Set thirst level to 50
        dog.passageOfTime(); // Simulate the passage of time

        // Verify that hunger increases, happiness decreases, and health decreases
        assertEquals(60, dog.getHungery(), "Expected hunger to increase by 10 after passage of time.");
        assertEquals(60, dog.getThirst(), "Expected thirst to increase by 10 after passage of time.");
        assertEquals(45, dog.getHealth(), "Expected health to decrease by 5 after passage of time.");
        assertEquals(25, dog.getHappiness(), "Expected happiness to decrease by 25 after passage of time.");
    }
}

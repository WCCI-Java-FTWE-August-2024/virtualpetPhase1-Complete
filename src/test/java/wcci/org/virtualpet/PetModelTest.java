package wcci.org.virtualpet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wcci.org.virtualpet.Enums.*;
import wcci.org.virtualpet.Exceptions.ValidateException;
import wcci.org.virtualpet.Models.PetModel;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the PetModel class.
 */
class PetModelTest {
    private PetModel pet;

    // Concrete subclass for testing
    private class TestPet extends PetModel {
        /**
         * Constructor for TestPet.
         *
         * @param name The name of the pet.
         * @param type The type of the pet.
         * @param age  The age of the pet.
         */
        public TestPet(String name, PetType type, int age) {
            super(name, type, age);
        }

        /**
         * Returns a test speech for the pet.
         *
         * @return A string representing the pet's speech.
         */
        @Override
        public String speak() {
            return "Test pet speaks!";
        }
    }

    /**
     * Sets up a new pet instance before each test.
     */
    @BeforeEach
    void setUp() {
        pet = new TestPet("Buddy", PetType.DOG, 3);
    }

    /**
     * Tests the initial values of the pet.
     */
    @Test
    void testInitialValues() {
        assertEquals("Buddy", pet.getName()); // Check initial name
        assertEquals(PetType.DOG, pet.getType()); // Check initial type
        assertEquals(3, pet.getAge()); // Check initial age
        assertEquals(0, pet.getHealth()); // Check initial health
        assertEquals(0, pet.getHappiness()); // Check initial happiness
        assertEquals(0, pet.getHungery()); // Check initial hunger
        assertEquals(0, pet.getThirst()); // Check initial thirst
        assertFalse(pet.isDead()); // Check initial death status
    }

    /**
     * Checks the name for validation Exception
     * @throws ValidateException
     */
    @Test
    void testSetName() throws ValidateException{
        assertThrows(ValidateException.class, () -> pet.setName("")); //Checks for empty
        assertThrows(ValidateException.class, () -> pet.setName(null));//Checks for null
        String name = String.format("%-150s", " "); //Fills a string with 150 characters
        assertThrows(ValidateException.class, () -> pet.setName(name));//Checks for to long
        pet.setName("Spot"); //populate with a valid value
        assertEquals("Spot", pet.getName()); // Check Name for correct value
        
    }

    /**
     * Tests the setHealth method.
     * @throws ValidateException 
     */
    @Test
    void testSetAge() throws ValidateException {
        assertThrows(ValidateException.class, () -> pet.setAge(0)); //Checks for 0
        assertThrows(ValidateException.class, () -> pet.setAge(100)); //Checks for above 20
        pet.setAge(5); // populates with a correct value
        assertEquals(5, pet.getAge()); // Check health within bounds
    }


    /**
     * Tests the setHealth method.
     */
    @Test
    void testSetHealth() {
        pet.setHealth(50);
        assertEquals(50, pet.getHealth()); // Check health within bounds

        pet.setHealth(150);
        assertEquals(100, pet.getHealth()); // Check health upper bound

        pet.setHealth(-10);
        assertEquals(0, pet.getHealth()); // Check health lower bound
        assertTrue(pet.isDead()); // Check death status
        assertEquals(DeathBy.Disease, pet.getDied()); // Check death reason
    }

    /**
     * Tests the setHappiness method.
     */
    @Test
    void testSetHappiness() {
        pet.setHappiness(50);
        assertEquals(50, pet.getHappiness()); // Check happiness within bounds

        pet.setHappiness(150);
        assertEquals(100, pet.getHappiness()); // Check happiness upper bound

        pet.setHappiness(-10);
        assertEquals(0, pet.getHappiness()); // Check happiness lower bound
        assertTrue(pet.isDead()); // Check death status
        assertEquals(DeathBy.Loneliness, pet.getDied()); // Check death reason
    }

    /**
     * Tests the setHungery method.
     */
    @Test
    void testSetThirst() {
        pet.setThirst(50);
        assertEquals(50, pet.getThirst()); // Check hunger within bounds

        pet.setThirst(150);
        assertEquals(100, pet.getThirst()); // Check hunger upper bound
        assertTrue(pet.isDead()); // Check death status
        assertEquals(DeathBy.Thirst, pet.getDied()); // Check death reason

        pet.setThirst(-10);
        assertEquals(0, pet.getThirst()); // Check hunger lower bound
    }

    /**
     * Tests the setHungery method.
     */
    @Test
    void testSetHungery() {
        pet.setHungery(50);
        assertEquals(50, pet.getHungery()); // Check hunger within bounds

        pet.setHungery(150);
        assertEquals(100, pet.getHungery()); // Check hunger upper bound
        assertTrue(pet.isDead()); // Check death status
        assertEquals(DeathBy.Starvation, pet.getDied()); // Check death reason

        pet.setHungery(-10);
        assertEquals(0, pet.getHungery()); // Check hunger lower bound
    }

/**
     * Tests the Feed method.
     */
    @Test
    void testWater() {
        pet.water();
        assertEquals(0, pet.getThirst()); // Check decreased thirst
        assertEquals(5, pet.getHealth()); // Check increased health
        assertEquals(15, pet.getHappiness()); // Check increased happiness
    }

    /**
     * Tests the Feed method.
     */
    @Test
    void testFeed() {
        pet.feed();
        assertEquals(0, pet.getHungery()); // Check decreased hunger
        assertEquals(5, pet.getHealth()); // Check increased health
        assertEquals(15, pet.getHappiness()); // Check increased happiness
        assertEquals(5, pet.getThirst()); // Check increased health
    }

    /**
     * Tests the Play method.
     */
    @Test
    void testPlay() {
        pet.play();
        assertEquals(15, pet.getHungery()); // Check increased hunger
        assertEquals(10, pet.getHealth()); // Check increased health
        assertEquals(20, pet.getHappiness()); // Check increased happiness
        assertEquals(15, pet.getThirst()); // Check increased health
    }

    /**
     * Tests the Heal method.
     */
    @Test
    void testHeal() {
        pet.heal();
        assertEquals(15, pet.getHungery()); // Check increased hunger
        assertEquals(10, pet.getHealth()); // Check increased health
        assertEquals(20, pet.getHappiness()); // Check increased happiness
        assertEquals(10, pet.getThirst()); // Check increased health
    }

    /**
     * Tests the Speak method.
     */
    @Test
    void testSpeak() {
        assertEquals("Test pet speaks!", pet.speak()); // Check pet speech
    }

    /**
     * Tests the PassageOfTime method.
     */
    @Test
    void testPassageOfTime() {
        pet.setHungery(40);
        pet.setHappiness(40);
        pet.setHealth(40);
        pet.setThirst(40);

        pet.passageOfTime();

        assertEquals(48, pet.getHungery()); // Check hunger after time passage
        assertEquals(20, pet.getHappiness()); // Check happiness after time passage
        assertEquals(36, pet.getHealth()); // Check health after time passage
        assertEquals(48, pet.getThirst()); // Check increased health

    }

    /**
     * Tests the CheckHealth method.
     */
    @Test
    void testCheckHealth() {
        pet.setHealth(10);
        assertEquals("Buddy is not healthy at 10%", pet.checkHealth()); // Check health status

        pet.setHealth(50);
        assertEquals(pet.toString(), pet.checkHealth()); // Check healthy pet
    }

    /**
     * Tests the CheckHappiness method.
     */
    @Test
    void testCheckHappiness() {
        pet.setHappiness(10);
        assertEquals("Buddy is not happy at 10%", pet.checkHappiness()); // Check happiness status

        pet.setHappiness(50);
        assertEquals(pet.toString(), pet.checkHappiness()); // Check happy pet
    }
}
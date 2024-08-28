package wcci.org.virtualpet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wcci.org.virtualpet.Enums.PetType;
import wcci.org.virtualpet.Models.CatModel;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CatModel class.
 */
class CatModelTest {
    private CatModel cat;

    /**
     * Sets up a new CatModel instance before each test.
     */
    @BeforeEach
    void setUp() {
        cat = new CatModel("Whiskers", 2);
    }

    /**
     * Tests the initial values of the cat.
     */
    @Test
    void testInitialValues() {
        assertEquals("Whiskers", cat.getName()); // Check initial name
        assertEquals(PetType.CAT, cat.getType()); // Check initial type
        assertEquals(2, cat.getAge()); // Check initial age
        assertEquals(0, cat.getHealth()); // Check initial health
        assertEquals(0, cat.getHappiness()); // Check initial happiness
        assertEquals(0, cat.getHungery()); // Check initial hunger
        assertFalse(cat.isDead()); // Check initial death status
    }

    /**
     * Tests the Speak method for a dead cat.
     */
    @Test
    void testSpeakWhenDead() {
        cat.setHealth(0); // Set health to 0 to simulate death
        assertEquals("I am Dead", cat.speak()); // Check speech for dead cat
    }

    /**
     * Tests the Speak method for a cat with low happiness.
     */
    @Test
    void testSpeakWhenUnhappy() {
        cat.setHappiness(15); // Set happiness to a low value
        assertEquals("Grrrrr", cat.speak()); // Check speech for unhappy cat
    }

    /**
     * Tests the Speak method for a happy cat.
     */
    @Test
    void testSpeakWhenHappy() {
        cat.setHappiness(50); // Set happiness to a high value
        assertEquals("Pur", cat.speak()); // Check speech for happy cat
    }

    /**
     * Tests the Feed method and its effect on the cat's state.
     */
    @Test
    void testFeed() {
        cat.feed();
        assertEquals(0, cat.getHungery()); // Check decreased hunger
        assertEquals(5, cat.getHealth()); // Check increased health
        assertEquals(15, cat.getHappiness()); // Check increased happiness
    }

    /**
     * Tests the Play method and its effect on the cat's state.
     */
    @Test
    void testPlay() {
        cat.play();
        assertEquals(15, cat.getHungery()); // Check increased hunger
        assertEquals(10, cat.getHealth()); // Check increased health
        assertEquals(20, cat.getHappiness()); // Check increased happiness
    }

    /**
     * Tests the Heal method and its effect on the cat's state.
     */
    @Test
    void testHeal() {
        cat.heal();
        assertEquals(15, cat.getHungery()); // Check increased hunger
        assertEquals(10, cat.getHealth()); // Check increased health
        assertEquals(20, cat.getHappiness()); // Check increased happiness
    }

    /**
     * Tests the PassageOfTime method and its effect on the cat's state.
     */
    @Test
    void testPassageOfTime() {
        cat.setHungery(40);
        cat.setHappiness(40);
        cat.setHealth(40);

        cat.passageOfTime();

        assertEquals(48, cat.getHungery()); // Check hunger after time passage
        assertEquals(20, cat.getHappiness()); // Check happiness after time passage
        assertEquals(36, cat.getHealth()); // Check health after time passage
    }

    /**
     * Tests the CheckHealth method for a cat with low health.
     */
    @Test
    void testCheckHealthWhenUnhealthy() {
        cat.setHealth(10);
        assertEquals("Whiskers is not healthy at 10%", cat.checkHealth()); // Check health status
    }

    /**
     * Tests the CheckHealth method for a healthy cat.
     */
    @Test
    void testCheckHealthWhenHealthy() {
        cat.setHealth(50);
        assertEquals(cat.toString(), cat.checkHealth()); // Check health status
    }

    /**
     * Tests the CheckHappiness method for a cat with low happiness.
     */
    @Test
    void testCheckHappinessWhenUnhappy() {
        cat.setHappiness(10);
        assertEquals("Whiskers is not happy at 10%", cat.checkHappiness()); // Check happiness status
    }

    /**
     * Tests the CheckHappiness method for a happy cat.
     */
    @Test
    void testCheckHappinessWhenHappy() {
        cat.setHappiness(50);
        assertEquals(cat.toString(), cat.checkHappiness()); // Check happiness status
    }
}


package wcci.org.virtualpet.BOService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import wcci.org.virtualpet.Models.*;
import wcci.org.virtualpet.Repositories.*;

import java.util.Random;

@Component
public class Populator implements CommandLineRunner {

    private final Random random = new Random();
    private final int min = 2;
    private final int max = 15;

    @Resource
    private final ScheduledTask task;

    @Resource
    private CatRepository cats;

    @Resource
    private DogRepository dogs;

    @Resource
    private RoboticCatRepository roboCats;

    @Resource
    private RoboticDogRepository roboDogs;

    public Populator(CatRepository cats, DogRepository dogs, RoboticCatRepository roboCats,
            RoboticDogRepository roboDogs, ScheduledTask task) {
        this.cats = cats;
        this.dogs = dogs;
        this.roboCats = roboCats;
        this.roboDogs = roboDogs;
        this.task = task;

    }

    @Override
    public void run(String... args) throws Exception {

        CatModel cat = new CatModel("Fuzzy", getRandomAge());
        cats.save(cat);
        task.put(cat);
        cat = new CatModel("Zake", getRandomAge());
        cats.save(cat);
        task.put(cat);
        cat = new CatModel("Smokey", getRandomAge());
        cats.save(cat);
        task.put(cat);
        cat = new CatModel("Molly", getRandomAge());
        cats.save(cat);
        task.put(cat);
        RoboticCatModel roboCat = new RoboticCatModel("Moon", getRandomAge());
        roboCats.save(roboCat);
        task.put(roboCat);
        roboCat = new RoboticCatModel("Daisy", getRandomAge());
        roboCats.save(roboCat);
        task.put(roboCat);
        roboCat = new RoboticCatModel("Perfect", getRandomAge());
        roboCats.save(roboCat);
        task.put(roboCat);
        DogModel dog = new DogModel("Spot", getRandomAge());
        dogs.save(dog);
        task.put(dog);
        dog = new DogModel("Rocky", getRandomAge());
        dogs.save(dog);
        task.put(dog);
        dog = new DogModel("Poppy", getRandomAge());
        dogs.save(dog);
        task.put(dog);
        dog = new DogModel("Stella", getRandomAge());
        dogs.save(dog);
        task.put(dog);
        RoboticDogModel roboDog = new RoboticDogModel("Rex", getRandomAge());
        roboDogs.save(roboDog);
        task.put(roboDog);
        roboDog = new RoboticDogModel("Iris", getRandomAge());
        roboDogs.save(roboDog);
        task.put(roboDog);
        roboDog = new RoboticDogModel("Ranger", getRandomAge());
        roboDogs.save(roboDog);
        task.put(roboDog);
    }

    private int getRandomAge() {
        return random.nextInt(max - min) + min;
    }
}

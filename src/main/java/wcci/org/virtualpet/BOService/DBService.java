package wcci.org.virtualpet.BOService;

import java.util.*;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import wcci.org.virtualpet.Enums.PetType;
import wcci.org.virtualpet.Interfaces.PetInterface;
import wcci.org.virtualpet.Models.*;
import wcci.org.virtualpet.Repositories.*;

@Service
class DBService {
    @Resource
    private CatRepository cats;

    @Resource
    private DogRepository dogs;

    @Resource
    private RoboticCatRepository roboCats;

    @Resource
    private RoboticDogRepository roboDogs;

    public DBService(CatRepository cats, DogRepository dogs, RoboticCatRepository roboCats,
            RoboticDogRepository roboDogs, ScheduledTask task) {
        this.cats = cats;
        this.dogs = dogs;
        this.roboCats = roboCats;
        this.roboDogs = roboDogs;
    }

    public Map<Long, PetInterface> getAll() {
        HashMap<Long, PetInterface> map = new HashMap<>();
        for (PetType petType : PetType.values()) {
            switch (petType) {
                case DOG: {
                    List<DogModel> dogRepo = dogs.findAll();
                    for (DogModel dog : dogRepo) {
                        map.put(dog.getId(), dog);
                    }
                    break;
                }
                case CAT: {
                    List<CatModel> catRepo = cats.findAll();
                    for (CatModel cat : catRepo) {
                        map.put(cat.getId(), cat);
                    }
                    break;
                }
                case ROBOTIC_DOG: {
                    List<RoboticDogModel> dogRepo = roboDogs.findAll();
                    for (RoboticDogModel dog : dogRepo) {
                        map.put(dog.getId(), dog);
                    }
                    break;
                }

                case ROBOTIC_CAT: {
                    List<RoboticCatModel> catRepo = roboCats.findAll();
                    for (RoboticCatModel cat : catRepo) {
                        map.put(cat.getId(), cat);
                    }
                    break;
                }

                default:
                    break;
            }
        }
        return map;
    }

    public PetInterface getById(long id) {
        PetInterface result = null;

        Optional<CatModel> optCat = cats.findById(id);
        if (!optCat.isEmpty()) {
            result = optCat.get();
        }
        if (result == null) {
            Optional<DogModel> optDog = dogs.findById(id);
            if (!optDog.isEmpty()) {
                result = optDog.get();
            }
        }
        if (result == null) {
            Optional<RoboticDogModel> optRoboDog = roboDogs.findById(id);
            if (!optRoboDog.isEmpty()) {
                result = optRoboDog.get();
            }
        }
        if (result == null) {
            Optional<RoboticCatModel> optRoboCat = roboCats.findById(id);
            if (!optRoboCat.isEmpty()) {
                result = optRoboCat.get();
            }
        }
        return result;
    }

    public Map<Long, PetInterface> getByName(String name) {
        Map<Long, PetInterface> result = new HashMap<Long, PetInterface>();

        List<CatModel> listCat = cats.findByName(name);
        if (listCat != null) {
            for (CatModel cat : listCat) {
                result.put(cat.getId(), cat);
            }
        }

        List<DogModel> listDog = dogs.findByName(name);
        if (listDog != null) {
            for (DogModel dog : listDog) {
                result.put(dog.getId(), dog);
            }
        }
        List<RoboticCatModel> listRoboCat = roboCats.findByName(name);
        if (listRoboCat != null) {
            for (RoboticCatModel cat : listRoboCat) {
                result.put(cat.getId(), cat);
            }
        }
        List<RoboticDogModel> listRoboDog = roboDogs.findByName(name);
        if (listRoboDog != null) {
            for (RoboticDogModel dog : listRoboDog) {
                result.put(dog.getId(), dog);
            }
        }
        return result;
    }

    public Map<Long, PetInterface> getByPetType(PetType petType) {
        Map<Long, PetInterface> result = new HashMap<Long, PetInterface>();
        switch (petType) {
            case CAT: {
                List<CatModel> catRepo = cats.findAll();
                for (CatModel cat : catRepo) {
                    result.put(cat.getId(), cat);
                }
                break;

            }
            case DOG: {
                List<DogModel> dogRepo = dogs.findAll();
                for (DogModel dog : dogRepo) {
                    result.put(dog.getId(), dog);
                }
                break;
            }
            case ROBOTIC_CAT: {
                List<RoboticCatModel> catRepo = roboCats.findAll();
                for (RoboticCatModel cat : catRepo) {
                    result.put(cat.getId(), cat);
                }
                break;
            }
            case ROBOTIC_DOG: {
                List<RoboticDogModel> dogRepo = roboDogs.findAll();
                for (RoboticDogModel dog : dogRepo) {
                    result.put(dog.getId(), dog);
                }
                break;
            }
        }
        return result;
    }

    public void UpdatePet(PetInterface pet) {
        PetType petType = pet.getType();
        switch (petType) {
            case CAT: {
                cats.save((CatModel) pet);
                break;
            }
            case DOG: {
                dogs.save((DogModel) pet);
                break;
            }
            case ROBOTIC_CAT: {
               roboCats.save((RoboticCatModel)pet);
                break;
            }
            case ROBOTIC_DOG: {
                roboDogs.save((RoboticDogModel)pet);
                break;
            }
        }
    }

    public void RemovePet(Long id) {
        PetInterface pet = getById(id);
        PetType petType = pet.getType();
        switch (petType) {
            case CAT: {
                cats.delete((CatModel) pet);
                break;
            }
            case DOG: {
                dogs.delete((DogModel) pet);
                break;
            }
            case ROBOTIC_CAT: {
               roboCats.delete((RoboticCatModel)pet);
                break;
            }
            case ROBOTIC_DOG: {
                roboDogs.delete((RoboticDogModel)pet);
                break;
            }
        }

    }

}

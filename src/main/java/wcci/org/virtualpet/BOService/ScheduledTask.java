package wcci.org.virtualpet.BOService;

import java.util.*;
import java.util.concurrent.locks.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import wcci.org.virtualpet.Enums.PetType;
import wcci.org.virtualpet.Interfaces.PetInterface;

@Component
public class ScheduledTask {
    private static Map<Long, PetInterface> map = new HashMap<>();
    private static final Lock lock = new ReentrantLock(); // lock object

    @Resource
    private DBService service;

    public ScheduledTask(DBService service) {
        this.service = service;
    }

    /**
     * Task runs with a delay of 5 seconds after the previous execution completes
     */
    @Scheduled(fixedDelay = 5000)
    public void runTaskWithFixedDelay() {
        System.out.println("Passage of time " + map.size() + " pets to check ");
        lock.lock(); // Acquire the lock
        ArrayList<Long> toDelete = new ArrayList<>();
        try {
            for (long x = map.size(); x > 0; x--) {
                PetInterface current = map.get(x);
                System.out.println(current.toString());
                current.passageOfTime();
                if (current.isDead()) {
                    System.out.println(current);
                    map.remove(current.getId());
                }
            }

        } finally {
            lock.unlock();
        }

    }

    public Collection<PetInterface> getMap() {
        Map<Long, PetInterface> list = new HashMap<Long, PetInterface>();
        lock.lock(); // Acquire the lock
        try {
            for (Map.Entry<Long, PetInterface> entry : list.entrySet()) {
                list.put(entry.getKey(), entry.getValue());
            }

        } finally {
            lock.unlock();
        }
        return list.values();
    }

    public Collection<PetInterface> getByPetType(PetType petType) {
        Map<Long, PetInterface> list = new HashMap<Long, PetInterface>();
        lock.lock(); // Acquire the lock
        try {
            for (Map.Entry<Long, PetInterface> entry : list.entrySet()) {
                if (entry.getValue().getType().equals(petType)) {
                    list.put(entry.getKey(), entry.getValue());
                }
            }
        } finally {
            lock.unlock();
        }
        return list.values();
    }

    public Collection<PetInterface> getByName(String name) {
        Map<Long, PetInterface> list = new HashMap<Long, PetInterface>();
        lock.lock(); // Acquire the lock
        try {
            for (Map.Entry<Long, PetInterface> entry : list.entrySet()) {
                if (entry.getValue().getName().contains(name)) {
                    list.put(entry.getKey(), entry.getValue());
                }
            }
        } finally {
            lock.unlock();
        }
        return list.values();
    }

    public PetInterface getById(Long id) {
        PetInterface item = null;
        lock.lock(); // Acquire the lock
        try {
            for (Map.Entry<Long, PetInterface> entry : map.entrySet()) {
                if (entry.getValue().getId() == id) {
                    item = entry.getValue();
                }
            }
        } finally {
            lock.unlock();
        }
        return item;
    }

    public void removeById(Long id) {
        lock.lock(); // Acquire the lock
        try {
            for (Map.Entry<Long, PetInterface> entry : map.entrySet()) {
                if (entry.getValue().getId() == id) {
                    map.remove(id);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public PetInterface put(PetInterface item) {

        lock.lock(); // Acquire the lock
        try {
            map.put(item.getId(), item);
        } finally {
            lock.unlock();
        }
        return item;
    }
}

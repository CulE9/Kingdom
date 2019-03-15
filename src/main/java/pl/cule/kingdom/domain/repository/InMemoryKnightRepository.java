package pl.cule.kingdom.domain.repository;

import org.springframework.scheduling.annotation.Scheduled;
import pl.cule.kingdom.domain.Knight;
import pl.cule.kingdom.utils.Ids;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;

public class InMemoryKnightRepository implements KnightRepository {

    private Map<Integer, Knight> knights = new HashMap<>();

    private Random rand = new Random();

    public InMemoryKnightRepository() {
    }

    @Override
    public void createKnight(String name, int age) {
        Knight newKnight = new Knight(name, age);
        newKnight.setId(Ids.generateNewId(knights.keySet()));
        knights.put(newKnight.getId(), newKnight);
    }

    @Override
    public Collection<Knight> getAllKnights() {
        return knights.values();
    }

    @Override
    public Optional<Knight> getKnightByName(String name) {
        Optional<Knight> knightByName = knights.values().stream().filter(knight -> knight.getName().equals(name)).findAny();
        return knightByName;
    }

    @Override
    public void deleteKnight(Integer id) {
        knights.remove(id);
    }

    @Override
    public void createKnight(Knight knight) {
        knight.setId(Ids.generateNewId(knights.keySet()));
        knights.put(knight.getId(), knight);
    }

    @Override
    public Knight getKnightById(Integer id) {
        return knights.get(id);
    }

    @Override
    public String toString() {
        return "InMemoryKnightRepository{" +
                "knights=" + knights +
                '}';
    }

    @Override
    public void updateKnight(int id, Knight knight) {
        knights.put(id, knight);
    }

    @Override
    @Transactional
    @Scheduled(fixedDelayString = "${questCreationDelay}") //, initialDelay = 3000) - po jakim czasie od uruchomienia
    public void createRandomKnight() {
        List<Knight> knights = new ArrayList<>();

        knights.add(new Knight("Malagant", 35));
        knights.add(new Knight("Lancelot", 25));
        knights.add(new Knight("Dagonet", 44));
        knights.add(new Knight("Lamorak", 22));
        knights.add(new Knight("Tristan", 19));
        knights.add(new Knight("Girflet", 29));
        knights.add(new Knight("Pelleas", 41));

        Knight knight = knights.get(rand.nextInt(knights.size()));
        createKnight(knight);
    }
}

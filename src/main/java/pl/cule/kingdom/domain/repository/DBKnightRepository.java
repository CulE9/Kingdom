package pl.cule.kingdom.domain.repository;

import org.springframework.scheduling.annotation.Scheduled;
import pl.cule.kingdom.domain.Knight;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

public class DBKnightRepository implements KnightRepository {

    @PersistenceContext
    private EntityManager em;

    private Random rand = new Random();

    @Override
    @Transactional
    public void createKnight(String name, int age) {
        Knight knight = new Knight(name, age);
        em.persist(knight);
    }

    @Override
    public Collection<Knight> getAllKnights() {
        return em.createQuery("from Knight", Knight.class).getResultList();
    }

    @Override
    public Optional<Knight> getKnightByName(String name) {
        Knight knightByName = em.createQuery("FROM Knight k WHERE k.name=:name", Knight.class)
                .setParameter("name", name).getSingleResult();
        return Optional.ofNullable(knightByName);
    }

    @Override
    @Transactional
    public void deleteKnight(Integer id) {
        em.remove(id);
    }

    @Override
    @Transactional
    public void createKnight(Knight knight) {
        em.persist(knight);
    }

    @Override
    public Knight getKnightById(Integer id) {
        return em.find(Knight.class, id);
    }

    @Override
    @Transactional
    public void updateKnight(int id, Knight knight) {
        em.merge(knight);
    }

    @Override
    @Transactional
//    @Scheduled(fixedDelayString = "${knightCreationDelay}") //, initialDelay = 3000) - po jakim czasie od uruchomienia
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

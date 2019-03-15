package pl.cule.kingdom.domain.repository;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import pl.cule.kingdom.domain.Quest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class QuestRepository {

    @PersistenceContext
    private EntityManager em;

    private Random rand = new Random();

//    Map<Integer, Quest> quests = new HashMap<>();

    @Transactional
    public void createQuest(String description) {
//        int newId = Ids.generateNewId(quests.keySet());
//        Quest newQuest = new Quest(newId, description);
//        quests.put(newId, newQuest);
        Quest newQuest = new Quest(description);
        em.persist(newQuest);
    }

    public List<Quest> getAll() {
//        return new ArrayList<>(quests.values());
        return em.createQuery("from Quest", Quest.class).getResultList();
    }

    @Transactional
    public void deleteQuest(Quest quest) {
//        quests.remove(quest.getId());
        em.remove(quest);
    }

    @Transactional
//    @Scheduled(fixedDelayString = "${questCreationDelay}") //, initialDelay = 3000) - po jakim czasie od uruchomienia
    public void createRandomQuest() {
        List<String> descriptions = new ArrayList<>();

        descriptions.add("Uratuj księżniczkę");
        descriptions.add("Weź udział w turnieju");
        descriptions.add("Zabij bandę goblinów");
        descriptions.add("Zabij smoka");
        descriptions.add("Wygraj turniej");
        descriptions.add("Uwolnij króla");

        String description = descriptions.get(rand.nextInt(descriptions.size()));
        createQuest(description);
    }

    @Transactional
    public void update(Quest quest) {
//        quests.put(quest.getId(), quest);
        em.merge(quest);
    }

    public Quest getQuest(Integer id) {
//        return quests.get(id);
        return em.find(Quest.class, id);
    }
}

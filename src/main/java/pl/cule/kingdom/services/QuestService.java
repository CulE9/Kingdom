package pl.cule.kingdom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cule.kingdom.domain.Quest;
import pl.cule.kingdom.domain.repository.KnightRepository;
import pl.cule.kingdom.domain.repository.QuestRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestService {

    private final KnightRepository knightRepository;
    private final QuestRepository questRepository;

    private final static Random rand = new Random();

    @Autowired
    public QuestService(KnightRepository knightRepository, QuestRepository questRepository) {
        this.knightRepository = knightRepository;
        this.questRepository = questRepository;
    }

    public void assignRandomQuestByName(String knightName) {
        List<Quest> allQuests = questRepository.getAll();
        Quest randomQuest = allQuests.get(rand.nextInt(allQuests.size()));
        knightRepository.getKnightByName(knightName).ifPresent(knight -> knight.setQuest(randomQuest));
//        questRepository.deleteQuest(randomQuest);
    }

    public void assignRandomQuestById(int id) {
        List<Quest> allQuests = questRepository.getAll();
        Quest randomQuest = allQuests.get(rand.nextInt(allQuests.size()));
        knightRepository.getKnightById(id).setQuest(randomQuest);
//        questRepository.deleteQuest(randomQuest);
    }

    public List<Quest> getAllNotStartedQuests() {
        return questRepository.getAll().stream().filter(quest -> !quest.isStarted()).collect(Collectors.toList());
    }

    public void update(Quest quest) {
        questRepository.update(quest);
    }

    public boolean isQuestCompleted(Quest quest) {
        return quest.isCompleted();
    }
}

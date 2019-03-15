package pl.cule.kingdom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.cule.kingdom.domain.Knight;
import pl.cule.kingdom.domain.PlayerInformation;
import pl.cule.kingdom.domain.repository.KnightRepository;
import pl.cule.kingdom.domain.repository.PlayerInformationRepository;
import pl.cule.kingdom.domain.repository.QuestRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class KnightService {

    private final KnightRepository knightRepository;
    private final QuestRepository questRepository;
    private final PlayerInformationRepository playerInformationRepository;

    @Autowired
    public KnightService(KnightRepository knightRepository, QuestRepository questRepository, PlayerInformationRepository playerInformationRepository) {
        this.knightRepository = knightRepository;
        this.questRepository = questRepository;
        this.playerInformationRepository = playerInformationRepository;
    }

    public List<Knight> getAllKnights() {
        return new ArrayList<>(knightRepository.getAllKnights());
    }

    public void saveKnight(Knight knight) {
        knightRepository.createKnight(knight);
    }

    public Knight getKnight(Integer id) {
        return knightRepository.getKnightById(id);
    }

    public void deleteKnight(Integer id) {
        knightRepository.deleteKnight(id);
    }

    public void updateKnight(Knight knight) {
        knightRepository.updateKnight(knight.getId(), knight);
    }

    @Transactional
    public void getMyGold() {
        List<Knight> allKnights = getAllKnights();
        allKnights.forEach(knight -> {
                    if (knight.getQuest() != null) {
                        boolean completed = knight.getQuest().isCompleted();
                        if (completed) {
                            questRepository.update(knight.getQuest());
                        }
                    }
                }
        );
        PlayerInformation first = playerInformationRepository.getFirst();
        int currentGold = first.getGold();
        first.setGold(currentGold + collectRewards());
    }

    private int collectRewards() {
        Predicate<Knight> knightPredicate = knight -> {
            if (knight.getQuest() != null) {
                return knight.getQuest().isCompleted();
            } else {
                return false;
            }
        };
        int sum = knightRepository.getAllKnights().stream()
                .filter(knightPredicate)
                .mapToInt(knight -> knight.getQuest().getReward())
                .sum();
        knightRepository.getAllKnights().stream()
                .filter(knightPredicate).forEach(knight -> {
            knight.setQuest(null);
        });
        return sum;
    }
}

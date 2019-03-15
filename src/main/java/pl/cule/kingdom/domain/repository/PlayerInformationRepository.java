package pl.cule.kingdom.domain.repository;

import org.springframework.stereotype.Repository;
import pl.cule.kingdom.domain.PlayerInformation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class PlayerInformationRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void createPlayerInformation(PlayerInformation playerInformation) {
//        PlayerInformation pi = new PlayerInformation();
//        em.persist(pi);
        em.persist(playerInformation);
    }

    public PlayerInformation getFirst() {
        return em.createQuery("from PlayerInformation", PlayerInformation.class).getResultList().get(0);
    }
}

package pl.cule.kingdom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.cule.kingdom.domain.PlayerInformation;
import pl.cule.kingdom.domain.repository.KnightRepository;
import pl.cule.kingdom.domain.repository.PlayerInformationRepository;
import pl.cule.kingdom.domain.repository.QuestRepository;
import pl.cule.kingdom.services.QuestService;
import pl.cule.kingdom.utils.Role;
import pl.cule.kingdom.utils.RoleRepository;

import javax.transaction.Transactional;

@Component
@Scope("singleton") //domyślnie
public class Starter implements CommandLineRunner {

    private final KnightRepository knightRepository;
    private final QuestRepository questRepository;
    private final QuestService questService;
    private final PlayerInformationRepository playerInformationRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public Starter(KnightRepository knightRepository, QuestRepository questRepository, QuestService questService, PlayerInformationRepository playerInformationRepository, RoleRepository roleRepository) {
        this.knightRepository = knightRepository;
        this.questRepository = questRepository;
        this.questService = questService;
        this.playerInformationRepository = playerInformationRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public void run(String... args) {

        for (int i = 0; i < 5; i++) {
            questRepository.createRandomQuest();
            knightRepository.createRandomKnight();
        }

        knightRepository.createKnight("Percival", 32);
        questService.assignRandomQuestByName("Percival");

        addUsersAndRoles();

//        System.out.println(roleRepository.getAll());
    }

    private void addUsersAndRoles() {
        PlayerInformation playerUser = new PlayerInformation("user1", passwordEncoder().encode("user1"));
        playerInformationRepository.createPlayerInformation(playerUser);
        PlayerInformation playerAdmin = new PlayerInformation("admin", passwordEncoder().encode("admin"));
        playerInformationRepository.createPlayerInformation(playerAdmin);

        Role user1RoleUSER = new Role("user1", "USER");
        Role adminRoleUSER = new Role("admin", "USER");
        Role adminRoleADMIN = new Role("admin", "ADMIN");

        roleRepository.persistRole(user1RoleUSER);
        roleRepository.persistRole(adminRoleUSER);
        roleRepository.persistRole(adminRoleADMIN);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

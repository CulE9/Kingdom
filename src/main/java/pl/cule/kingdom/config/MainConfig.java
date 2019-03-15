package pl.cule.kingdom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.cule.kingdom.domain.repository.DBKnightRepository;
import pl.cule.kingdom.domain.repository.InMemoryKnightRepository;
import pl.cule.kingdom.domain.repository.KnightRepository;

@Configuration
//@ImportResource("classpath:config/castle-config.xml")
//@PropertySource("classpath:knightRepository.properties")
public class MainConfig {

    @Bean(name = "inMemoryKnightRepository")
    @Profile("dev")
    public KnightRepository createInMemoryRepo() {
        KnightRepository repo = new InMemoryKnightRepository();
        System.err.println("----- Profile <dev> activated -----");
        return repo;
    }

    @Bean(name = "DBKnightRepository")
    @Profile("prod")
    public KnightRepository createDBRepo() {
        KnightRepository repo = new DBKnightRepository();
        System.err.println("----- Profile <prod> activated -----");
        return repo;
    }

//    @Bean
//    public Quest createQuest() {
//        return new Quest();
//    }

//    @Autowired
//    Quest quest;
//
//    @Bean(name = "lancelot")
//    @Primary
////    @Scope("prototype")
//    public Knight lancelot() {
//        Knight lancelot = new Knight("Lancelot", 29);
//        lancelot.setQuest(quest);
//        return lancelot;
//    }
//
//    @Bean(name = "percival")
//    public Knight createKnightBean() {
//        Knight percival = new Knight("Percival", 25);
//        percival.setQuest(quest);
//        return percival;
//    }

//    @Bean(name = "zamek", initMethod = "build", destroyMethod = "tearDown")
//    @Value("${my.castle.name:East Watch}")
//    public Castle castle(String name) {
//        Castle castle = new Castle(knight());
//        castle.setName(name);
//        return castle;
//    }
}

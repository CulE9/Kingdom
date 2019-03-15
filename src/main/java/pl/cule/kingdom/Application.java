package pl.cule.kingdom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
//@PropertySource("classpath:knightRepository.properties") // ścieżka własnych properties
//@ComponentScan({"pl.cule.kingdom","pl.cule.component"})
//@ComponentScan(basePackageClasses = {Starter.class, Castle.class,
//        Quest.class, Knight.class})
//@ImportResource("classpath:config/kingdom-config.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
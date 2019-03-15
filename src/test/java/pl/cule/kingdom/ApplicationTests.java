package pl.cule.kingdom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.cule.kingdom.domain.repository.InMemoryKnightRepository;
import pl.cule.kingdom.domain.Knight;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    Knight knight;

    @Autowired
    InMemoryKnightRepository castle;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCastle() {
        String except = "Znajduje się tu zamek o nazwie Castle Black. Zamieszkały przez rycerza Rycerz o imieniu Lancelot(29). Zadanie: Uratuj księżniczkę.";
        assertEquals(except, castle.toString());
    }

}

package pl.cule.kingdom.domain;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class KnightTest {

    @Test
    public void testIfQuestMarkedAsStarted() {
        Knight knight = new Knight("Percival", 25);
        Quest quest = new Quest(1, "Testowe zadanie");
        knight.setQuest(quest);

        assertTrue(knight.getQuest().isStarted());
    }
}

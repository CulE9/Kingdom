package pl.cule.kingdom.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestTest {

    @Test
    public void settingStartedFlagToFalseShouldSetStartDate() {
        Quest quest = new Quest(1, "tesowe zadanie");
        quest.setStarted(true);
        assertNotNull(quest.startDate);
    }

    @Test
    public void questShouldBeCompleted() {
        Quest quest = new Quest(1, "tesowe zadanie");
        quest.setStarted(true);
        quest.lenghtInSeconds = -60;
        assertTrue(quest.isCompleted());
        assertTrue(quest.isCompleted());
    }

    @Test
    public void questShouldBeNotCompleted() {
        Quest quest = new Quest(1, "tesowe zadanie");
        quest.setStarted(true);
        quest.lenghtInSeconds = 200;
        assertFalse(quest.isCompleted());
        assertFalse(quest.isCompleted());
    }
}

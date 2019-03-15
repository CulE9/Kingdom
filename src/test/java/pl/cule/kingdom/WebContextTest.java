package pl.cule.kingdom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.cule.kingdom.controllers.QuestController;
import pl.cule.kingdom.domain.PlayerInformation;
import pl.cule.kingdom.services.KnightService;
import pl.cule.kingdom.services.QuestService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class WebContextTest {

    @Mock
    KnightService knightService;
    @Mock
    QuestService questService;
    @Mock
    PlayerInformation playerInformation;
    @InjectMocks
    QuestController questController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(questController).build();
    }

    @Test
    public void testCheckQuest() throws Exception {
        mockMvc.perform(get("/checkQuest"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/knights"));
    }

    @Test
    public void contextLoads() {
    }
}

package pl.cule.kingdom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.cule.kingdom.components.TimeComponent;
import pl.cule.kingdom.domain.Knight;
import pl.cule.kingdom.domain.PlayerInformation;
import pl.cule.kingdom.domain.repository.KnightRepository;
import pl.cule.kingdom.domain.repository.PlayerInformationRepository;
import pl.cule.kingdom.services.KnightService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class KnightController {

    private final TimeComponent timeComponent;
    private final PlayerInformationRepository playerInformationRepository;
    private final KnightService service;
    private final KnightRepository knightRepository;

    @Autowired
    public KnightController(TimeComponent timeComponent, PlayerInformationRepository playerInformationRepository, KnightRepository knightRepository, KnightService service) {
        this.timeComponent = timeComponent;
        this.playerInformationRepository = playerInformationRepository;
        this.knightRepository = knightRepository;
        this.service = service;
    }

    @RequestMapping("/knights")
    public String getKnights(Model model) {
        List<Knight> allKnights = service.getAllKnights();
        PlayerInformation playerInformation = playerInformationRepository.getFirst();
        model.addAttribute("knights", allKnights);
        model.addAttribute("timecomponent", timeComponent);
        model.addAttribute("playerinformation", playerInformation);
        model.addAttribute("knightRepository", knightRepository);

        return "knights";
    }

    @RequestMapping("/knight")
    public String getKnight(@RequestParam("id") Integer id, Model model) {
        Knight knight = service.getKnight(id);
        PlayerInformation playerInformation = playerInformationRepository.getFirst();
        model.addAttribute("knight", knight);
        model.addAttribute("timecomponent", timeComponent);
        model.addAttribute("playerinformation", playerInformation);
        return "knight";
    }

    @RequestMapping("/newknight")
    public String createKnight(Model model) {
        PlayerInformation playerInformation = playerInformationRepository.getFirst();
        model.addAttribute("knight", new Knight());
        model.addAttribute("timecomponent", timeComponent);
        model.addAttribute("playerinformation", playerInformation);
        return "knightform";
    }

    @RequestMapping(value = "/knights", method = RequestMethod.POST)
    public String saveKnight(@Valid Knight knight, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.err.println("Wystąpiły błędy w formularzu:");
            bindingResult.getAllErrors().forEach(error -> {
                System.err.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "knightform";
        } else {
            service.saveKnight(knight);
            return "redirect:/knights";
        }
    }

    @RequestMapping(value = "/knight/delete/{id}")
    public String deleteKnight(@PathVariable("id") int id) {
        service.deleteKnight(id);
        return "redirect:/knights";
    }
}

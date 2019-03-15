package pl.cule.kingdom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;
import pl.cule.kingdom.domain.Quest;
import pl.cule.kingdom.domain.repository.QuestRepository;

import java.text.ParseException;
import java.util.Locale;

@Service
public class QuestFormatter implements Formatter<Quest> {

    @Autowired
    QuestRepository repository;

    @Override
    public Quest parse(String idAsStr, Locale locale) throws ParseException {
        Integer id = Integer.parseInt(idAsStr);
        return repository.getQuest(id);
    }

    @Override
    public String print(Quest object, Locale locale) {
        return object.toString();
    }
}

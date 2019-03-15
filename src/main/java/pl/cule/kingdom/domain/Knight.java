package pl.cule.kingdom.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Knight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Pole imię nie może być puste")
    @Size(min = 2, max = 40, message = "Imię rycerza musi być między 2, a 40 znaków")
    private String name;

    @NotNull
    @Range(min = 18, max = 60, message = "Rycerz musi mieć co najmniej 18 lat, a nie więcej niż 60")
    private int age;
    private int level;

    //    @Transient //lub
//    @Embedded //lub
    @OneToOne
    private Quest quest;

    public Knight() {
        this.level = 1;
    }

    public Knight(String name, int age) {
        this.name = name;
        this.age = age;
        this.level = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knight knight = (Knight) o;
        return age == knight.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        if (quest != null) {
            quest.setStarted(true);
        }
        this.quest = quest;
    }

    @Override
    public String toString() {
        return "Rycerz o imieniu " + name + "(" + age + "). Zadanie: " + quest + ".";
    }
}

package questgame.domain;

import java.util.Objects;
import java.util.UUID;

public class Quest extends Entity<UUID> {
    String name, game;
    String question;
    String wrong1, wrong2, correct;

    public Quest(String name, String question, String wrong1, String wrong2, String correct, String game) {
        this.name = name;
        this.question = question;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
        this.correct = correct;
        this.game = game;
        this.setId(UUID.randomUUID());
    }
    public Quest(UUID id, String name, String question, String wrong1, String wrong2, String correct, String game) {
        this.name = name;
        this.question = question;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
        this.correct = correct;
        this.game = game;
        this.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getWrong1() {
        return wrong1;
    }

    public void setWrong1(String wrong1) {
        this.wrong1 = wrong1;
    }

    public String getWrong2() {
        return wrong2;
    }

    public void setWrong2(String wrong2) {
        this.wrong2 = wrong2;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quest quest = (Quest) o;
        return Objects.equals(name, quest.name) && Objects.equals(game, quest.game) && Objects.equals(question, quest.question) && Objects.equals(wrong1, quest.wrong1) && Objects.equals(wrong2, quest.wrong2) && Objects.equals(correct, quest.correct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, game, question, wrong1, wrong2, correct);
    }
}

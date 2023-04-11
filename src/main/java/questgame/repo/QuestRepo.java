package questgame.repo;

import questgame.domain.Quest;
import questgame.domain.User;

import java.util.ArrayList;
import java.util.UUID;

public interface QuestRepo extends Repository<UUID, Quest>{
    void delete(UUID id);
    ArrayList<Quest> findByGame(String game);
}

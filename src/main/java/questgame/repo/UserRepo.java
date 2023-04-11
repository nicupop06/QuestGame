package questgame.repo;

import questgame.domain.User;

import java.util.UUID;

public interface UserRepo extends Repository<UUID, User>{
    User findOneByUsername(String username);
    void updateup(User user);
    void updatedown(User user);
}


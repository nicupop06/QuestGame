package questgame.service;

import questgame.domain.Quest;
import questgame.domain.User;
import questgame.repo.QuestRepo;
import questgame.repo.UserRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Service {
    private UserRepo userRepo;
    private QuestRepo questRepo;

    public Service(UserRepo userRepo, QuestRepo questRepo){
        this.userRepo = userRepo;
        this.questRepo = questRepo;
    }

    public User findOne(UUID id){
        return userRepo.findOne(id);
    }

    public User findOneByUsername(String username){
        return userRepo.findOneByUsername(username);
    }

    public ArrayList<User> findAll(){
        return new ArrayList<>( (Collection<User>) userRepo.findAll());
    }

    public void save(User user){
        userRepo.save(user);
    }

    public ArrayList<Quest> findAllQuests(){
        return new ArrayList<>( (Collection<Quest>) questRepo.findAll());
    }

    public void save(Quest quest){
        questRepo.save(quest);
    }

    public Quest findOneQuest(UUID id){
        return questRepo.findOne(id);
    }

    public void delete(UUID id) { questRepo.delete(id);}

    public ArrayList<Quest> findByGame(String game) {
        return new ArrayList<>((Collection<Quest>) questRepo.findByGame(game));
    }

    public void updateup(User user){
        userRepo.updateup(user);
    }

    public void updatedown(User user){
        userRepo.updatedown(user);
    }


}

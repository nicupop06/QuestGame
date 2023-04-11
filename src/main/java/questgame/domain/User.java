package questgame.domain;

import java.util.Objects;
import java.util.UUID;

public class User extends Entity<UUID>{
    String username;
    String password;
    int tokens;

    public User(String username, String password){
        this.setId(UUID.randomUUID());
        this.username = username;
        this.password = password;
        this.tokens = 200;
    }

    public User(UUID id, String username, String password, int tokens){
        this.setId(id);
        this.username = username;
        this.password = password;
        this.tokens = tokens;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return tokens == user.tokens && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, tokens);
    }
}

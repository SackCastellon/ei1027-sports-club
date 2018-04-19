package es.uji.ei1027.sportsclub.dao;

import es.uji.ei1027.sportsclub.model.UserDetails;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@SuppressWarnings("DesignForExtension")
public class FakeUserProvider implements IUserDao {

    private final @NotNull Map<String, UserDetails> knownUsers = new HashMap<>();

    public FakeUserProvider() {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        UserDetails userAlice = new UserDetails();
        userAlice.setUsername("alice");
        userAlice.setPassword(passwordEncryptor.encryptPassword("alice"));
        knownUsers.put("alice", userAlice);

        UserDetails userBob = new UserDetails();
        userBob.setUsername("bob");
        userBob.setPassword(passwordEncryptor.encryptPassword("bob"));
        knownUsers.put("bob", userBob);
    }

    @Override
    public @NotNull Optional<UserDetails> loadUserByUsername(@NotNull String username, @NotNull String password) {
        return Optional.ofNullable(knownUsers.get(username.trim())).stream().filter((user) -> new BasicPasswordEncryptor().checkPassword(password, user.getPassword())).findAny();
    }

    @Override
    public @NotNull Collection<UserDetails> listAllUsers() {
        return knownUsers.values();
    }
}

package es.uji.ei1027.sportsclub.dao;

import es.uji.ei1027.sportsclub.model.UserDetails;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

public interface IUserDao {

    @NotNull Optional<UserDetails> loadUserByUsername(@NotNull String username, @NotNull String password);

    @NotNull Collection<UserDetails> listAllUsers();
}

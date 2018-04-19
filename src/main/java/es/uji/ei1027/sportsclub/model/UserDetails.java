package es.uji.ei1027.sportsclub.model;

import org.jetbrains.annotations.NotNull;

public final class UserDetails {

    @NotNull String username = "";
    @NotNull String password = "";

    public @NotNull String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }
}

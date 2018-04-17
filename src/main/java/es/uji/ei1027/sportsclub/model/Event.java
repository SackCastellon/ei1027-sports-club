package es.uji.ei1027.sportsclub.model;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class Event {

    private @NotNull String name = "";
    private @NotNull String description = "";
    private @NotNull String type = "";

    private final @NotNull Set<Standing> standings = new HashSet<>(16);

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    public @NotNull String getType() {
        return type;
    }

    public void setType(@NotNull String type) {
        this.type = type;
    }

    public @NotNull Set<Standing> getStandings() {
        return Collections.unmodifiableSet(standings);
    }

    @Override
    public @NotNull String toString() {
        return String.format("Event{name='%s', description='%s', type='%s', standings=%s}", name, description, type, standings);
    }
}

package es.uji.ei1027.sportsclub.model;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class Swimmer {

    private @NotNull String fedId = "";
    private @NotNull String name = "";
    private @NotNull String country = "";
    private int age;
    private @NotNull String sex = "";

    private final @NotNull Set<Standing> standings = new HashSet<>(16);

    public @NotNull String getFedId() {
        return fedId;
    }

    public void setFedId(@NotNull String fedId) {
        this.fedId = fedId;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getCountry() {
        return country;
    }

    public void setCountry(@NotNull String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public @NotNull String getSex() {
        return sex;
    }

    public void setSex(@NotNull String sex) {
        this.sex = sex;
    }

    public @NotNull Set<Standing> getStandings() {
        return Collections.unmodifiableSet(standings);
    }

    public @NotNull Category getCategory() {
        return Category.getCategory(age);
    }

    @Override
    public @NotNull String toString() {
        return String.format("Swimmer{fedId='%s', name='%s', country='%s', age=%d, sex='%s', standings=%s}", fedId, name, country, age, sex, standings);
    }
}

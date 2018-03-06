package es.uji.ei1027.sportsclub.model;

import org.jetbrains.annotations.NotNull;

import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Category {
    MIDGET(0, 12),
    YOUTH(12, 14),
    INTERMEDIATE(14, 16),
    YOUNG(16, 18),
    SENIOR(18, Integer.MAX_VALUE);

    private final int min;
    private final int max;

    /**
     * @param min The minimum age (inclusive) of this category
     * @param max The maximum age (exclusive) of this category
     */
    Category(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static @NotNull Category getCategory(int age) {
        if (age < 0) throw new IllegalArgumentException("Age must be positive! Found: " + age);

        return Arrays.stream(values())
                .filter(it -> (age >= it.min) && (age < it.max)).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Cannot find any Category for age " + age));
    }

    @Override
    public @NotNull String toString() {
        return String.format("Category{min=%d, max=%d}", min, max);
    }

    private void writeObject(ObjectOutputStream out) throws NotSerializableException {
        throw new NotSerializableException("es.uji.ei1027.sportsclub.model.Category");
    }
}

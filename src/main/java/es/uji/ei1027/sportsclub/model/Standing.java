package es.uji.ei1027.sportsclub.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Time;

public final class Standing {

    private @NotNull String swimmerName = "";
    private @NotNull String eventName = "";
    private int position;
    private @Nullable Time finishTime;

    public @NotNull String getSwimmerName() {
        return swimmerName;
    }

    public void setSwimmerName(@NotNull String swimmerName) {
        this.swimmerName = swimmerName;
    }

    public @NotNull String getEventName() {
        return eventName;
    }

    public void setEventName(@NotNull String eventName) {
        this.eventName = eventName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public @Nullable Time getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(@Nullable Time finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public @NotNull String toString() {
        return String.format("Standing{swimmerName='%s', eventName='%s', position=%d, finishTime=%s}", swimmerName, eventName, position, finishTime);
    }
}

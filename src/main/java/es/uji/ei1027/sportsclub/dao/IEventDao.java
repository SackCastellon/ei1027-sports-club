package es.uji.ei1027.sportsclub.dao;

import es.uji.ei1027.sportsclub.model.Event;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IEventDao {

    @NotNull List<Event> getEvents();
}

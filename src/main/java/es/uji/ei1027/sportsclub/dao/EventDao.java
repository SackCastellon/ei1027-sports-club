package es.uji.ei1027.sportsclub.dao;

import es.uji.ei1027.sportsclub.model.Event;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EventDao implements IEventDao {

    private JdbcTemplate template;

    @Autowired
    public final void setDataSource(@Qualifier("dataSource") @NotNull DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public @NotNull List<Event> getEvents() {
        return template.query("SELECT * FROM event;", new EventMapper());
    }


    private static final class EventMapper implements RowMapper<Event> {

        public @NotNull Event mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
            Event event = new Event();
            event.setName(rs.getString("name"));
            event.setDescription(rs.getString("description"));
            event.setType(rs.getString("type"));
            return event;
        }
    }

    @Override
    public final @NotNull String toString() {
        return String.format("EventDao{template=%s}", template);
    }
}

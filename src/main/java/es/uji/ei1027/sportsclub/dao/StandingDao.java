package es.uji.ei1027.sportsclub.dao;

import es.uji.ei1027.sportsclub.model.Standing;
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
@SuppressWarnings("DesignForExtension")
public class StandingDao implements IStandingDao {

    private JdbcTemplate template;

    @Autowired
    public void setDataSource(@Qualifier("dataSource") @NotNull DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public @NotNull List<Standing> getStandings() {
        return template.query("SELECT * FROM standing;", new StandingMapper());
    }

    @Override
    public @NotNull List<Standing> getEventStandings(@NotNull String eventName) {
        return template.query("SELECT * FROM standing WHERE event_name = ?;", new StandingMapper(), eventName);
    }

    @Override
    public @NotNull List<Standing> getCountryStandings(@NotNull String country) {
        return template.query("SELECT st.* FROM standing st JOIN swimmer sw ON st.swimmer_name = sw.name WHERE sw.country = ?;", new StandingMapper(), country);
    }

    private static final class StandingMapper implements RowMapper<Standing> {

        public @NotNull Standing mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
            Standing standing = new Standing();
            standing.setSwimmerName(rs.getString("swimmer_name"));
            standing.setEventName(rs.getString("event_name"));
            standing.setPosition(rs.getInt("position"));
            standing.setFinishTime(rs.getTime("finish_time"));
            return standing;
        }
    }

    @Override
    public @NotNull String toString() {
        return String.format("StandingDao{template=%s}", template);
    }
}

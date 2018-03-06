package es.uji.ei1027.sportsclub.dao;

import es.uji.ei1027.sportsclub.model.Swimmer;
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
public class SwimmerDao implements ISwimmerDao {

    private JdbcTemplate template;

    @Autowired
    public final void setDataSource(@Qualifier("dataSource") @NotNull DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public @NotNull List<Swimmer> getSwimmers() {
        return template.query("SELECT * FROM swimmer;", new SwimmerMapper());
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public @NotNull Swimmer getSwimmer(@NotNull String name) {
        return template.queryForObject("SELECT * FROM swimmer WHERE name = ?;", new SwimmerMapper(), name);
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public void addSwimmer(@NotNull Swimmer swimmer) {
        template.update("INSERT INTO swimmer(name, fed_id, country, age, sex) VALUES(?,?,?,?,?)",
                swimmer.getName(), swimmer.getFedId(), swimmer.getCountry(), swimmer.getAge(), swimmer.getSex());
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public void updateSwimmer(@NotNull Swimmer swimmer) {
        template.update("UPDATE swimmer SET fed_id = ?, country = ?, age = ?, sex = ? WHERE name = ?",
                swimmer.getFedId(), swimmer.getCountry(), swimmer.getAge(), swimmer.getSex(), swimmer.getName());
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public void deleteSwimmer(@NotNull String name) {
        template.update("DELETE FROM swimmer WHERE name = ?", name);
    }

    private static final class SwimmerMapper implements RowMapper<Swimmer> {

        public @NotNull Swimmer mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
            Swimmer swimmer = new Swimmer();
            swimmer.setName(rs.getString("name"));
            swimmer.setFedId(rs.getString("fed_id"));
            swimmer.setCountry(rs.getString("country"));
            swimmer.setAge(rs.getInt("age"));
            swimmer.setSex(rs.getString("sex"));
            return swimmer;
        }
    }

    @Override
    public final @NotNull String toString() {
        return String.format("SwimmerDao{template=%s}", template);
    }
}

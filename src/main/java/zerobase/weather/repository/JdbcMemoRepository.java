package zerobase.weather.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zerobase.weather.domain.Memo;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMemoRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Memo save(Memo memo) {
        String sql = "insert into memo values(?, ?)";
        jdbcTemplate.update(sql,memo.getId(), memo.getText());
        return memo;
    }

    private RowMapper<Memo> memoRowMapper() {
        return (rs, rowNum) -> new Memo(
                rs.getInt("id"),
                rs.getString("text"));
    }

    public List<Memo> findAll() {
        String sql = "select * from memo";
        return jdbcTemplate.query(sql, memoRowMapper());
        // sql 실행 후 반환된 객체는 resultset 형태 -> memoRowMapper 이용하여 Memo 객체로 가져옴
    }

    public Optional<Memo> findById(int id) {
        String sql = "select * from memo where id = ?";
        return jdbcTemplate.query(sql, memoRowMapper(), id).stream().findFirst();
        // 혹시 일치하는 id가 없을 경우, optional 객체로 맵핑하여 혹시 모를 null 값을 쉽게 처리하기 위해
    }

}

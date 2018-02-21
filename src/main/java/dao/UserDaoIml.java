package dao;

import entity.User;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoIml implements UserDao{


    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoIml(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM public.\"User\"";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public void save(User user) {
        String sql = "INSERT INTO public.\"User\" (name, email, age) VALUES(?,?,?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getAge());
    }

    public User getById(int id) {
        String sql = "SELECT * FROM public.\"User\" WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM public.\"User\" WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public void update(User user) {
        String sql = "UPDATE public.\"User\" SET name=?, email=?, age=? WHERE id=?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getAge(), user.getId());
    }
}

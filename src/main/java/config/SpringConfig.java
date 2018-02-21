package config;

import dao.UserDao;
import dao.UserDaoIml;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import service.UserService;
import service.UserServiceImpl;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Bean
    JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/springtryone");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123");
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean
    UserDao getUserDao(){
        return new UserDaoIml(getJdbcTemplate());
    }

    @Bean
    UserService getUserService(){
        return new UserServiceImpl();
    }

}

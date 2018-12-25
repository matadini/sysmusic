package pl.matadini.sysmusic.server.application.configuration;

import lombok.Data;
import lombok.ToString;
import pl.matadini.sysmusic.server.common.CommonConst;

@Data
@ToString
public class Configuration {
    private String jdbcDriver;
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;
    private String hibernateDialect;


    public static Configuration empty() {
        Configuration configuration = new Configuration();
        configuration.setJdbcUrl(CommonConst.EMPTY_STRING);
        configuration.setJdbcUser(CommonConst.EMPTY_STRING);
        configuration.setJdbcDriver(CommonConst.EMPTY_STRING);
        configuration.setJdbcPassword(CommonConst.EMPTY_STRING);
        configuration.setHibernateDialect(CommonConst.EMPTY_STRING);
        return configuration;
    }

}

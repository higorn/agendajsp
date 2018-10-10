package br.com.ftec.higor.agendaweb.util;

import br.com.ftec.higor.agendaweb.jdbc.JdbcDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @author higor
 */
public class JdbcUtil {
  private static final ResourceBundle dbProps       = ResourceBundle.getBundle(System.getProperty("jdbc.props", "jdbc"));
  private static final String         PROP_DBDRIVER = "dbdriver";
  private static final String         PROP_DBURL    = "dburl";
  private static final String         PROP_DBUSER   = "dbuser";
  private static final String         PROP_DBPASS   = "dbpass";
  private static final JdbcDataSource dataSource;

  static {
    try {
      Class.forName(getDbDriver());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    dataSource = new JdbcDataSource(getDbUrl(), getDbUser(), getDbPass());

    if (getDbDriver().equals("org.hsqldb.jdbcDriver")) {
      try {
        exec("data/hsqldb.sql");
      } catch (IOException e) {
        e.printStackTrace();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static JdbcDataSource getDataSource() {
    return dataSource;
  }

  public static void exec(String sqlFile) throws IOException, SQLException {
    ClassLoader classLoader = JdbcUtil.class.getClassLoader(); 
    File file = new File(classLoader.getResource(sqlFile).getFile());
//    String sql = new String(Files.readAllBytes(Paths.get(sqlFile)));
    String sql = new String(Files.readAllBytes(file.toPath()));
    try (Connection c = dataSource.getConnection()) {
      Statement st = c.createStatement();
      st.execute(sql);
    }
  }

  public static String getDbDriver() {
    return dbProps.getString(PROP_DBDRIVER);
  }

  public static String getDbUrl() {
    return dbProps.getString(PROP_DBURL);
  }

  public static String getDbUser() {
    return dbProps.getString(PROP_DBUSER);
  }

  public static String getDbPass() {
    return dbProps.getString(PROP_DBPASS);
  }
}

package se360.shortlinker.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se360.shortlinker.restserver.dao.LinksDAO;
import se360.shortlinker.restserver.dao.UserDAO;
import se360.shortlinker.restserver.util.DBUtil;

@SpringBootApplication
public class RestserverApplication {
	public static final UserDAO userDAO = new UserDAO();
	public static final LinksDAO linksDAO = new LinksDAO();

	public static void main(String[] args) {
		DBUtil.createTables();
		DBUtil.createDummyData();
		SpringApplication.run(RestserverApplication.class, args);
	}

}

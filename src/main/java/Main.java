import com.solvd.navigator.dao.impl.DistanceDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Navigator initial app");
        DistanceDAOImpl distanceDAO = new DistanceDAOImpl();
        LOGGER.info(distanceDAO.getAllDistancesByCityId(22));
    }
}
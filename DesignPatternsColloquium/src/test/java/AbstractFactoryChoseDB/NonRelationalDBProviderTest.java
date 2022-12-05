package AbstractFactoryChoseDB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonRelationalDBProviderTest {

    @Test
    void chooseDistinctDB() {
        DB db = ChooseTypeOfDB.chooseDBType("NonRelational");
        DistinctDB distinctDB = db.chooseDistinctDB("OracleBerkley");
        assertNotNull(distinctDB);
    }
}
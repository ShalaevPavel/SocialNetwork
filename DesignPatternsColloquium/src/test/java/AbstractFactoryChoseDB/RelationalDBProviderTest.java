package AbstractFactoryChoseDB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationalDBProviderTest {

    @Test
    void chooseDistinctDB() {
        DB db = ChooseTypeOfDB.chooseDBType("Relational");
        DistinctDB distinctDB = db.chooseDistinctDB("Oracle");
        assertNotNull(distinctDB);
    }
}
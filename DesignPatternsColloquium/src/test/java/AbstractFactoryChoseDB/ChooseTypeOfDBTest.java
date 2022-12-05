package AbstractFactoryChoseDB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChooseTypeOfDBTest {

    @Test
    void chooseDBType() {
        DB db = ChooseTypeOfDB.chooseDBType("Relational");


        assertNotNull(db);
    }
}
package AbstractFactoryChoseDB;

import static org.junit.jupiter.api.Assertions.*;

class MongoDBTest {

    @org.junit.jupiter.api.Test
    void add() {
        MongoDB mongoDB = new MongoDB();
        assertEquals("smth Added to MongoDB", mongoDB.add("smth "));
    }
}
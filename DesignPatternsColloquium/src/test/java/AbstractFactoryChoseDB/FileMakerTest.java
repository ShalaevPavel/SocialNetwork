package AbstractFactoryChoseDB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileMakerTest {

    @Test
    void add() {
        DistinctDB fileMaker = new FileMaker();
        String expected = "Yo Added to FileMaker";
        String actual = fileMaker.add("Yo ");
        assertEquals(expected, actual);
    }
}
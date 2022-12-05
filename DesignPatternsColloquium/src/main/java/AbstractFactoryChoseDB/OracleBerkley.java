package AbstractFactoryChoseDB;

public class OracleBerkley implements DistinctDB {
    @Override
    public void delete() {
        System.out.println("Deleted from OracleBerkley");
    }

    @Override
    public void read() {
        System.out.println("Read from OracleBerkley");

    }

    @Override
    public String add(String value) {
        return value + "Added to OracleBerkley";

    }
}

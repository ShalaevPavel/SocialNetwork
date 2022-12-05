package AbstractFactoryChoseDB;

public class MySQL implements DistinctDB {
    @Override
    public void delete() {
        System.out.println("Deleted from MySQL");
    }

    @Override
    public void read() {
        System.out.println("Read from MySQL");

    }

    @Override
    public String add(String value) {
        return value + "Added to MySQL";

    }
}

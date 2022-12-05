package AbstractFactoryChoseDB;

public class PostgreSQL implements DistinctDB {
    @Override
    public void delete() {
        System.out.println("Deleted from PostgreSQL");
    }

    @Override
    public void read() {
        System.out.println("Read from PostgreSQL");

    }

    @Override
    public String add(String value) {
        return value + "Added to PostgreSQL";

    }
}

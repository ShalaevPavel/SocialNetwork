package AbstractFactoryChoseDB;

public class OracleDB implements DistinctDB {
    @Override
    public void delete() {
        System.out.println("Deleted from OracleDB");
    }

    @Override
    public void read() {
        System.out.println("Read from OracleDB");

    }

    @Override
    public String add(String value) {
        return value + "Added to OracleDB";

    }
}

package AbstractFactoryChoseDB;

public class FileMaker implements DistinctDB {

    @Override
    public void delete() {
        System.out.println("Deleted from FileMaker");
    }

    @Override
    public void read() {
        System.out.println("Read from FileMaker");
    }

    @Override
    public String add(String value) {
        return value + "Added to FileMaker";

    }
}

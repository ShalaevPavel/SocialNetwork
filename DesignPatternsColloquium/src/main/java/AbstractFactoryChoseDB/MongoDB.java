package AbstractFactoryChoseDB;

public class MongoDB implements DistinctDB{
    @Override
    public void delete() {
        System.out.println("Deleted from MongoDB");
    }

    @Override
    public void read() {
        System.out.println("Read from MongoDB");

    }

    @Override
    public String add(String value) {
        return value + "Added to MongoDB";
    }
}

package AbstractFactoryChoseDB;

public class NonRelationalDBProvider extends DB {
    @Override
    public DistinctDB chooseDistinctDB(String DBName) {
        if (DBName.equals("MongoDB")){
            return new MongoDB();
        }
        else if (DBName.equals("Oracle Berkley DB")) {
            return new OracleBerkley();
        }
        else {
            return new FileMaker();
        }
    }
}

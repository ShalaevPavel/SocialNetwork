package AbstractFactoryChoseDB;

public class RelationalDBProvider extends DB {


    @Override
    public DistinctDB chooseDistinctDB(String DBName) {
        if (DBName.equals("MySQL")) {
            return new MySQL();
        } else if (DBName.equals("PostgreSQL")) {
            return new PostgreSQL();
        } else {
            return new OracleDB();
        }
    }
}

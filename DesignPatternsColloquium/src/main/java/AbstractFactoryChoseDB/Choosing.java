package AbstractFactoryChoseDB;

public class Choosing {
    public static void main(String[] args) {
        DB dbType = ChooseTypeOfDB.chooseDBType("Relational");
        DistinctDB db = dbType.chooseDistinctDB("MySQL");
        System.out.println(db.add("New note "));
    }
}

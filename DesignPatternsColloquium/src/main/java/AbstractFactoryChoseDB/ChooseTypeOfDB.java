package AbstractFactoryChoseDB;

public class ChooseTypeOfDB {
    public static DB chooseDBType(String type){
        if (type.equals("Relational")){
            return new RelationalDBProvider();
        }
        else {
            return new NonRelationalDBProvider();
        }
    }
}

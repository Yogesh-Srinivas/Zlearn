public class Database {
    private Database(){}
    private static Database instance = null;
    public static Database getInstance(){
        if(instance!=null){
            instance= new Database();
        }
        return instance;
    }
}

class Authenticator{

}

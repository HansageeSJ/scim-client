package objectClasses;

/**
 * Created by hansagee on 5/12/17.
 */
public class User {
    final String userName;
    final Name name;
    String id;


  public User(String userName, Name name) {

        this.userName = userName;
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public Name getName() {

        return name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

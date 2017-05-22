package objectClasses;

/**
 * Created by hansagee on 5/15/17.
 */
public class UserGroups {
    private String displayName;
    private String id;


    public UserGroups(String displayName)
    {
        this.displayName=displayName;
    }
    public String getDisplayName() {
        return displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


}

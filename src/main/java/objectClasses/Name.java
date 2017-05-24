package objectClasses;

/**
 * 
 */
public class Name {
    String familyName;
    String givenName;

    public Name(String familyName, String givenName){

        this.familyName = familyName;
        this.givenName = givenName;

    }

    public String getFamilyName() {
        return familyName;
    }

    public String setFamilyName(String familyName) {
        this.familyName = familyName;
        return familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
}

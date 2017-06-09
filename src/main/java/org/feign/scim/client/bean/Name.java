package org.feign.scim.client.bean;

/**
 *
 */
public class Name {
    String familyName;
    String givenName;

    /**
     *
     * @param familyName
     * @param givenName
     */
    public Name(String familyName, String givenName){

        this.familyName = familyName;
        this.givenName = givenName;
    }

    /**
     *
     * @return
     */

    public String getFamilyName() {
        return familyName;
    }

    /**
     *
     * @param familyName
     * @return
     */

    public String setFamilyName(String familyName) {
        this.familyName = familyName;
        return familyName;
    }

    /**
     *
     * @return
     */

    public String getGivenName() {
        return givenName;
    }

    /**
     *
     * @param givenName
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
}

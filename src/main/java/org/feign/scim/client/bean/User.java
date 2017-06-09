package org.feign.scim.client.bean;

/**
 * This is the user object class.
 */
public class User {
    private String userName;
    private Name name;
    private String id;
    Email[] email;

    /**
     *
     * @param userName
     * @param name
     */

    public User(String userName, Name name) {

        this.userName = userName;
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    public Name getName() {

        return name;
    }

    /**
     *
     * @param id
     */

    public void setId(String id){
       this.id = id;
    }
    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

}

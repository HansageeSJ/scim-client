package org.feign.scim.client.bean;

/**
 * This is the Groups object class.
 */
public class Groups {
    private String displayName;
    private String id;

    /**
     *
     * @param displayName
     */

    public Groups(String displayName)
    {
        this.displayName=displayName;
    }

    /**
     *
     * @return
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @param id
     */

    public void setId(String id) {
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


package org.feign.scim.client.endpoint;

import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.feign.scim.client.bean.Groups;
import org.feign.scim.client.exception.SCIMClientException;

import static org.feign.scim.client.util.SCIMClientConstants.AUTHORIZATION;
import static org.feign.scim.client.util.SCIMClientConstants.ENDPOINT_URL;

/**
 * This class is used to handle SCIM Groups.
 *Feign is used as a http client binder.
 */
public class GroupResource {
    private static final Log log = LogFactory.getLog(GroupResource.class);
    @Headers("Content-Type: application/scim+json")



    public interface GroupResourceClient {
        @Headers(AUTHORIZATION)
        @RequestLine("POST /scim/v2/Groups/")
        Groups createGroups(Groups content);

        @Headers(AUTHORIZATION)
        @RequestLine("GET /scim/v2/Groups/{id}")
        Groups getGroupsById(@Param("id") String id);

        @Headers(AUTHORIZATION)
        @RequestLine("PUT /scim/v2/Groups/{Id}")
        void updateGroups(@Param("Id") String Id, Groups displayName);

        @Headers(AUTHORIZATION)
        @RequestLine("DELETE /scim/v2/Groups/{Id}")
        void deleteGroups(@Param("Id") String Id);
    }

    /**
     *
     * @param displayName
     * @return
     * @throws SCIMClientException
     */
    public Groups createGroups(String displayName) throws SCIMClientException{
        if (displayName == null) {
            throw new SCIMClientException("Enter valid displayname!");
        }
        GroupResourceClient client = Feign.builder().encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(GroupResourceClient.class,ENDPOINT_URL);
        Groups groups = client.createGroups(new Groups(displayName));
        log.info("The group details are " + groups.getDisplayName());
        log.info("The group Id is:"+groups.getId());
        return groups;
    }

    /**
     *
     * @param Id
     * @return
     * @throws SCIMClientException
     */
    public Groups getGroupsById(String Id) throws SCIMClientException{
        if (Id == null || Id.isEmpty()) {
            throw new SCIMClientException("Enter valid id!");
        }
        GroupResourceClient client = Feign.builder().decoder(new GsonDecoder())
                .target(GroupResourceClient.class, ENDPOINT_URL);
        Groups groups = client.getGroupsById(Id);
        log.info("Requested group details are " + groups.getDisplayName());
        return groups;
    }

    /**
     *
     * @param Id
     * @param displayName
     * @return
     * @throws SCIMClientException
     */
    public Groups updateGroup(String Id, String displayName) throws SCIMClientException{
        if (Id == null || Id.isEmpty()) {
            throw new SCIMClientException("Couldn't find the user!");
        }
        GroupResourceClient client = Feign.builder().encoder(new GsonEncoder())
                .target(GroupResourceClient.class,ENDPOINT_URL);
        Groups groups = new Groups(displayName);
        client.updateGroups(Id,groups);
        log.info("The updated group details are " + groups.getDisplayName());
        return groups;
    }

    /**
     *
     * @param Id
     * @throws SCIMClientException
     */
    public void deleteGroups(String Id) throws SCIMClientException {
        if (Id == null || Id.isEmpty()) {
            throw new SCIMClientException("Couldn't find the user!");
        }
        GroupResourceClient client = Feign.builder().decoder(new GsonDecoder())
                .target(GroupResourceClient.class, ENDPOINT_URL);
        client.deleteGroups(Id);
        log.info("Group is deleted successfuly");
    }

}

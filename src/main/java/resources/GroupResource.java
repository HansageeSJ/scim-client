package resources;

import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import objectClasses.UserGroups;

import static util.SCIMClientConstants.AUTHORIZATION;
import static util.SCIMClientConstants.ENDPOINT_URL;

/**
 *This class is used to handle SCIM Groups using java object.
 *Feign is used as a http client binder.
 */
public class GroupResource {

    @Headers("Content-Type: application/scim+json")

    public interface GroupResourceClient {
        @Headers(AUTHORIZATION)
        @RequestLine("POST /scim/v2/Groups/")
        UserGroups createGroups(UserGroups content);

        @Headers(AUTHORIZATION)
        @RequestLine("GET /scim/v2/Groups/{id}")
        UserGroups getGroupsById(@Param("id") String id);

        @Headers(AUTHORIZATION)
        @RequestLine("PUT /scim/v2/Groups/{Id}")
        void updateGroups(@Param("Id") String Id, UserGroups displayName);

        @Headers(AUTHORIZATION)
        @RequestLine("DELETE /scim/v2/Groups/{Id}")
        void deleteGroups(@Param("Id") String Id);
    }

    GroupResourceClient client;

    public void createGroups(String displayName){
        client = Feign.builder().encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(GroupResourceClient.class,ENDPOINT_URL);
        UserGroups userGroups = client.createGroups(new UserGroups(displayName));
        System.out.println(userGroups.getId());
        System.out.println("Successfully created the Group");

    }

    public void getGroupsById(String Id){
        client = Feign.builder().decoder(new GsonDecoder())
                .target(GroupResourceClient.class, ENDPOINT_URL);
        UserGroups userGroups =  client.getGroupsById(Id);
        System.out.println(userGroups.getDisplayName());
    }

    public void updateGroup(String id, String displayName){
        UserGroups userGroups = new UserGroups(displayName);
        client = Feign.builder().encoder(new GsonEncoder())
                .target(GroupResourceClient.class,ENDPOINT_URL);
        client.updateGroups(id,userGroups);
        System.out.println(userGroups.getDisplayName());
    }

    public void deleteGroups(String Id){
        client.deleteGroups(Id);
        System.out.println("Successfully Deleted");
    }
}

package resources;

import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import objectClasses.Name;
import objectClasses.User;

import static util.SCIMClientConstants.AUTHORIZATION;
import static util.SCIMClientConstants.ENDPOINT_URL;

/**
 *This class is used to handle SCIM Users using java object.
 *Feign is used as a http client binder.
 */
public class UserResource {

    @Headers("Content-Type: application/scim+json")


    public interface UserResourceClient {

        @Headers(AUTHORIZATION)
        @RequestLine("POST /scim/v2/Users/")
        User createUser(User UserData);

        @Headers(AUTHORIZATION)
        @RequestLine("GET /scim/v2/Users/{Id}")
        User getUserById(@Param("Id") String Id);

        @Headers(AUTHORIZATION)
        @RequestLine("PUT /scim/v2/Users/{Id}")
        void updateUser(@Param("Id") String Id, User Details);

        @Headers(AUTHORIZATION)
        @RequestLine("DELETE /scim/v2/Users/{Id}")
        void deleteUser(@Param("Id") String Id);
    }
    UserResourceClient client;

    public void createUser(String userName, String familyName, String givenName){
        client = Feign.builder().encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(UserResourceClient.class,ENDPOINT_URL);
        User user = client.createUser(new User(userName,new Name(familyName,givenName)));
        System.out.println(user.getId());
        System.out.println("Successfully created the Users");
    }

    public void getUserById(String Id){
        client = Feign.builder().decoder(new GsonDecoder())
                .target(UserResourceClient.class,ENDPOINT_URL);
        User user = client.getUserById(Id);
        System.out.println(user.getUserName());
        System.out.println(user.getName().getFamilyName());
        System.out.println(user.getName().getGivenName());
    }

    public void updateUser(String Id, String userName, String familyName, String givenName){
        User user = new User(userName,new Name(familyName,givenName));
        client = Feign.builder().encoder(new GsonEncoder())
                .target(UserResourceClient.class,ENDPOINT_URL);
        client.updateUser(Id,user);
        System.out.println(user.getName().getFamilyName());
    }
    public void deleteUser(String Id){
        client.deleteUser(Id);
        System.out.println("Successfully Deleted");

    }

}

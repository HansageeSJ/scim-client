package org.feign.scim.client.endpoint;

import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.feign.scim.client.bean.User;
import org.feign.scim.client.exception.SCIMClientException;

import static org.feign.scim.client.util.SCIMClientConstants.AUTHORIZATION;
import static org.feign.scim.client.util.SCIMClientConstants.ENDPOINT_URL;

/**
 *This class is used to handle SCIM Users.
 *Feign is used as a http client binder.
 */
public class UserResource {

    private static final Log log = LogFactory.getLog(GroupResource.class);
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

    /**
     *
     * @param user
     * @return
     * @throws SCIMClientException
     */

    public User createUser(User user) throws SCIMClientException {
        if (user.getUserName() == null) {
            throw new SCIMClientException("Please add valid username!");
        }
        UserResourceClient client = Feign.builder().encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(UserResourceClient.class, ENDPOINT_URL);
        client.createUser(user);
        log.info("The user's id : "+" "+user.getId());
        log.info("The user's username :"+" "+user.getUserName());
        log.info("The user's familyname:"+" "+user.getName().getFamilyName());
        log.info("The user's givenname:"+" "+user.getName().getGivenName());
        return user;
    }

    /**
     *
     * @param Id
     * @return
     * @throws SCIMClientException
     */
    public User getUserById(String Id) throws SCIMClientException {
       if (Id==null || Id.isEmpty()){
           throw new SCIMClientException("Please add valid id!");
       }
        UserResourceClient client = Feign.builder().decoder(new GsonDecoder())
                .target(UserResourceClient.class, ENDPOINT_URL);
        User user = client.getUserById(Id);
        log.info("The user's username :"+" "+user.getUserName());
        log.info("The user's familyname:"+" "+user.getName().getFamilyName());
        log.info("The user's givenname:"+" "+user.getName().getGivenName());
        return user;
    }

    /**
     *
     * @param Id
     * @param user
     * @return
     * @throws SCIMClientException
     */
    public User updateUser(String Id, User user) throws SCIMClientException {
        if (Id == null || Id.isEmpty()) {
            throw new SCIMClientException("Couldn't find the user!");
        }
        UserResourceClient client = Feign.builder().encoder(new GsonEncoder())
                .target(UserResourceClient.class, ENDPOINT_URL);
        client.updateUser(Id, user);
        log.info("The user's username :"+" "+user.getUserName());
        log.info("The user's familyname:"+" "+user.getName().getFamilyName());
        log.info("The user's givenname:"+" "+user.getName().getGivenName());
        return user;
    }

    /**
     *
     * @param Id
     * @throws SCIMClientException
     */
    public void deleteUser(String Id) throws SCIMClientException {
        if (Id == null || Id.isEmpty()) {
            throw new SCIMClientException("Couldn't find the user");
        }
        UserResourceClient client = Feign.builder().decoder(new GsonDecoder())
                .target(UserResourceClient.class, ENDPOINT_URL);
        client.deleteUser(Id);
        log.info("User is deleted successfuly");
    }
}

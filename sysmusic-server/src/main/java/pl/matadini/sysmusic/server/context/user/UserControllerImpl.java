package pl.matadini.sysmusic.server.context.user;

import com.google.gson.Gson;
import lombok.Builder;
import org.eclipse.jetty.http.HttpStatus;
import pl.matadini.sysmusic.server.common.spark.SparkControllerResponse;
import spark.Request;
import spark.Response;
import spark.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;


@Builder
class UserControllerImpl implements UserController {

    Gson gson;
    EntityManagerFactory entityManagerFactory;

    @Override
    public void initialize(Service service) {
        service.path("/user", ()->{
            service.get("/all", UserControllerImpl.this::getUsers, gson::toJson);
        });
    }

    @Override
    public Object addUser(Request request, Response response) {
        return null;
    }

    @Override
    public Object removeUser(Request request, Response response) {
        return null;
    }

    @Override
    public Object getUsers(Request request, Response response) {

        try {

            UserService userService = UserServiceFactory.create(entityManagerFactory);
            List<UserDto> users = userService.getUsers();

            return SparkControllerResponse.builder()
                    .object(users)
                    .status(HttpStatus.OK_200)
                    .build();

        } catch (Exception ex) {

            return SparkControllerResponse.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR_500)
                    .message(ex.getMessage())
                    .build();
        }
    }

    @Override
    public Object changePassword(Request request, Response response) {
        return null;
    }

    @Override
    public Object authorize(Request request, Response response) {
        return null;
    }
}

package pl.matadini.sysmusic.server.context.user;

import pl.matadini.sysmusic.server.common.spark.SparkController;
import spark.Request;
import spark.Response;

interface UserController extends SparkController {

    Object addUser(Request request, Response response);

    Object removeUser(Request request, Response response);

    Object getUsers(Request request, Response response);

    Object changePassword(Request request, Response response);

    Object authorize(Request request, Response response);

}

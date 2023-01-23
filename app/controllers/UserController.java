package controllers;

import models.Status;
import models.User;
import play.data.FormFactory;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;

import java.util.List;

import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

public class UserController {

    private final FormFactory formFactory;

    @Inject
    public UserController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result userList(Http.Request request){
        List<User> pendingUsers = User.getUserByStatus(Status.Pending);
        List<User> users = User.getUserList();
//        User user = new User();
        return ok(views.html.user_list.render(pendingUsers, users, request));
    }

    public Result approve(Http.Request request, Long id){
        User user = User.getUserById(id);
        user.setStatus(Status.Approved);
        user.save();
        user.refresh();
        return redirect(routes.UserController.userList());
    }

    public Result reject(Http.Request request, Long id){
        User user = User.getUserById(id);
        user.setStatus(Status.Rejected);
        user.save();
        user.refresh();
        return redirect(routes.UserController.userList());
    }
}

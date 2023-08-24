package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.SocialMediaDAO;
import Model.Account;
import Model.Message;
import Service.SocialMediaService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    SocialMediaService socialMediaService;
    SocialMediaDAO socialMediaDAO;

    public SocialMediaController(){
        this.socialMediaService = new SocialMediaService();
        this.socialMediaDAO = new SocialMediaDAO();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::postMsgHandler);
        app.get("messages", this::getAllMsgHandler);
        app.get("/messages/{message_id}", this::getMsgByMsgIdHandler);
        app.delete("/messages/{message_id}", this::deleteMsgByMsgIdHandler);
        app.patch("/messages/{message_id}", this::patchMsgByMsgIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMsgByAccIdHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    private void registerHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = socialMediaService.registerAccount(account);
        if(addedAccount != null){
            ctx.json(mapper.writeValueAsString(addedAccount));
        }
        else{
            ctx.status(400);
        }

    }

    private void loginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account foundAccount = socialMediaService.loginAccount(account);
        if(foundAccount != null){
            ctx.json(mapper.writeValueAsString(foundAccount));
        }
        else{
            ctx.status(401);
        }
    }

    private void postMsgHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message sentMessage = socialMediaService.postMessage(message);
        if(sentMessage != null){
            ctx.json(mapper.writeValueAsString(sentMessage));
        }
        else{
            ctx.status(400);
        }
    }

    private void getAllMsgHandler(Context ctx) {
        List<Message> messages = socialMediaService.getAllMessages();
        ctx.json(messages);
    }

    private void getMsgByMsgIdHandler(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        Message targetMessage = socialMediaService.getMsgByMsgId(id);
        if(targetMessage != null){
            ctx.json(targetMessage);
        }
    }

    private void deleteMsgByMsgIdHandler(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        Message targetMessage = socialMediaService.deleteMsgByMsgId(id);
        if(targetMessage != null){
            ctx.json(targetMessage);
        }    
    }
    
    private void patchMsgByMsgIdHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message newMessage = mapper.readValue(ctx.body(), Message.class);
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        Message targetMessage = socialMediaService.patchMsgByMsgId(id, newMessage.message_text);
        if(targetMessage != null){
            ctx.json(targetMessage);
        }
        else{
            ctx.status(400);
        }
    }
    
    private void getAllMsgByAccIdHandler(Context ctx) {
        int accId = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> messages = socialMediaService.getAllMsgByAccId(accId);
        ctx.json(messages);
    }
}
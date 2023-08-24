/*package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Account;
import Service.SocialMediaService;


public class SocialMediaController {
 
    SocialMediaService socialMediaService;

    public SocialMediaController(){
        this.socialMediaService = new SocialMediaService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("example-endpoint", this::exampleHandler);
        
        app.post("localhost:8080/register", ctx -> {
            //For processing registration.
            String jsonString = ctx.body();
            ObjectMapper om = new ObjectMapper();
            Account ac = om.readValue(jsonString, Account.class);
            if(Account){
               
                ctx.status(200);
            }
            else{
                //otherwise status 400 to signify client error.
                ctx.status(400);
            }
        });
        
        app.post("localhost:8080/login", ctx -> {
            //For processing logins.
            
            //login was successful. Everything is good. Status 200.
            ctx.status(200);

            //Login unsuccessful. Status 401 to signify unathorized.
            ctx.status(401);
        });
        
        app.post("localhost:8080/messages", ctx -> {
            //For processing the creation of new messages.

            //Message creation successful.
            ctx.status(200);

            //Message creation unsuccessful, client error.
            ctx.status(400);
        });
        
        app.get("localhost:8080/messages", ctx -> {
            //Retrieves all the messages

            //Retrieval successful.
            ctx.status(200);
        });
        
        app.get("localhost:8080/messages/{message_id}", ctx -> {
            //Retrieves message by message id.

            //Always gives status 200, even if there was no message matching message id.
            ctx.status(200);
        });
        
        app.delete("localhost:8080/messages/{message_id}", ctx -> {
            //Deletes a message identified by an id.
            
            
            ctx.status(200);
        });

        app.patch("localhost:8080/messages/{message_id}", ctx -> {
            //Updates a message text identified by an id. 

            //Message update successful. Status 200.
            ctx.status(200);
            //Message unsuccessful for any reason. Status 400.
            ctx.status(400);
        });
        
        app.get("localhost:8080/accounts/{account_id}/messages", ctx -> {
            //Retrieves all messages from a user.

            //Status is always 200.
            ctx.status(200);
        });
        return app;
    }

    private void exampleHandler(Context context) {
        context.json("sample text");
    }


}*/
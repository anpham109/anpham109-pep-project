package Service;
import Model.Account;
import Model.Message;
import java.util.List;

import DAO.SocialMediaDAO;



public class SocialMediaService {
    private SocialMediaDAO socialMediaDAO;
    public SocialMediaService(){
        socialMediaDAO = new SocialMediaDAO();
    }

    public Account registerAccount(Account newAccount) {
        boolean validSyntax = !(newAccount.getUsername().trim().equals("") || newAccount.getPassword().length() <= 4);
        if(validSyntax){
            List<Account> accounts = socialMediaDAO.getAllAccounts();
            for (Account account : accounts) {
                if(account.getUsername().equals(newAccount.getUsername())){
                    return null;
                }
            }
            Account addedAccount = socialMediaDAO.addNewAccount(newAccount);
            return addedAccount;
        }
        return null;
    }

    public Account loginAccount(Account loginAccount) {
        List<Account> accounts = socialMediaDAO.getAllAccounts();
        String username = loginAccount.getUsername();
        String password = loginAccount.getPassword();    
        for (Account account : accounts) {
                if(account.getUsername().equals(username) && account.getPassword().equals(password)){
                    return account;
                }
            }
        return null;
    }

    public Message postMessage(Message newMessage){
        if(socialMediaDAO.getAccountById(newMessage.posted_by) && newMessage.getMessage_text().trim().length() > 0 && newMessage.getMessage_text().length() < 255){
            Message postedMessage = socialMediaDAO.postMessage(newMessage);
            return postedMessage;
        }
        return null;
    }

    public List<Message> getAllMessages(){
        return socialMediaDAO.getAllMessages();
    }

    public Message getMsgByMsgId(int id) {
        return socialMediaDAO.getMsgByMsgId(id);
    }

    public Message deleteMsgByMsgId(int id) {
        Message targetMessage = getMsgByMsgId(id);
        if(targetMessage!=null){
            socialMediaDAO.deleteMsgByMsgId(id);
        }
        return targetMessage;
    }

    public Message patchMsgByMsgId(int id, String newMessage) {
        Message targetMessage = getMsgByMsgId(id);
        if(targetMessage != null && newMessage.trim().length() > 0 && newMessage.length() < 255){
            return socialMediaDAO.patchMsgByMsgId(id, newMessage, targetMessage);
        }  
        return null;
    }

    public List<Message> getAllMsgByAccId(int accId) {
        return socialMediaDAO.getAllMsgByAccId(accId);
    }
}

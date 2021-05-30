package com.company.Messages.SendHolders;

import com.company.Data.User;
import com.company.Messages.Enums.MessageAuthor;
import com.company.Messages.IMessage;
import com.company.Messages.Message;
import com.company.Utilities.UserUtilities;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

//Класс для отправки сообщения
public class SendMessageHolder implements IMessage {

    private String from;
    private String to;
    private Message encryptedMessageForTo;
    private Message encryptedMessageForFrom;

    public String getFrom(){
        return from;
    }

    public String getTo(){
        return to;
    }

    public Message getMessageForTo(){
        return encryptedMessageForTo;
    }

    public Message getMessageForFrom(){
        return encryptedMessageForFrom;
    }

    public SendMessageHolder(){}

    public SendMessageHolder(String from, String to, Message message) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, IOException {
        this.from = from;
        this.to = to;
        this.encryptedMessageForFrom = new Message(message.messageType, MessageAuthor.Me,User.rsa.encode(User.data.key,message.data),message.index);//User.rsa.encode(User.data.key,message.data)
        this.encryptedMessageForTo = new Message(message.messageType,MessageAuthor.Him,User.rsa.encode(UserUtilities.getUserByPhone(to).key,message.data),message.index);//User.rsa.encode(UserUtilities.getUserByPhone(to).key,message.data)
        encryptedMessageForFrom.setTimeNow();
        encryptedMessageForTo.setTimeNow();
    }

    @Override
    public String toString()
    {
        return "from = " + from + "\nto = " + to + "\nmessage = " + encryptedMessageForFrom.toString();
    }
}





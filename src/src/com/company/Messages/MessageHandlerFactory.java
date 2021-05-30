package com.company.Messages;

import com.company.Data.Caching;
import com.company.Data.User;
import com.company.Messages.Enums.MessageAuthor;
import com.company.Messages.Enums.MessageHandlerType;
import com.company.Messages.ReceiveHolders.MissedMessagesHolder;
import com.company.Messages.ReceiveHolders.ReceiveMessageHolder;
import com.company.Server.WebsocketClient;
import com.company.Utilities.JSONWrapper;

import java.util.HashMap;

//Класс-фабрика для обработчиков разных типов сообщений
public final class MessageHandlerFactory {

    public static HashMap<MessageHandlerType, WebsocketClient.MessageHandler> messageHandlers = new HashMap<>() {{
        put(MessageHandlerType.Receive, receiveMessage());
        put(MessageHandlerType.UpdateMissed, updateMissed());
    }};

    private static WebsocketClient.MessageHandler receiveMessage() {
        return message -> {
            ReceiveMessageHolder receiveMessageHolder = JSONWrapper.decode(message.message, ReceiveMessageHolder.class);
            Caching.storeReceived(receiveMessageHolder);
            User.invokeOnMessageReceived(receiveMessageHolder.getMessageForTo());
        };
    }

    private static WebsocketClient.MessageHandler updateMissed() {
        return message -> {
            MissedMessagesHolder missedMessagesHolder = JSONWrapper.decode(message.message, MissedMessagesHolder.class);

            for (int i = 0; i < missedMessagesHolder.messages.length; i++) {

                Message tempMessage = JSONWrapper.decode(missedMessagesHolder.messages[i],Message.class);
                tempMessage.sender = MessageAuthor.Him;
                Caching.storeReceived(new ReceiveMessageHolder(missedMessagesHolder.from,User.data.phone,tempMessage));
                User.invokeOnMessageReceived(tempMessage);

            }

        };
    }

    /*private static WebsocketClient.MessageHandler editIndex()
    {
        return message -> {
            EditIndexHolder editIndexHolder = JSONWrapper.decode(message.message, EditIndexHolder.class);
            MessageUtilities.editIndexOfMessage(editIndexHolder.phone,String.valueOf(editIndexHolder.originalIndex),String.valueOf(editIndexHolder.newIndex));
        };
    }*/

}


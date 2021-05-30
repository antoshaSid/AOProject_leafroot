package com.company.Messages;

import com.company.Messages.Enums.Command;
import com.company.Messages.SendHolders.SendMessageHolder;
import com.company.Messages.SendHolders.UpdateMissedMessagesHolder;

public final class MessageWrapperFactory {

    public static MessageWrapper createSendMessageWrapper(SendMessageHolder message){
        return new MessageWrapper(Command.Send,message);
    }

    public static MessageWrapper createUpdateMissedMessageWrapper(UpdateMissedMessagesHolder message){
        return new MessageWrapper(Command.UpdateMissed,message);
    }

}

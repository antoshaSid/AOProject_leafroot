package com.company.EventHolders;

import com.company.Messages.Message;
import com.company.Messages.ReceiveHolders.ReceiveMessageHolder;

public interface IMessageReceived {

    public void Receive(ReceiveMessageHolder message);

}

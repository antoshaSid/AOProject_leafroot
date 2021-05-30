package com.company.Utilities;

import com.company.Data.Config;
import com.company.Data.User;
import com.company.Messages.Message;

import java.io.*;

//Методя для удобной работы с сообщениями
public final class MessageUtilities {

    public static Message getLastMessageOfUser(String phone) {
        var file = new File(Config.CHAT_CACHE_PATH + "\\" + phone + ".chat");
        int lines = 0;
        StringBuilder builder = new StringBuilder();
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            long fileLength = file.length() - 2;
            // Set the pointer at the last of the file
            randomAccessFile.seek(fileLength);
            for(long pointer = fileLength; pointer >= 0; pointer--){
                randomAccessFile.seek(pointer);
                char c;
                // read from the last one char at the time
                c = (char)randomAccessFile.read();
                // break when end of the line
                if(c == '\n'){
                    break;
                }
                builder.append(c);
            }
            builder.reverse();
            return JSONWrapper.decode(builder.toString(),Message.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Message getMessageByIndex(String phone, int index) {
        try
        {
            File file = new File(Config.CACHE_PATH + "\\" + phone + ".chat");
            FileReader fr=new FileReader(file);   //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while((line=br.readLine())!=null)
            {
                if(line.contains(String.valueOf(index)))
                {
                    return JSONWrapper.decode(line, Message.class);
                }
            }
            fr.close();    //closes the stream and release the resources
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void editIndexOfMessage(String phone, String oldIndex, String newIndex){
        File fileToBeModified = new File(Config.CACHE_PATH + "\\" + phone + ".chat");

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();

            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }

            String newContent = oldContent.replaceAll("\"index\":" + oldIndex, "\"index\":" + newIndex);
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static String getDecodedTextMessage(Message message){
        return User.rsa.decode(message.data);
    }

    public static byte[] getDecodedImageMessage(Message message){
        return Convert.hexStringToBytes(User.rsa.decode(message.data));
    }

}
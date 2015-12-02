package myfirstmavenproject.test.maven;

import com.mashape.unirest.http.exceptions.UnirestException;

public class Commands {
  String      text;
  int         chatID;
  TelegramBot telegramBot;
  String[]    duty  = new String[10];
  int         Index = 0;
  String      username;

  public Commands(String commandText, int chatID, TelegramBot telegramBot,
      String username) {
    text = commandText.toLowerCase();
    this.chatID = chatID;
    this.telegramBot = telegramBot;
    this.username = username;
  }

  public void CheckCommands() throws UnirestException {
    if (text.contains("/start")) {
      String reply = "Hi, this is an example bot\n" + "Your chat_id is "
          + chatID + "\n" + "Your username is " + username;
      telegramBot.sendMessage(chatID, reply);
    } else if (text.contains("/echo")) {
      telegramBot.sendMessage(chatID, "Received " + text);
    } else if (text.contains("/toupper")) {
      String param = text.substring("/toupper".length(), text.length());
      telegramBot.sendMessage(chatID, param.toUpperCase());
    } else if (text.contains("hi")) {
      telegramBot.sendMessage(chatID, "hi");
    } else if (text.contains("time")) {
      telegramBot.sendMessage(chatID, "time");
    }
  }

}

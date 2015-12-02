package myfirstmavenproject.test.maven;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public final class TelegramBot {
  private final String endpoint = "https://api.telegram.org/bot";
  private final String token;

  public TelegramBot(String token) {
      this.token = token;
  }

  public HttpResponse<JsonNode> sendMessage(Integer chatId, String text) throws UnirestException {
      return Unirest.post(endpoint + token + "/sendMessage")
              .field("chat_id", chatId)
              .field("text", text)
              .asJson();
  }

  public HttpResponse<JsonNode> getUpdates(Integer offset) throws UnirestException {
      return Unirest.post(endpoint + token + "/getUpdates")
              .field("offset", offset)
              .asJson();
  }

  public void run() throws UnirestException {
    int last_update_id = 0; // last processed command

    HttpResponse<JsonNode> response;
    while (true) {
        response = getUpdates(last_update_id++);

        if (response.getStatus() == 200) {
            JSONArray responses = response.getBody().getObject().getJSONArray("result");
            if (responses.isNull(0)) continue;
            else last_update_id = responses
                    .getJSONObject(responses.length() - 1)
                    .getInt("update_id") + 1;

              for (int i = 0; i < responses.length(); i++) {
                JSONObject message = responses
                    .getJSONObject(i)
                    .getJSONObject("message");

                int chat_id = message
                    .getJSONObject("chat")
                    .getInt("id");

                String username = message
                    .getJSONObject("chat")
                    .getString("first_name");

                String text = message
                    .getString("text");
                
                
                Commands thisCommands=new Commands(text,chat_id,this,username);
                thisCommands.CheckCommands();
                
//                if (text.contains("/start")) {
//                    String reply = "Hi, this is an example bot\n" +
//                        "Your chat_id is " + chat_id + "\n" +
//                        "Your username is " + username;
//                    sendMessage(chat_id, reply);
//                } else if i(text.contains("/echo")) {
//                    sendMessage(chat_id, "Received " + text);
//                } else if (text.contains("/toupper")) {
//                    String param = text.substring("/toupper".length(), text.length());
//                    sendMessage(chat_id, param.toUpperCase());
//                }else if (text.contains("hi")){
//                  sendMessage(chat_id, "hi");
//                }
            }
        }
    }
  }
}

package myfirstmavenproject.test.maven;

import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {

  public static void main(String[] args) throws UnirestException {
    String token = "164333430:AAE_vz48IRyFfs7EIUBOyEsr--phlNDqjb4";
    TelegramBot test = new TelegramBot(token);
    test.run();
  }

}

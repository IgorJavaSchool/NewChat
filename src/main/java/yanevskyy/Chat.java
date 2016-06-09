package yanevskyy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Imitations chat. User writes a message in console and get random answer from console.
 */
public class Chat {

  private GetMessage getMessage;
  private SendMessage sendMessage;
  private boolean exit;
  private boolean stopSend;
  protected String fileLog;

  public Chat(String txt, String log){
    this.getMessage = new GetMessage();
    this.sendMessage = new SendMessage(txt);
    this.exit = false;
    this.stopSend = false;
    this.fileLog = log;
  }

  /**
   * Takes messages from User and writes its into file "LOG".
   * Takes random message from text file and writes its into file "LOG".
   * @throws IOException
     */
  public void startProgram() throws IOException {
    String message;
    Writer writer = new FileWriter(fileLog);
    sendMessage.fillText();
    while (!exit){
      message = getMessage.readMessage();
      writer.write(message + "\n");
      checkMessage(message);
      if (!stopSend){
        writer.write(sendMessage.generationMessage());
      }
    }
    getMessage.closeStream();
    writer.flush();
    writer.close();
  }

  /**
   * Gets word or sentence from console. Returns random sentence from file.
   * If gets "стоп", then continue writes the words in console but not returns.
   * If gets "продолжить", then continue writes the words in console and
   * returns random sentence from file.
   * If gets "закончить", then closes program.
   * @param message
     */
  public void checkMessage(String message){
    if (message.equalsIgnoreCase("стоп"))
      this.stopSend = true;
    else if (message.equalsIgnoreCase("продолжить"))
      this.stopSend = false;
    else if (message.equalsIgnoreCase("закончить")) {
      this.exit = true;
      this.stopSend = true;
    }
  }

  public static void main(String[] args) {
    Chat chat = new Chat("Text.txt", "LOG.txt");
    try {
      chat.startProgram();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
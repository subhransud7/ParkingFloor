

import java.io.*;
import services.TicketService;

public class Main {
  public static void main(String[] args)throws IOException{

    final BufferedReader reader;
    CommandHandler commandHandler;

    final String fileName = "input.txt";
    final File file = new File(fileName);
    final TicketService ticketService = new TicketService();
    final CommandFactory commandFactory = new CommandFactory(ticketService);
    
    try{
      reader = new BufferedReader(new FileReader(file));
      String input = reader.readLine();
      while(input != null){
        commandHandler = new CommandHandler(input,commandFactory);
        commandHandler.setValidity();
        if(!commandHandler.checkValid()){
          commandHandler.displayError();
          input = reader.readLine(); 
          continue;
        }else{
          commandHandler.setParams();
          commandHandler.execute();
        }
        input = reader.readLine(); 
      }
      
    }catch(FileNotFoundException e){
      System.out.println("File Not Found");
    } 
  }
}
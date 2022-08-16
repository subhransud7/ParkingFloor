import java.util.*;
import services.PrintService;
import entities.Command;
import entities.CommandEnum;
import entities.ErrorEnum;

/*
Processess every request and assigns a command to handle the request if valid.
*/
public class CommandHandler{
  private CommandEnum commandType; 
  private List<String> params;
  private final String[] parsedCommand;
  private boolean isValid;
  private final CommandFactory commandFactory;
  private ErrorEnum errorType;
  
  CommandHandler(String unParsedString, CommandFactory commandFactory){
    this.parsedCommand = unParsedString.split(" +", 0);
    this.commandFactory = commandFactory;
  }

  void setValidity(){
    HashMap<String,List<String>> commandParametersMap = commandFactory.getCommandParametersMap();
    setValid(false);
    for(String comm: commandParametersMap.keySet()){
      //checks whether command is correct
      if(parsedCommand[0].equals(comm)){
        List<String> parameters = commandParametersMap.get(comm);
        commandType = commandFactory.getCommandEnumMap().get(comm);
        setValid(true);
        //checks if parameter arguments are valid
        if(parsedCommand.length>2){
          if(parameters.isEmpty()){
            setValid(false);
            errorType = ErrorEnum.INVALID_FORMAT;
            return;
          }
          int i,j;
          for(i=2,j=0;i<parsedCommand.length && j<parameters.size();i+=2,j++){
            if(!parsedCommand[i].equals(parameters.get(j))){
              setValid(false);
              errorType = ErrorEnum.INVALID_FORMAT;
              return;
            }
          }
          if(parsedCommand.length!=i || parameters.size()!=j){
            setValid(false);
            errorType = ErrorEnum.INVALID_FORMAT;
            return;
          }
        }
      }
    }
    if(!checkValid()){
      errorType=ErrorEnum.INVALID_COMMAND;
    }
  }

  boolean checkValid() {
    return isValid;
  }

  private void setValid(boolean b) {
    isValid=b;
  }

  void setParams(){
    params = new ArrayList<String>();
    if(commandType == CommandEnum.CREATE_PARK){
      params.add(parsedCommand[1]);
    }else if(commandType == CommandEnum.PARK){
      params.add(parsedCommand[1]);
      params.add(parsedCommand[3]);
    }else if(commandType == CommandEnum.LEAVE){
      params.add(parsedCommand[1]);
    }else if(commandType == CommandEnum.SLOT_NO_FOR_VEHICLE){
      params.add(parsedCommand[1]);
    }else if(commandType == CommandEnum.SLOT_NUMS_FOR_AGE){
      params.add(parsedCommand[1]);
    }else if(commandType == CommandEnum.VEHICLE_NUMS_FOR_AGE){
      params.add(parsedCommand[1]);
    }
  }

  public void execute(){
    Command command = commandFactory.getCommandMap().get(commandType);
    command.setParams(params);
    if(command.isValid()){
      command.execute();
    }
    command.printResult();
  }

  public void displayError() {
    new PrintService(errorType).displayError();
  }
} 


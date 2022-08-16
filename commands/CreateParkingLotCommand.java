package commands;

import services.TicketService;
import services.PrintService;
import java.util.List;
import entities.Command;
import entities.CommandEnum;
import entities.ErrorEnum;

/*
* Command to create a parking lot with given number of slots.
*/
public class CreateParkingLotCommand implements Command{
  private int slots;
  private List<String> params;
  private ErrorEnum errorType;
  private final TicketService ticketService;
  private final CommandEnum commandType;

  public CreateParkingLotCommand(TicketService ticketService){
    this.ticketService=ticketService;
    this.errorType = ErrorEnum.DEFAULT;
    this.commandType = CommandEnum.CREATE_PARK;
  }

  @Override
  public void execute(){
    ticketService.createParkingFloor(slots);
  }

  @Override
  public boolean isValid() {
    if(ticketService.isParkingFloorPresent()){
      errorType=ErrorEnum.PARKING_FLOOR_PRESENT;
    }else if(slots < 1 || slots > 1000){
      errorType=ErrorEnum.INVALID_PARAMETERS;
    }
    if(errorType!=ErrorEnum.DEFAULT){
      return false;
    }
    return true;
  }

  @Override
  public void printResult() {
    if(errorType!=ErrorEnum.DEFAULT){
      new PrintService(errorType, commandType).displayError();
    }else{
      new PrintService(commandType, params).displaySuccess();
    }
  }

  @Override
  public void setParams(List<String> params) {
    this.params = params;
    slots=Integer.valueOf(params.get(0));
    errorType = ErrorEnum.DEFAULT; 
  }
}
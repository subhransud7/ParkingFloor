package commands;

import java.util.List;
import java.util.stream.Collectors;

import entities.Command;
import entities.ErrorEnum;
import entities.CommandEnum;
import services.PrintService;
import services.TicketService;

public class SlotNumbersForDriverAgeCommand implements Command{
    private List<Integer> slotNums;
    private int ageOfDriver;
    final private TicketService ticketService;
    private List<String> params;
    private ErrorEnum errorType;
    private CommandEnum commandType;

    public SlotNumbersForDriverAgeCommand(TicketService ticketService){
      this.ticketService=ticketService;
      this.commandType=CommandEnum.SLOT_NUMS_FOR_AGE;
      this.errorType=ErrorEnum.DEFAULT;
    }

    @Override
    public void execute(){
      slotNums=ticketService.getSlotNumsForDriverAge(ageOfDriver);
    } 

    @Override
    public void printResult() {
      if(errorType!=ErrorEnum.DEFAULT){
        new PrintService(errorType, commandType).displayError();
      }else{
        params = slotNums.stream().map(Object::toString)
                                  .collect(Collectors.toUnmodifiableList());
        new PrintService(commandType, params).displaySuccess();
      }
    }

    @Override
    public boolean isValid() {
      if(!ticketService.isParkingFloorPresent()){
        errorType=ErrorEnum.NO_PARKING_FLOOR;
      }else if(ageOfDriver < 1){
        errorType=ErrorEnum.INVALID_PARAMETERS;
      }
      if(errorType!=ErrorEnum.DEFAULT){
        return false;
      }
      return true;
    }

    @Override
    public void setParams(List<String> params) {
      // TODO Auto-generated method stub
      this.params = params;
      ageOfDriver = Integer.valueOf(params.get(0));
      errorType = ErrorEnum.DEFAULT;
    }
  
}
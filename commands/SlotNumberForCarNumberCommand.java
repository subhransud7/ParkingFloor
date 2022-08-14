package commands;
import services.PrintService;
import services.TicketService;

import java.util.List;

import entities.Command;
import entities.ErrorEnum;
import entities.CommandEnum;


public class SlotNumberForCarNumberCommand implements Command{
    private int slotNum;
    private String vehicleNum;
    private final TicketService ticketService;
    private List<String> params;
    private ErrorEnum errorType;
    private final CommandEnum commandType;
    
    public SlotNumberForCarNumberCommand(TicketService ticketService){
      this.ticketService=ticketService;
      this.commandType=CommandEnum.SLOT_NO_FOR_VEHICLE;
      this.errorType=ErrorEnum.DEFAULT;
    }
    @Override
    public void execute(){
      slotNum=ticketService.getSlotNumForVehicleNum(vehicleNum);
      if(slotNum<0){
        errorType=ErrorEnum.NO_VEHICLE_FOUND;
      }
    }    

    @Override
    public void printResult() {
      if(errorType!=ErrorEnum.DEFAULT){
        new PrintService(errorType, commandType).displayError();
      }else{
        params.add(Integer.toString(slotNum));
        new PrintService(commandType, params).displaySuccess();
      }
    }
    
    @Override
    public boolean isValid() {
      if(!ticketService.isParkingFloorPresent()){
        errorType=ErrorEnum.NO_PARKING_FLOOR;
        return false;
      }      
      return true;
    }

    @Override
    public void setParams(List<String> params) {
      // TODO Auto-generated method stub
      this.params = params;
      vehicleNum = params.get(0);
      errorType = ErrorEnum.DEFAULT;
    }
  
}
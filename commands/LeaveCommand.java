package commands;
import services.PrintService;
import services.TicketService;

import java.util.List;
import entities.*;
import entities.Command;
import entities.Vehicle;

public class LeaveCommand implements Command{
    private int slotToFree;
    private Vehicle vehicleLeft;
    private final CommandEnum commandType;
    private final TicketService ticketService;
    private ErrorEnum errorType;
    private List<String> params;

    public LeaveCommand(TicketService ticketService){
      this.ticketService=ticketService;
      this.commandType=CommandEnum.LEAVE;
      this.errorType=ErrorEnum.DEFAULT;
    }

    @Override
    public void execute(){
      vehicleLeft = ticketService.freeSlotNum(slotToFree);
      if(vehicleLeft == null){
        errorType=ErrorEnum.NO_VEHICLE_FOUND;
      }
    }

    @Override
    public void printResult() {
      if(errorType!=ErrorEnum.DEFAULT){
        new PrintService(errorType, commandType).displayError();
      }else{
        params.add(vehicleLeft.getVehicleNum());
        params.add(Integer.toString(vehicleLeft.getAgeOfDriver()));
        new PrintService(commandType, params).displaySuccess();
      }
    }
        
    @Override
    public boolean isValid() {
      if(!ticketService.isParkingFloorPresent()){
        errorType=ErrorEnum.NO_PARKING_FLOOR;
      }
      else if(slotToFree > ticketService.getCapacity() || slotToFree < 1){
        errorType=ErrorEnum.INVALID_PARAMETERS;
      }
      if(errorType!=ErrorEnum.DEFAULT){
        return false;
      }
      return true;
    }

    @Override
    public void setParams(List<String> params) {
      this.params = params;
      slotToFree=Integer.valueOf(params.get(0));
      errorType = ErrorEnum.DEFAULT;
    }
  
}
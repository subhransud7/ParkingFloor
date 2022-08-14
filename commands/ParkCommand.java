package commands;

import java.util.List;

import entities.Command;
import entities.ErrorEnum;
import entities.CommandEnum;
import services.PrintService;
import services.TicketService;
import entities.Vehicle;

public class ParkCommand implements Command{
    private String vehicleNum;
    private int ageOfDriver;
    private final TicketService ticketService;
    private ErrorEnum errorType;
    private final CommandEnum commandType;
    private int slotAssigned;
    private List<String> params;
    
    public ParkCommand(TicketService ticketService){
      this.ticketService=ticketService;
      this.commandType=CommandEnum.PARK;
      this.errorType=ErrorEnum.DEFAULT;
    }

    @Override
    public void execute(){
      slotAssigned = ticketService.assignSlot(new Vehicle(vehicleNum,ageOfDriver));
      //ticketService.updateData(vehicleNum,ageOfDriver);
    }

    @Override
    public void printResult() {
      if(errorType!=ErrorEnum.DEFAULT){
        new PrintService(errorType, commandType).displayError();
      }else{
        params.add(1,Integer.toString(slotAssigned));
        new PrintService(commandType, params).displaySuccess();
      }
    }
        
    @Override
    public boolean isValid() {
      if(ageOfDriver < 1){
        errorType=ErrorEnum.INVALID_PARAMETERS;
      }
      else if(!ticketService.isParkingFloorPresent()){
        errorType=ErrorEnum.NO_PARKING_FLOOR;
      }
      else if(ticketService.isParkingFull()){
        errorType=ErrorEnum.PARKING_FULL;
      }
      else if(ticketService.checkVehicle(new Vehicle(vehicleNum, ageOfDriver))){
        errorType=ErrorEnum.VEHICLE_ALREADY_PRESENT;
      }
      if(errorType!=ErrorEnum.DEFAULT){
        return false;
      }
      return true;
    }
    
    @Override
    public void setParams(List<String> params) {
      // TODO Auto-generated method stub
      this.params=params;
      vehicleNum=params.get(0);
      ageOfDriver=Integer.valueOf(params.get(1));
      errorType = ErrorEnum.DEFAULT;
    }
  
}
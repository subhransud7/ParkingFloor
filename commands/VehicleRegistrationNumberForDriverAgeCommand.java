package commands;

import java.util.List;

import services.PrintService;
import services.TicketService;
import entities.Command;
import entities.CommandEnum;
import entities.ErrorEnum;

public class VehicleRegistrationNumberForDriverAgeCommand implements Command{
    private List<String> vehicleNums;
    private int age;
    private final TicketService ticketService;
    private List<String> params;
    private ErrorEnum errorType;
    private final CommandEnum commandType;
    
    public VehicleRegistrationNumberForDriverAgeCommand(TicketService ticketService){
        this.ticketService = ticketService;
        this.commandType=CommandEnum.VEHICLE_NUMS_FOR_AGE;
        this.errorType=ErrorEnum.DEFAULT;
    }

    @Override
    public void execute() {
        vehicleNums = ticketService.getVehicleNumsForAge(age);
    }

    @Override
    public void printResult() {
      if(errorType!=ErrorEnum.DEFAULT){
        new PrintService(errorType, commandType).displayError();
      }else{
        params = vehicleNums;
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
      this.params = params;
      this.age = Integer.valueOf(params.get(0));
      errorType = ErrorEnum.DEFAULT;
    }
  
}
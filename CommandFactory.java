import services.TicketService;
import java.util.HashMap;
import java.util.List;
import commands.CreateParkingLotCommand;
import entities.*;
import commands.*;
/*
Factory class for all the commands.
*/
public class CommandFactory {
    final private TicketService ticketService;
    final private HashMap<CommandEnum,Command> commandMap;
    final private HashMap<String,List<String>> commandParametersMap;
    final private HashMap<String,CommandEnum> commandEnumMap;
  
    CommandFactory(TicketService ticketService){
        this.ticketService = ticketService;
        commandMap = new HashMap<CommandEnum,Command>();
        commandParametersMap = new HashMap<String,List<String>>();
        commandEnumMap = new HashMap<String,CommandEnum>();
        initialize();
    }
    private void initiateCommandMap(){
        commandMap.put(CommandEnum.CREATE_PARK, new CreateParkingLotCommand(ticketService));
        commandMap.put(CommandEnum.LEAVE, new LeaveCommand(ticketService));
        commandMap.put(CommandEnum.PARK, new ParkCommand(ticketService));
        commandMap.put(CommandEnum.SLOT_NUMS_FOR_AGE, new SlotNumbersForDriverAgeCommand(ticketService));
        commandMap.put(CommandEnum.VEHICLE_NUMS_FOR_AGE, new VehicleRegistrationNumberForDriverAgeCommand(ticketService));
        commandMap.put(CommandEnum.SLOT_NO_FOR_VEHICLE, new SlotNumberForCarNumberCommand(ticketService));
    }
    private void initiateCommandParametersMap(){
        commandParametersMap.put("Create_parking_lot",null);
        commandParametersMap.put("Leave",null);
        commandParametersMap.put("Park",List.of("driver_age"));
        commandParametersMap.put("Slot_numbers_for_driver_of_age",null);
        commandParametersMap.put("Slot_number_for_car_with_number",null);
        commandParametersMap.put("Vehicle_registration_number_for_driver_of_age",null);
      }
    private void initiateCommandEnumMap(){
        commandEnumMap.put("Create_parking_lot", CommandEnum.CREATE_PARK);
        commandEnumMap.put("Leave", CommandEnum.LEAVE);
        commandEnumMap.put("Park", CommandEnum.PARK);
        commandEnumMap.put("Slot_numbers_for_driver_of_age", CommandEnum.SLOT_NUMS_FOR_AGE);
        commandEnumMap.put("Slot_number_for_car_with_number", CommandEnum.SLOT_NO_FOR_VEHICLE);
        commandEnumMap.put("Vehicle_registration_number_for_driver_of_age", CommandEnum.VEHICLE_NUMS_FOR_AGE);
    }
    public HashMap<CommandEnum,Command> getCommandMap() {
        return commandMap;
    }
    public HashMap<String,List<String>> getCommandParametersMap() {
        return commandParametersMap;
    }
    public HashMap<String,CommandEnum> getCommandEnumMap() {
        return commandEnumMap;
    }
    public void initialize() {
        initiateCommandMap();
        initiateCommandEnumMap();
        initiateCommandParametersMap();
    }

}

package services;

import entities.ErrorEnum;

import java.util.Arrays;
import java.util.List;

import entities.CommandEnum;

public class PrintService {
    private ErrorEnum invalidType;
    private CommandEnum commandType;
    private List<String> printValues;
    public PrintService(ErrorEnum invalidType, CommandEnum commandType){
        this.invalidType = invalidType;
        this.commandType = commandType;
        
    }

    public PrintService(CommandEnum commandType, List<String> printValues) {
        this.commandType = commandType;
        this.printValues = printValues;
    }
    public PrintService(ErrorEnum invalidType){
        this.invalidType=invalidType;
    }
    public void displayError(){
        switch(invalidType){
            case INVALID_COMMAND:
                System.out.println("Invalid Command");
                break;
            case INVALID_FORMAT:
                System.out.println("Invalid Command Format");
                break;
            case INVALID_PARAMETERS:
                System.out.println("Invalid Parameters for the given Command");
                break;
            case PARKING_FULL:
                System.out.println("Unable to Park. Parking Floor is Full");
                break;
            case NO_VEHICLE_FOUND:
                System.out.println("No Such Vehicle Found");
                break;
            case VEHICLE_ALREADY_PRESENT:
                System.out.println("Vehicle Already Present");
                break;
            case NO_PARKING_FLOOR:
                System.out.println("There is no Parking Floor");
                break;
            case PARKING_FLOOR_PRESENT:
                System.out.println("There is already a Parking Floor");
                break;

        }
    }
 

    public void displaySuccess(){
        switch(commandType){
            case CREATE_PARK:
                System.out.println("Created parking of "+printValues.get(0)+" slots");
                break;
            case PARK:
                System.out.println("Car with vehicle registration number \""+printValues.get(0)+"\" has been parked at slot number "+printValues.get(1));
                break;
            case LEAVE:
                System.out.println("Slot number "+ printValues.get(0)+" vacated, the car with vehicle registration number \""+printValues.get(1)+ "\" left the space, the driver of the car was of age "+printValues.get(2));
                break;
            case SLOT_NO_FOR_VEHICLE:
                System.out.println(printValues.get(1));
                break;
            case VEHICLE_NUMS_FOR_AGE:
                System.out.println(Arrays.toString(printValues.toArray()));
                break;
            case SLOT_NUMS_FOR_AGE:
                System.out.println(Arrays.toString(printValues.toArray()));
            }
        }

    }


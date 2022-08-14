package services;
//Issues a ticket to vehicle if slot is open
import entities.ParkingFloor;
import entities.Slot;
import entities.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class TicketService{
  private ParkingFloor parkingFloor;
  
  public TicketService(){
    
  }

  public void createParkingFloor(int slots){
    parkingFloor = new ParkingFloor(slots);
    initateParkingFloor(parkingFloor.getCapacity());
  }

  private void initateParkingFloor(int capacity) {
    for(int i=1;i<=capacity;i++){
      parkingFloor.addOrUpdateSlotNum(new Slot(i), null);
    }
  }

  public Vehicle freeSlotNum(int slotNum){
    Vehicle vehicleLeft = parkingFloor.getParkingFloorMap().get(new Slot(slotNum));
    parkingFloor.addOrUpdateSlotNum(new Slot(slotNum), null);
    parkingFloor.addToNextSlotNum(slotNum);
    return vehicleLeft;
  }
  
  public int assignSlot(Vehicle vehicle) {
    int freeSlot = parkingFloor.getNextSlotNum();
    parkingFloor.addOrUpdateSlotNum(new Slot(freeSlot,vehicle.getVehicleNum()), vehicle);
    return freeSlot;
  }

  public int getSlotNumForVehicleNum(String vehicleNum) {
    HashMap<Slot, Vehicle> parkingFloorMap = parkingFloor.getParkingFloorMap();
    for(Entry<Slot, Vehicle> entry: parkingFloorMap.entrySet()) {
      if(entry.getValue()!=null && vehicleNum.equals(entry.getValue().getVehicleNum())){
        return entry.getKey().getSlotNum();
      }
    }
    return -1;
  }
  public boolean checkVehicle(Vehicle vehicle){
    HashMap<Slot, Vehicle> parkingFloorMap = parkingFloor.getParkingFloorMap();
    for(Vehicle isVehicle: parkingFloorMap.values()){
      if(vehicle.equals(isVehicle)){
        return true;
      }
    }
    return false;
  }
  public List<Integer> getSlotNumsForDriverAge(int ageOfDriver) {
    HashMap<Slot, Vehicle> parkingFloorMap = parkingFloor.getParkingFloorMap();
    List<Integer> slotNums = new ArrayList<Integer>();
    for(Entry<Slot, Vehicle> entry: parkingFloorMap.entrySet()) {
      if(entry.getValue()!=null && entry.getValue().getAgeOfDriver()==ageOfDriver){
        slotNums.add(entry.getKey().getSlotNum());
      }
    }
    return slotNums;
  }

  public List<String> getVehicleNumsForAge(int ageOfDriver){
    HashMap<Slot, Vehicle> parkingFloorMap = parkingFloor.getParkingFloorMap();
    List<String> vehicleNums = new ArrayList<String>();
    for(Entry<Slot, Vehicle> entry: parkingFloorMap.entrySet()) {
      if(entry.getValue()!=null && entry.getValue().getAgeOfDriver()==ageOfDriver){
        vehicleNums.add(entry.getValue().getVehicleNum());
      }
    }
    return vehicleNums;
  }
  public boolean isParkingFloorPresent(){
    return (parkingFloor!=null);
  }

  public int getCapacity() { 
    return parkingFloor.getCapacity();
  }

  public boolean isParkingFull() {
    return parkingFloor.isFull();
  }
  


}
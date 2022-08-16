package services;

import entities.ParkingFloor;
import entities.Slot;
import entities.Vehicle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


/*
* This class provides all the ticketing services. All the business logic is implemented here. 
*/
public class TicketService{
  private ParkingFloor parkingFloor;

  
  /*
  * Creates a new parking floor with given number of slots.
  */
  public void createParkingFloor(int slots){
    parkingFloor = new ParkingFloor(slots);
    initateParkingFloor(parkingFloor.getCapacity());
  }
  
  /*
  * Adds the empty slots to the newly created parking floor.
  */
  private void initateParkingFloor(int capacity) {
    for(int i=1;i<=capacity;i++){
      parkingFloor.addOrUpdateSlotNum(new Slot(i), null);
    }
  }
  
  /*
  * Frees the slot when vehicle leaves and adds it to the free slots queue.
  * Returns the vehicle that left the slot.
  */
  public Vehicle freeSlotNum(int slotNum){
    Vehicle vehicleLeft = parkingFloor.getParkingFloorMap().get(new Slot(slotNum));
    parkingFloor.addOrUpdateSlotNum(new Slot(slotNum), null);
    parkingFloor.addToNextSlotNum(slotNum);
    return vehicleLeft;
  }

  /*
  * Returns the next free slot in the parking floor.
  */
  public int assignSlot(Vehicle vehicle) {
    int freeSlot = parkingFloor.getNextSlotNum();
    parkingFloor.addOrUpdateSlotNum(new Slot(freeSlot,vehicle.getVehicleNum()), vehicle);
    return freeSlot;
  }

  /*
  * Returns the slot number if the given vehicle number is parked in the parking floor
  * else returns -1.
  */
  public int getSlotNumForVehicleNum(String vehicleNum) {
    HashMap<Slot, Vehicle> parkingFloorMap = parkingFloor.getParkingFloorMap();
    for(Entry<Slot, Vehicle> entry: parkingFloorMap.entrySet()) {
      if(entry.getValue()!=null && vehicleNum.equals(entry.getValue().getVehicleNum())){
        return entry.getKey().getSlotNum();
      }
    }
    return -1;
  }

  /*
  * Returns whether the given vehicle is presently parked or not
  */
  public boolean checkVehicle(Vehicle vehicle){
    HashMap<Slot, Vehicle> parkingFloorMap = parkingFloor.getParkingFloorMap();
    for(Vehicle isVehicle: parkingFloorMap.values()){
      if(vehicle.equals(isVehicle)){
        return true;
      }
    }
    return false;
  }
  
  /*
  * Returns the slot numbers where the vehicles are parked for a given driver age
  */
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
  
  /*
  * Returns the vehicle numbers that are parked for a given driver age
  */
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

  /*
  * Checks if parking floor is already present or not
  */
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
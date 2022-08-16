package entities;

import java.util.HashMap;
import java.util.PriorityQueue;

/*
ParkingFloor represents a single parking floor with n slots. Keeps record of slots and vehicles parked within.
*/
public class ParkingFloor{
    final private int capacity;
    final private PriorityQueue<Integer> nextSlotNum ;
    final HashMap<Slot, Vehicle> parkingFloorMap;
    public ParkingFloor(int slots) {
        capacity=slots;
        parkingFloorMap = new HashMap<Slot, Vehicle>();
        nextSlotNum = new PriorityQueue<Integer>();
        initiateNextSlotNum();
    }
  
    /*
    * Initiates the slot free slot queue.
    */
    private void initiateNextSlotNum() {
        for(int i=1;i<=capacity;i++){
            nextSlotNum.add(i);
        }
    }
  
    /*
    * Stores the Vehicle against the provided slot.
    */
    public void addOrUpdateSlotNum(Slot slot, Vehicle vehicle){
        parkingFloorMap.put(slot,vehicle);
    }

    /*
    * Adds the slot freed to the queue
    */
    public void addToNextSlotNum(int slotNum){
        nextSlotNum.add(slotNum);
    }

    /*
    * Returns the nearest slot free to park the vehicle.
    */
    public int getNextSlotNum(){
        return nextSlotNum.poll();
    }

    /*
    * Returns the capacity of the parking floor.
    */
    public int getCapacity(){
        return capacity;
    }

    /*
    * Returns the mapping of slots to vehicle.
    */
    public HashMap<Slot,Vehicle> getParkingFloorMap(){
        return parkingFloorMap;
    }

    /*
    * Returns whether parking floor is full or not.
    */
    public boolean isFull(){
        return nextSlotNum.peek() == null;
    }    
}

package entities;

import java.util.HashMap;
import java.util.PriorityQueue;

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


    private void initiateNextSlotNum() {
        for(int i=1;i<=capacity;i++){
            nextSlotNum.add(i);
        }
    }

    public void addOrUpdateSlotNum(Slot slot, Vehicle vehicle){
        parkingFloorMap.put(slot,vehicle);
    }

    public void addToNextSlotNum(int slotNum){
        nextSlotNum.add(slotNum);
    }

    public int getNextSlotNum(){
        return nextSlotNum.poll();
    }

    public int getCapacity(){
        return capacity;
    }

    public HashMap<Slot,Vehicle> getParkingFloorMap(){
        return parkingFloorMap;
    }

    public boolean isFull(){
        return nextSlotNum.peek() == null;
    }
    
    boolean isEmpty(){
        return nextSlotNum.peek() == 1;
    }
    
 
}

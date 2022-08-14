package entities;

public class Slot{
    final private int slotNum;
    private String vehicleNum;

    public Slot(int slotNum){
        this.slotNum = slotNum;
    }

    public Slot(int slotNum, String vehicleNum){
        this.slotNum = slotNum;
        this.vehicleNum = vehicleNum;
    }

    public void setVehicleNum(String vehicleNum){
        this.vehicleNum = vehicleNum;
    }

    public int getSlotNum(){
        return slotNum;
    }

    public String getVehicleNum(){
        return vehicleNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Slot simpson = (Slot) o;
        return slotNum == simpson.slotNum;
    }

    @Override
    public int hashCode() {
        return slotNum;
    }
}
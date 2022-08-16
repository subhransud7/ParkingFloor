package entities;

/*
* Vehicle represent the vehicle to be parked. 
*/
public class Vehicle{
    final private String vehicleNum;
    final private int ageOfDriver;

    public Vehicle(String vehicleNum, int ageOfDriver){
        this.vehicleNum = vehicleNum;
        this.ageOfDriver = ageOfDriver;
    }

    public String getVehicleNum(){
        return vehicleNum;
    }

    public int getAgeOfDriver(){
        return ageOfDriver;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return vehicleNum.equals(vehicle.vehicleNum) ;
    }
}
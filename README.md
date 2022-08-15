# ParkingFloor
This is a Automated Ticketing System to create a single Parking Floor which can hold up to n slots. 
Each slot is given a number starting at one increasing with increasing distance from the entry point in steps of one.

When a car enters the parking floor, ticket is issued to the driver. The ticket
issuing process includes:-
1. Vehicle registration Number and the age of the driver of the car is noted.
2. Slots are allocated and ticket is issued.
3. Nearest slot is always allocated

Input:
Input for the program is provided by file which will contain commands to process.

Output:
It prints the result to terminal.

Valid Commands:
1. Create_parking_lot 6: Create a parking lot of 6 slots
2. Park KA-01-HH-1234 driver_age 21: Park car with vehicle registration number “KA-01-HH-1234”, and the vehicle is driven by the driver of age 21
3. Slot_numbers_for_driver_of_age 21: Return all Slot Number(Comma-separated) of all cars which have drivers with age==21
4. Slot_number_for_car_with_number PB-01-HH-1234: Return slot number for the car with registration number “PB-01-HH-1234”
5. Leave 2: Vacate the slot number 2 from the parking lot, i.e. car which was parked at slot number 2 has left the space if there exists no car at slot
number 2, print “NO VEHICLES FOUND”
6. Vehicle_registration_number_for_driver_of_age 18: Get all parked vehicle registration number of cars parked by the driver of age 18


Assumptions:
1. Vehicle Number would be of 13 length and will always follow the same format <XX-XX-XX-XXXX>.
3. There will be exactly 1 entry/exit points so there can't be any concurrent parking requests/exits.
4. There will be only 1 type of vehicle( not differentiating at all on type of vehicles).
5. No billing system.
6. Parameters of commands will be in right type.

Checks Implemented:
1. Checks whether given command is correct or returns INVALID_COMMAND
2. Checks whether format of a command is correct or returns INVALID_FORMAT
3. Checks for constraints for parameters passed.
4. Checks if Parking Floor is there before processing any command
5. Returns Parking is Full if there is no slot available.
6. Doesn't allow to park the already parked vehicle.
7. Checks whether leave requesting vehicle is valid(should already be parked).


Examples:
Input file: input1.txt, it gives the result same as in output1.txt
Input file: input2.txt, it gives the result same as in output2.txt

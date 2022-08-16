package entities;

import java.util.List;
/*
Provides an interface to all commands.
*/
public interface Command{
  
  /*
  * Checks whether command is valid.
  */
  boolean isValid();
  
  /*
  * Sets the parameters for the command.
  */
  void setParams(List<String> params);

  /*
  * Executes the command.
  */
  void execute();

  /*
  * Prints the result of the execution of command, whether success or failure.
  */
  void printResult();
}
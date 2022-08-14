package entities;

import java.util.List;

public interface Command{
  
  boolean isValid();
  void setParams(List<String> params);
  void execute();
  void printResult();
}
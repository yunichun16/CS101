package edu.nyu.cs;

import java.util.Arrays;

/**
 * A virtual moped, roaming the streets of New York.
 * The signatures of a few methods are given and MUST be completed and used as indicated.
 * Create as many additional properties or methods as you want, as long as the given methods behave as indicated in the instructions.
 * Follow good object-oriented design, especially the principles of abstraction (i.e. the black box metaphor) and encapsulation (i.e. methods and properties belonging to specific objects), as we have learned them.
 * The rest is up to you.
 */
public class Moped {

    private int[] location;
    private String orientation;
    private int gasLevel;
    private boolean parked;

  public Moped() {
    this.location = new int[]{10, 5};
    this.orientation = "south";
    this.gasLevel = 100;
  }
    /**
     * Sets the orientation of the moped to a particular cardinal direction.
     * @param orientation A string representing which cardinal direction at which to set the orientation of the moped.  E.g. "north", "south", "east", or "west".
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation.toLowerCase();
    }

    /**
     * Returns the current orientation of the moped, as a lowercase String.
     * E.g. "north", "south", "east", or "west".
     * @return The current orientation of the moped, as a lowercase String.
     */
    public String getOrientation() {
        return this.orientation;       
    }

    /**
     * Prints the current location, by default exactly following the format:
     *      Now at 12th St. and 5th Ave, facing South.
     *
     * If the current location is associated with location-based advertising, this method should print exactly following format:
     *      Now at 12th St. and 4th Ave, facing West.  Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?
     * 
     * Note that the suffixes for the numbers must be correct: i.e. the "st" in "1st", "nd" in "2nd", "rd" in "3rd", "th" in "4th", etc, must be correct.
     */
    public void printLocation() {
        String streetSuffix = getSuffix(this.location[0]);
        String avenueSuffix = getSuffix(this.location[1]);
        String message = "Now at " + this.location[0] + streetSuffix + " St. and " + this.location[1] + avenueSuffix + " Ave, facing " + this.orientation + ".";
        if (shouldShowAd()) {
          message += " Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?";
        }
        System.out.println(message);
    }
    
    private String getSuffix(int num) {
      String suffix;
      switch (num % 10) {
          case 1:
              suffix = "st";
              break;
          case 2:
              suffix = "nd";
              break;
          case 3:
              suffix = "rd";
              break;
          default:
              suffix = "th";
              break;
      }
      return suffix;
    }

    public boolean shouldShowAd() {
      return this.location[0] == 12 && this.location[1] == 4;
    }

    /**
     * Handles the command, `go left`.
     * Moves the moped one block to the left, and causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.  Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goLeft() {
        if (this.gasLevel >= 2) {
            this.gasLevel -= 2;
            switch (this.orientation) {
              case "north":
                this.orientation = "west";
                this.location[1]--;
                break;
              case "east":
                this.orientation = "north";
                this.location[0]++;
                break;
              case "south":
                this.orientation = "east";
                this.location[1]++;
                break;
              case "west":
                this.orientation = "south";
                this.location[0]--;
                break;
            }
          } else {
            System.out.println("Not enough gas to go left!");
          }
        }

    /**
     * Handles the command, `go right`.
     * Moves the moped one block to the right, and causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.  Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goRight() {
        if (this.gasLevel >= 2) {
            this.gasLevel -= 2;
            switch (this.orientation) {
              case "north":
                this.orientation = "east";
                this.location[1]++;
                break;
              case "east":
                this.orientation = "south";
                this.location[0]--;
                break;
              case "south":
                this.orientation = "west";
                this.location[1]--;
                break;
              case "west":
                this.orientation = "north";
                this.location[0]++;
                break;
            }
          } else {
            System.out.println("Not enough gas to go right!");
          }
        }

    /**
     * Handles the command,`straight on`.
     * Moves the moped one block straight ahead.
     * Consumes gas with each block moved, and doesn't move unless there is sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goStraight() {
        if (this.gasLevel >= 1) {
            this.gasLevel--;
            switch (this.orientation) {
              case "north":
                this.location[0]++;
                break;
              case "east":
                this.location[1]++;
                break;
              case "south":
                this.location[0]--;
                break;
              case "west":
                this.location[1]--;
                break;
            }
          } else {
            System.out.println("Not enough gas to go straight!");
          }
    }

    /**
     * Handles the command,`back up`.
     * Moves the moped one block backwards, but does not change the cardinal direction the moped is facing.
     * Consumes gas with each block moved, and doesn't move unless there is sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goBackwards() {
        if (this.gasLevel >= 1) {
        this.gasLevel--;
        switch (this.orientation) {
            case "north":
            this.location[0]--;
            break;
            case "east":
            this.location[1]--;
            break;
            case "south":
            this.location[0]++;    
            break;
            case "west":
            this.location[1]++;
            break;
        }
        } else {
        System.out.println("Not enough gas to go backwards!");
        }
    }

    /**
     * Handles the command,`how we doin'?`.
     * This method must not print anything.
     * @return The current gas level, as an integer from 0 to 100.
     */
    public int getGasLevel() {
      int currentGasLevel = this.gasLevel;
      this.gasLevel = 100; // assuming 100 is the maximum gas level
      return currentGasLevel;
    }

    /**
     * Prints the current gas level, by default exactly following the format:
     *      The gas tank is currently 85% full.
     *
     * If the moped is out of gas, this method should print exactly following format:
     *      We have run out of gas.  Bye bye!
     */
    public void printGasLevel() {
        if (gasLevel == 0) {
            System.out.println("We have run out of gas.  Bye bye!");
        } else {
            System.out.println("The gas tank is currently " + gasLevel + "% full.");
        }
    }

    /**
     * Handles the command, `fill it up`.
     * This method must not print anything.
     * Fills the gas level to the maximum.
     */
    public void fillGas() {
        gasLevel = 100;
    }

    /**
     * Handles the command, `park`.
     * This causes the program to quit.  
     * You can use System.exit(0); to cause a program to quit with status code 0, which indicates a normal graceful exit. 
     * (In case you were wondering, status code 1 represents quitting as a result of an error of some kind).
     */
    public void park() {
      this.parked = true;
      if (parked) {
          System.out.println("You have parked your moped. Thank you for using the Moped Simulator!");
      }
    }

    /**
     * Handles the command, `go to Xi'an Famous Foods`
     * Causes the moped to self-drive, block-by-block, to 8th Ave. and 15th St.
     * Consumes gas with each block, and doesn't move unless there is sufficient gas, as according to the instructions.
     */
    public void goToXianFamousFoods() {
      int[] targetLocation = {15, 8};
      String targetOrientation = "north";
      int[] currentLocation = this.location;
      String currentOrientation = this.orientation;
  
      while (!Arrays.equals(currentLocation, targetLocation) || !currentOrientation.equals(targetOrientation)) {
          switch (currentOrientation) {
              case "north":
                  if (currentLocation[1] > targetLocation[1]) {
                      currentLocation[1]--;
                      System.out.println("Now at " + currentLocation[0] + "th St. and " + currentLocation[1] + "th Ave, facing " + currentOrientation + ".");
                  } else {
                      currentOrientation = "west";
                  }
                  break;
              case "south":
                  if (currentLocation[1] < targetLocation[1]) {
                      currentLocation[1]++;
                      System.out.println("Now at " + currentLocation[0] + "th St. and " + currentLocation[1] + "th Ave, facing " + currentOrientation + ".");
                  } else {
                      currentOrientation = "east";
                  }
                  break;
              case "east":
                  if (currentLocation[0] < targetLocation[0]) {
                      currentLocation[0]++;
                      System.out.println("Now at " + currentLocation[0] + "th St. and " + currentLocation[1] + "th Ave, facing " + currentOrientation + ".");
                  } else {
                      currentOrientation = "north";
                  }
                  break;
              case "west":
                  if (currentLocation[0] > targetLocation[0]) {
                      currentLocation[0]--;
                      System.out.println("Now at " + currentLocation[0] + "th St. and " + currentLocation[1] + "th Ave, facing " + currentOrientation + ".");
                  } else {
                      currentOrientation = "south";
                  }
                  break;
          }
      }
      System.out.println("Now at " + targetLocation[0] + "th St. and " + targetLocation[1] + "th Ave, facing " + targetOrientation + ". We have reached Xi'an Famous Foods.  Enjoy your noodles!");
    }
  
    /**
     * Generates a string, containing a list of all the user commands that the program understands.
     * @return String containing commands that the user can type to control the moped.
     */
    public String getHelp() {
        return "go left = makes moped go left\n" +
        "go right = make moped go right\n" +
        "straight on = go forward\n" +
        "back up = go opposite direction\n" +
        "how we doin'? = check gas tank\n" +
        "fill 'er up = fill gas tank\n" +
        "park = park the car and end program\n" +
        "go to Xi'an Famous Foods = auto-drive to Xi'an Famous Foods on 15th st. and 8th Ave\n" +
        "help = display commands\n";
    }   

    /**
     * Sets the current location of the moped.
     * @param location an int array containing the new location at which to place the moped, in the order {street, avenue}.
     */
    public void setLocation(int[] location) {
        this.location = location;
    }

    /**
     * Gets the current location of the moped.
     * @return The current location of the moped, as an int array in the order {street, avenue}.
     */
    public int[] getLocation() {
        return this.location;
    }

}

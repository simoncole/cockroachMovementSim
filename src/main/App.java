package main;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        //aspirin and water get placed by user
        //get number of simulations to run
        //cockroach starts from the middle of the floor

        //The software shall output the number and direction of moves made by the cockroach in each
        //simulation run, and the average of these simulated moves into the specified output file.
        // The software shall also output the room size, location of the aspirin and glass of water, and
        // number of runs into the output file
        // The software shall format the output file with labels for each data stored to facilitate reading
        // The software shall output upon termination of the simulation the time spent running the
        // simulations.
        Scanner scanner = new Scanner(System.in);

        int floorWidth;
        while(true){
            System.out.print("Hello, enter the width of the floor: ");
            while(!scanner.hasNextInt()) {
                System.out.println("Please enter an integer.");
                scanner.next();
            }
            floorWidth = scanner.nextInt();
            if(verifyDimension(floorWidth)) break;
            else {
                System.out.println("Please enter a valid dimension. Try again");
            }
        }
        
        int floorHeight;
        while(true){
            System.out.print("Now enter the height: ");
            while(!scanner.hasNextInt()) {
                System.out.println("Please enter an integer.");
                scanner.next();
            }
            floorHeight = scanner.nextInt();
            if(verifyDimension(floorHeight)) break;
            else {
                System.out.println("Please enter a valid dimension. Try again");
            }
        }

        int aspirinX;
        while(true) {
            System.out.print("What should be the x coordinate of the aspirin? This should be in the range of 0 to " + (floorWidth - 1) + ": ");
            while(!scanner.hasNextInt()) {
                System.out.println("Please enter an integer.");
                scanner.next();
            }
            aspirinX = scanner.nextInt();
            if(verifyTargetCoord(aspirinX, floorWidth - 1)) break;
            else {
                System.out.println("Please enter a valid coordinate. Try again");
            }

        }

        int aspirinY;
        while(true) {
            System.out.print("And the y? This should be in the range of 0 to " + (floorHeight - 1) + ": ");
            while(!scanner.hasNextInt()) {
                System.out.println("Please enter an integer.");
                scanner.next();
            }
            aspirinY = scanner.nextInt();
            if(verifyTargetCoord(aspirinY, floorHeight - 1)) break;
            else {
                System.out.println("Please enter a valid coordinate. Try again");
            }
        }

        int waterX;
        while(true) {
            System.out.print("What should be the x coordinate of the water? This should be in the range of 0 to " + (floorWidth - 1) + ": ");
            while(!scanner.hasNextInt()) {
                System.out.println("Please enter an integer.");
                scanner.next();
            }
            waterX = scanner.nextInt();
            if(verifyTargetCoord(waterX, floorWidth - 1)) break;
            else {
                System.out.println("Please enter a valid coordinate. Try again");
            }
        }

        int waterY;
        while(true) {
            System.out.print("And the y? This should be in the range of 0 to " + (floorHeight - 1) + ": ");
            while(!scanner.hasNextInt()) {
                System.out.println("Please enter an integer.");
                scanner.next();
            }
            waterY = scanner.nextInt();
            if(verifyTargetCoord(waterY, floorHeight - 1)) break;
            else {
                System.out.println("Please enter a valid coordinate. Try again");
            }
        }

        int numSimulations;
        while(true) {
            System.out.print("How many simulations should be run? ");
            while(!scanner.hasNextInt()) {
                System.out.println("Please enter an integer.");
                scanner.next();
            }
            numSimulations = scanner.nextInt();
            if(verifyNum(numSimulations)) break;
            else {
                System.out.println("Please enter a number greater than or equal to 1.");
            }
        }

        System.out.println("Enter a filename for the output file with any path prepeneded to it: ");
        String filename = scanner.next();

        int roomSize = floorWidth * floorHeight;
        writeToFile(filename, "Room size: " + roomSize);
        String aspirinLocation = "(" + aspirinX + ", " + aspirinY + ")";
        writeToFile(filename, "Aspirin location: " + aspirinLocation);
        String waterLocation = "(" + waterX + ", " + waterY + ")";
        writeToFile(filename, "Water location: " + waterLocation);
        writeToFile(filename, "Number of simulations: " + numSimulations);

        long startTime = System.nanoTime();

        int numSuccesses = 0;
        ArrayList<Integer> numMovesArr = new ArrayList<Integer>();
        for(int i = 0; i < numSimulations; i++) {
            Floor floor = new Floor(floorWidth, floorHeight, new int[]{aspirinX, aspirinY}, new int[]{waterX, waterY});

            if(simulate(floor, filename, numMovesArr)) {
                numSuccesses++;
            }
        }
        int avgMoves = 0;
        for(int numMoves : numMovesArr) {
            avgMoves += numMoves;
        }
        avgMoves = avgMoves / numMovesArr.size();
        writeToFile(filename, "Average number of moves: " + avgMoves);

        System.out.println("Number of successes: " + numSuccesses);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        writeToFile(filename, "Time spent running simulations in milliseconds: " + duration);
        

    }

    public static boolean simulate(Floor floor, String filename, ArrayList<Integer> numMovesArr) {
        int numMoves = 0;
        Random rand = new Random();
        ArrayList<String> directions = new ArrayList<String>();
        while(floor.getUnvisitedTiles().size() > 0) {
            Mover cockroach = floor.getCockroach();
            int roachX = cockroach.getX();
            int roachY = cockroach.getY();

            //for the case the cockroach is initially on aspirin or water
            if(!floor.getAspirin().getFound() && roachX == floor.getAspirin().getX() && roachY == floor.getAspirin().getY()) {
                floor.getAspirin().setFound();
            }
            if(!floor.getAspirin().getFound() && cockroach.getX() == floor.getWater().getX() && cockroach.getY() == floor.getWater().getY()) {
                floor.getWater().setFound();
            }

            Tile currTile = floor.getTile(roachX, roachY);

            ArrayList<String> availableDirections = currTile.getAvailibleDirections();
            int directionIndex = rand.nextInt(availableDirections.size());
            String direction = availableDirections.get(directionIndex);
            //move cockroach
            switch(direction) {
                //top
                case "top":
                    cockroach.decrementY();
                    directions.add("up;");
                    break;
                //right
                case "right":
                    cockroach.incrementX();
                    directions.add("right;");
                    break;
                //bottom
                case "bottom":
                    cockroach.incrementY();
                    directions.add("down;");
                    break;
                //left
                case "left":
                    cockroach.decrementX();
                    directions.add("left;");
                    break;
            }
            numMoves++;

            //visit the new tile
            ArrayList<Integer> newCockRoachCoords = new ArrayList<Integer>();
            newCockRoachCoords.add(cockroach.getX());
            newCockRoachCoords.add(cockroach.getY());
            floor.getUnvisitedTiles().remove(newCockRoachCoords);

            //check if cockroach is on aspirin or water
            if(cockroach.getX() == floor.getAspirin().getX() && cockroach.getY() == floor.getAspirin().getY()) {
                floor.getAspirin().setFound();
            }
            if(cockroach.getX() == floor.getWater().getX() && cockroach.getY() == floor.getWater().getY()) {
                floor.getWater().setFound();
            }

            if(floor.getAspirin().getFound() && floor.getWater().getFound()) {
                String directionsString = "";
                for(String dir : directions) {
                    directionsString += dir;
                }
                writeToFile(filename, "Directions: " + directionsString);
                writeToFile(filename, "Number of moves: " + numMoves);
                numMovesArr.add(numMoves);
                return true;
            }
        }

        return false;
    }

    static boolean verifyNum(int num) {
        if(num >= 1) return true;
        else return false;
    }

    static boolean verifyDimension(int dimension) {
        if(dimension >= 2) {
            return true;
        }
        else {
            return false;
        }
    }

    static boolean verifyTargetCoord(int coord, int maxCoord) {
            if(coord >= 0 && coord <= maxCoord) {
                return true;
            }
            else return false;
    }

    public static void writeToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(content);
            writer.newLine(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


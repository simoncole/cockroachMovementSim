import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        //aspirin and water get placed by user
        //get number of simulations to run
        //cockroach starts from the middle of the floor

        //build starting scenario from user input
        //simulate cockroach moving
        //output results
        Scanner scanner = new Scanner(System.in);

        System.out.print("Hello, enter the width of the floor: ");
        int floorWidth = verifyDimension(scanner);

        System.out.print("Now enter the height: ");
        int floorHeight = verifyDimension(scanner);

        System.out.print("What should be the x coordinate of the aspirin? This should be in the range of 0 to " + (floorWidth - 1) + ": ");
        int aspirinX = verifyTargetCoord(scanner, floorWidth - 1);

        System.out.print("And the y? This should be in the range of 0 to " + (floorHeight - 1) + ": ");
        int aspirinY = verifyTargetCoord(scanner, floorHeight - 1);

        System.out.print("What should be the x coordinate of the glass of water? This should be in the range of 0 to " + (floorWidth - 1) + ": ");
        int waterX = verifyTargetCoord(scanner, floorWidth - 1);

        System.out.print("And the y? This should be in the range of 0 to " + (floorHeight - 1) + ": ");
        int waterY = verifyTargetCoord(scanner, floorHeight - 1);

        System.out.print("How many simulations should be run? ");
        int numSimulations = verifyNum(scanner);

        int numSuccesses = 0;
        for(int i = 0; i < numSimulations; i++) {
            Floor floor = new Floor(floorWidth, floorHeight, new int[]{aspirinX, aspirinY}, new int[]{waterX, waterY});

            if(simulate(floor)) {
                numSuccesses++;
            }
        }
        System.out.println("Number of successes: " + numSuccesses);
        

    }

    public static boolean simulate(Floor floor) {
        int numMoves = 0;
        Random rand = new Random();
        while(floor.getUnvisitedTiles().size() > 0) {
            Mover cockroach = floor.getCockroach();
            int roachX = cockroach.getX();
            int roachY = cockroach.getY();
            // System.out.println("Cockroach coordinates: (" + roachX + ", " + roachY + ")");
            Tile currTile = floor.getTile(roachX, roachY);

            ArrayList<String> availableDirections = currTile.getAvailibleDirections();
            int directionIndex = rand.nextInt(availableDirections.size());
            String direction = availableDirections.get(directionIndex);
            //move cockroach
            switch(direction) {
                //top
                case "top":
                    cockroach.decrementY();
                    break;
                //right
                case "right":
                    cockroach.incrementX();
                    break;
                //bottom
                case "bottom":
                    cockroach.incrementY();
                    break;
                //left
                case "left":
                    cockroach.decrementX();
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
                System.out.println("Moves to find: " + numMoves);
                return true;
            }
        }

        return false;
    }

    static int verifyNum(Scanner scanner) {
        int num;
        while (true) {
            while(!scanner.hasNextInt()) {
                System.out.println("Please enter an integer.");
                scanner.next();
            }
            num = scanner.nextInt();
            if(num >= 1) {
                return num;
            }
            else {
                System.out.println("Please enter a number greater than or equal to 1.");
            }
        }
    }

    static int verifyDimension(Scanner scanner) {
        int dimension;
        while (true) {
            while(!scanner.hasNextInt()) {
                System.out.println("Please enter an integer.");
                scanner.next();
            }
            dimension = scanner.nextInt();
            if(dimension >= 2) {
                return dimension;
            }
            else {
                System.out.println("Please enter a number greater than or equal to 2.");
            }
        }
    }

    static int verifyTargetCoord(Scanner scanner, int maxCoord) {
        int coord;
        while (true) {
            while(!scanner.hasNextInt()) {
                System.out.println("Please enter an integer.");
                scanner.next();
            }
            coord = scanner.nextInt();
            if(coord >= 0 && coord <= maxCoord) {
                return coord;
            }
            else {
                System.out.println("Please enter a number in the range of 0 to " + maxCoord + ".");
            }
        }
    }
}

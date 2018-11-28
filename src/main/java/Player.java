import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static double distanse (int x1, int y1, int x2, int y2)
    {
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }

    public static void main(String args[]) {

        int myId = 0;
        int enemyId = 1;
        Structure base = new Structure();


        ArrayList<Sites> allSites = new ArrayList<Sites>();

        Scanner in = new Scanner(System.in);
        int numSites = in.nextInt();
        for (int i = 0; i < numSites; i++) {
            Sites site = new Sites();
            site.siteId = in.nextInt();
            site.x = in.nextInt();
            site.y = in.nextInt();
            site.radius = in.nextInt();
            allSites.add(site);
        }
        boolean start = true;

        // game loop
        while (true) {
            // structures
            ArrayList<Structure> empty = new ArrayList<Structure>();
            ArrayList<Structure> myMine = new ArrayList<Structure>();
            ArrayList<Structure> enemyMine = new ArrayList<Structure>();
            ArrayList<Structure> myTower = new ArrayList<Structure>();
            ArrayList<Structure> enemyTower = new ArrayList<Structure>();
            ArrayList<Structure> myBarraks = new ArrayList<Structure>();
            ArrayList<Structure> enemyBarraks = new ArrayList<Structure>();

            // units
            ArrayList<Unit> myUnit = new ArrayList<Unit>();
            ArrayList<Unit> enemyUnit = new ArrayList<Unit>();

            int gold = in.nextInt();
            int touchedSite = in.nextInt(); // -1 if none
            for (int i = 0; i < numSites; i++) {
                Structure str = new Structure();
                str.siteId = in.nextInt();
                str.ignore1 = in.nextInt(); // used in future leagues
                str.ignore2 = in.nextInt(); // used in future leagues
                str.structureType = in.nextInt(); // -1 = No structure, 2 = Barracks
                str.owner = in.nextInt(); // -1 = No structure, 0 = Friendly, 1 = Enemy
                str.param1 = in.nextInt();
                str.param2 = in.nextInt();


                if (str.structureType == -1)
                    empty.add(str);
                else if (str.structureType == 0)
                {
                    if (str.owner == myId)
                        myMine.add(str);
                    else if (str.owner == enemyId)
                        enemyMine.add(str);
                }
                else if (str.structureType == 1)
                {
                    if (str.owner == myId)
                        myTower.add(str);
                    else if (str.owner == enemyId)
                        enemyTower.add(str);
                }
                else if (str.structureType == 2)
                {
                    if (str.owner == myId)
                        myBarraks.add(str);
                    else if (str.owner == enemyId)
                        enemyBarraks.add(str);
                }
            }

            int numUnits = in.nextInt();
            for (int i = 0; i < numUnits; i++) {
                Unit unit = new Unit();
                int x = in.nextInt();
                int y = in.nextInt();
                int owner = in.nextInt();
                int unitType = in.nextInt(); // -1 = QUEEN, 0 = KNIGHT, 1 = ARCHER
                int health = in.nextInt();
                unit.x = x;
                unit.y = y;
                unit.owner = owner;
                unit.unitType = unitType;
                unit.health = health;
                if (owner == 0)
                    myUnit.add(unit);
                else if (owner == 1)
                    enemyUnit.add(unit);
            }

            for (Structure str: empty) {
                for (Sites site: allSites)
                {
                    if (str.siteId == site.siteId)
                    {
                        str.x = site.x;
                        str.y = site.y;
                    }
                }
            }
            for (Structure str: myBarraks) {
                for (Sites site: allSites)
                {
                    if (str.siteId == site.siteId)
                    {
                        str.x = site.x;
                        str.y = site.y;
                    }
                }
            }
            for (Structure str: enemyBarraks) {
                for (Sites site: allSites)
                {
                    if (str.siteId == site.siteId)
                    {
                        str.x = site.x;
                        str.y = site.y;
                    }
                }
            }
            for (Structure str: myMine) {
                for (Sites site: allSites)
                {
                    if (str.siteId == site.siteId)
                    {
                        str.x = site.x;
                        str.y = site.y;
                    }
                }
            }
            for (Structure str: enemyMine) {
                for (Sites site: allSites)
                {
                    if (str.siteId == site.siteId)
                    {
                        str.x = site.x;
                        str.y = site.y;
                    }
                }
            }
            for (Structure str: myTower) {
                for (Sites site: allSites)
                {
                    if (str.siteId == site.siteId)
                    {
                        str.x = site.x;
                        str.y = site.y;
                    }
                }
            }
            for (Structure str: enemyTower) {
                for (Sites site: allSites)
                {
                    if (str.siteId == site.siteId)
                    {
                        str.x = site.x;
                        str.y = site.y;
                    }
                }
            }

            int bestSiteId = empty.get(0).siteId;

            if (start) {
                double bestSiteDistanse = distanse(empty.get(0).x, empty.get(0).y, myUnit.get(0).x, myUnit.get(0).y);
                for (int i = 0; i < empty.size(); i++) {
                    double distanseToSite = distanse(empty.get(i).x, empty.get(i).y, myUnit.get(0).x, myUnit.get(0).y);
                    if (distanseToSite < bestSiteDistanse) {
                        bestSiteDistanse = distanseToSite;
                        bestSiteId = empty.get(i).siteId;
                        base = empty.get(i);
                    }
                }
                start = false;
            }
            else
            {
                double bestSiteDistanse = distanse(empty.get(0).x, empty.get(0).y, base.x, base.y);
                for (int i = 0; i < empty.size(); i++) {
                    double distanseToSite = distanse(empty.get(i).x, empty.get(i).y, base.x, base.y);
                    if (distanseToSite < bestSiteDistanse) {
                        bestSiteDistanse = distanseToSite;
                        bestSiteId = empty.get(i).siteId;
                    }
                }
            }

            System.err.println(base.siteId);

            if (myBarraks.size() == 0)
                System.out.println("BUILD " + bestSiteId + " BARRACKS-KNIGHT");
            else if (myBarraks.size() == 1)
                System.out.println("BUILD " + bestSiteId + " BARRACKS-ARCHER");
            else if (myMine.size() < 3)
                System.out.println("BUILD " + bestSiteId + " MINE");
            else
                System.out.println("BUILD " + bestSiteId + " TOWER");
            if (myBarraks.size() == 1)
                System.out.println("TRAIN " + myBarraks.get(0).siteId);
            else if (myBarraks.size() > 1)
                System.out.println("TRAIN " + myBarraks.get(0).siteId + " " + myBarraks.get(1).siteId);
            else
                System.out.println("TRAIN");



        }
    }
}

class Structure
{
    int siteId;
    int ignore1; // used in future leagues
    int ignore2; // used in future leagues
    int structureType; // -1 = No structure, 2 = Barracks
    int owner; // -1 = No structure, 0 = Friendly, 1 = Enemy
    int param1;
    int param2;
    int x, y;
}

class Unit
{
    int x;
    int y;
    int owner;
    int unitType; // -1 = QUEEN, 0 = KNIGHT, 1 = ARCHER
    int health;
}

class Sites
{
    int siteId;
    int x;
    int y;
    int radius;
}
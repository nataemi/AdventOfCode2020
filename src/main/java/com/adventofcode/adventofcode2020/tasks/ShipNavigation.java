package com.adventofcode.adventofcode2020.tasks;

import com.adventofcode.adventofcode2020.tasks.model.Action;
import com.adventofcode.adventofcode2020.tasks.model.Ship;
import com.adventofcode.adventofcode2020.tasks.model.ShipAction;
import com.adventofcode.adventofcode2020.utils.TxtFileReader;

import java.util.ArrayList;
import java.util.List;

public class ShipNavigation {
    public static int navigate(String resource){
        List<String> actionStrings = TxtFileReader.read(resource);
        List<ShipAction> shipActions = convertActionStringsToActions(actionStrings);
        Ship ship = new Ship();
        for(ShipAction shipAction: shipActions){
            ship.move(shipAction.getAction(), shipAction.getSteps());
        }
        return Math.abs(ship.getPosition().x) + Math.abs(ship.getPosition().y);
    }

    private static List<ShipAction> convertActionStringsToActions(List<String> actionStrings) {
        List<ShipAction> shipActions = new ArrayList<>();
        for(String actionString : actionStrings){
            shipActions.add(
                    new ShipAction(
                            Action.fromString(actionString.substring(0,1)), Integer.parseInt(actionString.substring(1))));
        }
        return shipActions;
    }
}

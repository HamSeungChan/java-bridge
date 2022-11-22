package bridge.service;

import bridge.domain.BridgeDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Map {
    private static final String DIVIDING_LINE = "|";
    private static final String PASS = "O";
    private static final String NOT_PASS = "X";
    private static final String BLANK = " ";
    private final List<List<String>> map;

    public Map() {
        map = IntStream.range(0, BridgeDirection.getBridgeDirectionSize())
                .mapToObj(i -> new ArrayList<String>())
                .collect(Collectors.toList());
    }


    public void drawMap(int moveDirectionNumber, boolean canMove) {
        for (int layer = 0; layer < BridgeDirection.getBridgeDirectionSize(); layer++) {
            map.get(layer).add(checkDirection(layer, moveDirectionNumber, canMove));
        }
        for(List<String> layer : map){
            System.out.println(layer);
        }
    }


    private String checkDirection(int LayerIndex, int moveDirectionNumber, boolean canMove) {
        if (LayerIndex == moveDirectionNumber)
            return checkPass(canMove);
        return BLANK;
    }

    private String checkPass(boolean canMove) {
        if (canMove) {
            return PASS;
        }
        return NOT_PASS;
    }

}

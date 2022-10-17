package ladder.domain;

import java.util.ArrayList;
import java.util.List;

public class VerticalLine {

    private final List<LineUnit> lineUnits;

    private final HorizontalPosition horizontalPosition;

    public VerticalLine(List<LineUnit> lineUnits, HorizontalPosition horizontalPosition) {
        this.lineUnits = lineUnits;
        this.horizontalPosition = horizontalPosition;
    }

    public static VerticalLine mapHorizontalLineToVertical(List<HorizontalLine> horizontalLine, int userIndex) {
        List<LineUnit> verticalLine = new ArrayList<>();
        for (int lineIndex = 0; lineIndex < horizontalLine.size(); lineIndex++) {
            HorizontalLine line = horizontalLine.get(lineIndex);
            LineUnit lineUnit = line.getUnits().get(userIndex);
            verticalLine.add(lineUnit);
        }
        return new VerticalLine(verticalLine, new HorizontalPosition(userIndex));
    }

    public void move(LadderPosition ladderPosition, LadderLength ladderLength) {
        if (ladderPosition.isArrived(ladderLength)) {
            return;
        }
        LineUnit unit = this.lineUnits.get(ladderPosition.length());
        ladderPosition.down();
        if (unit.hasPrevious()) {
            ladderPosition.previous();
            return;
        }
        if (unit.hasNext()) {
            ladderPosition.next();
            return;
        }
        move(ladderPosition, ladderLength);
    }


    public List<LineUnit> getLineUnits() {
        return lineUnits;
    }

    public boolean isSamePosition(HorizontalPosition horizontalPosition) {
        return this.horizontalPosition.equals(horizontalPosition);
    }
}

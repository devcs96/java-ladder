package ladder.domain;

public class LadderPosition {

    private final LadderLength ladderLength = new LadderLength(0);

    private int horizontalPosition;

    public LadderPosition(int horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }

    public void down() {
        this.ladderLength.increase();
    }

    public void next() {
        this.horizontalPosition++;
    }

    public void previous() {
        this.horizontalPosition--;
    }

    public boolean isArrived(LadderLength ladderLength) {
        return this.ladderLength.compareTo(ladderLength) >= 0;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    public int length() {
        return ladderLength.getLength();
    }

}
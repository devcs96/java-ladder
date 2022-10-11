package ladder.ui;

import ladder.model.*;

public abstract class OutputView {

    private static final int LADDER_USER_INTERVAL = 6;
    private static final String LADDER_WITH_NO_SPACE = " ";
    private static final String HORIZONTAL_UNIT = "-";
    private static final String VERTICAL_UNIT = "|";

    public static void printLadder(Users users, Ladder ladder) {
        printName(users);
        System.out.println();
        printLine(ladder , users);
    }

    private static void printName(Users users) {
        for (int userIndex = 0; userIndex < users.size(); userIndex++) {
            printSingleName(users, userIndex);
        }
    }

    private static void printSingleName(Users users, int userIndex) {
        String username = getNameOfUser(users.getUsers().get(userIndex));
        if (userIndex == 0) {
            System.out.print(username);
            return;
        }
        System.out.print(addDelimiter(LADDER_USER_INTERVAL - username.length(), LADDER_WITH_NO_SPACE) + username);
    }

    private static void printLine(Ladder ladder ,Users users) {
        for (int lineIndex = 0; lineIndex < ladder.getLadderLength().getLength() ;  lineIndex++) {
            printSingleLine(ladder,users,lineIndex);
            System.out.println();
        }
    }

    private static void printSingleLine(Ladder ladder ,Users users , int lineIndex) {
        for (int userIndex = 0; userIndex < users.size(); userIndex++) {
            StringBuilder stringBuilder = new StringBuilder();
            User user = users.getUsers().get(userIndex);
            String username = getNameOfUser(user);
            LineUnit unit = ladder.getLines().get(userIndex).getLineUnits().get(lineIndex);
            appendSingleLine(userIndex, stringBuilder, username, unit);
            System.out.print(stringBuilder);
        }
    }

    private static void appendSingleLine(int userIndex, StringBuilder stringBuilder, String username, LineUnit unit) {
        if (userIndex == 0) {
            stringBuilder.append(addDelimiter(username.length(), LADDER_WITH_NO_SPACE) + VERTICAL_UNIT);
            return;
        }
        if (unit.hasPrevious()) {
            stringBuilder.append(addDelimiter(LADDER_USER_INTERVAL - 1, HORIZONTAL_UNIT) + VERTICAL_UNIT);
            return;
        }
        stringBuilder.append(addDelimiter(LADDER_USER_INTERVAL - 1, LADDER_WITH_NO_SPACE) + VERTICAL_UNIT);
    }

    private static String addDelimiter(int length, String delimiter) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(delimiter);
        }
        return stringBuilder.toString();
    }

    private static String getNameOfUser(User user) {
        return user.getName().getName();
    }
}

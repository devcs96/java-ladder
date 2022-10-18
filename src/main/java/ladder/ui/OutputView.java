package ladder.ui;

import ladder.domain.*;
import ladder.dto.ResultDto;

import java.util.List;
import java.util.stream.IntStream;

public abstract class OutputView {

    private static final String LADDER_WITH_NO_SPACE = " ";
    private static final String HORIZONTAL_UNIT = "-";
    private static final String VERTICAL_UNIT = "|";

    private static final String PLAY_RESULT_MSG = "실행 결과";

    private static final int ADDITIONAL_SPACE = 1;

    public static void printLadder(Users users, Ladder ladder, LadderResult ladderResult) {
        int maxInterval = findMaxInterval(users, ladderResult);
        printName(users, maxInterval);
        System.out.println();
        printLine(ladder, maxInterval);
        printResult(ladderResult, maxInterval);
        System.out.println();
    }

    private static int findMaxInterval(Users users, LadderResult ladderResult) {
        List<User> user = users.getUsers();
        List<String> result = ladderResult.getResult();
        int maxInterval = -1;
        for (int i = 0; i < users.size(); i++) {
            maxInterval = Math.max(user.get(i).getName().maxLength(result.get(i)), maxInterval);
        }
        return maxInterval + ADDITIONAL_SPACE;
    }

    private static void printName(Users users, int maxInterval) {
        users.getUsers().stream()
                .map(OutputView::getNameOfUser)
                .forEach((username) -> System.out.print(addDelimiter(maxInterval - username.length(), LADDER_WITH_NO_SPACE) + username));
    }

    private static void printLine(Ladder ladder, int maxInterval) {
        List<VerticalLine> lines = ladder.getLines();
        for (int lengthIndex = 0; lengthIndex < ladder.getLadderLength().getLength(); lengthIndex++) {
            printSingleLine(maxInterval, lines, lengthIndex);
            System.out.println();
        }
    }

    private static void printSingleLine(int maxInterval, List<VerticalLine> lines, int lengthIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            LineUnit unit = lines.get(lineIndex).getLineUnits().get(lengthIndex);
            appendSingleLine(maxInterval, stringBuilder, unit);
        }
        System.out.print(stringBuilder);
    }

    private static void appendSingleLine(int maxInterval, StringBuilder stringBuilder, LineUnit unit) {
        if (unit.hasPrevious()) {
            stringBuilder.append(addDelimiter(maxInterval - 1, HORIZONTAL_UNIT) + VERTICAL_UNIT);
            return;
        }
        stringBuilder.append(addDelimiter(maxInterval - 1, LADDER_WITH_NO_SPACE) + VERTICAL_UNIT);
    }

    private static void printResult(LadderResult result, int maxInterval) {
        result.getResult()
                .forEach((username) -> System.out.print(addDelimiter(maxInterval - username.length(), LADDER_WITH_NO_SPACE) + username));
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

    public static void printResult(ResultDto resultDtos) {
        System.out.println(PLAY_RESULT_MSG);
        List<String> results = resultDtos.getResults();
        List<User> users = resultDtos.getUsers();
        IntStream.range(0, users.size())
                .forEach((idx) -> System.out.printf("%s : %s \n", getNameOfUser(users.get(idx)), results.get(idx)));
    }
}

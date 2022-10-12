package ladder.ui;

import ladder.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public abstract class OutputView {

    private static final String LADDER_WITH_NO_SPACE = " ";
    private static final String HORIZONTAL_UNIT = "-";
    private static final String VERTICAL_UNIT = "|";

    private static final String PLAY_RESULT_MSG = "실행 결과";
    private static final int ADDITIONAL_SPACE = 1;

    public static void printResult(UserName userName, Users users, List<String> result) {
        System.out.println(PLAY_RESULT_MSG);
        if (userName.isAllUser()){
            for(int  i = 0 ; i <result.size() ; i++){
                System.out.printf( "%s : %s \n", getNameOfUser(users.getUsers().get(i)) , result.get(i));
            }
            return;
        }
        System.out.printf( "%s : %s \n", userName.getName() , result.get(0));
    }
    public static void printLadder(Users users, Ladder ladder , LadderResult ladderResult) {

        int maxInterval = findMaxInterval(users, ladderResult);
        printName(users , maxInterval);
        System.out.println();
        printLine(ladder , maxInterval);
        printResult(ladderResult , maxInterval);
    }

    private static void printResult(LadderResult result , int maxInterval){
        result.getResult().stream().forEach((e)->print(e,maxInterval));
    }
    private static void printName(Users users , int maxInterval) {
        users.getUsers().stream().forEach((user)->print(getNameOfUser(user),maxInterval));
    }

    private static void printLine(Ladder ladder, int maxInterval){
        List<VerticalLine> lines = ladder.getLines();
        for (int i = 0 ; i < ladder.getLadderLength().getLength() ; i++){
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0 ; j < ladder.getLines().size() ; j++){
                LineUnit unit = lines.get(j).getLineUnits().get(i);
                if (unit.hasPrevious()) {
                    stringBuilder.append(addDelimiter(maxInterval - 1, HORIZONTAL_UNIT) + VERTICAL_UNIT);
                    continue;
                }
                stringBuilder.append(addDelimiter(maxInterval - 1, LADDER_WITH_NO_SPACE) + VERTICAL_UNIT);
            }
            System.out.println(stringBuilder);
        }
    }


    private static int findMaxInterval(Users users, LadderResult ladderResult) {
        validate(users, ladderResult);
        List<User> user = users.getUsers();
        List<String> result = ladderResult.getResult();
        int maxInterval = -1;
        for (int i = 0 ; i < users.size() ; i++){
            maxInterval = Math.max(user.get(i).getName().maxLength(result.get(i)) , maxInterval);
        }
        return maxInterval + ADDITIONAL_SPACE;
    }

    private static void validate(Users users, LadderResult ladderResult) {
        if (users.size() != ladderResult.size()){
            throw new IllegalArgumentException("결과개수는 사용자 개수와 일치해야 합니다.");
        }
    }


    private static String addDelimiter(int length, String delimiter) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(delimiter);
        }
        return stringBuilder.toString();
    }

    private static void print(String input , int maxInterval){
        System.out.print(addDelimiter(maxInterval - input.length(),LADDER_WITH_NO_SPACE) + input);
    }

    private static String getNameOfUser(User user) {
        return user.getName().getName();
    }



}

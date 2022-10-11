package ladder.model;

import java.util.List;

public class LadderResult {

    private final List<String> result;

    public LadderResult(List<String> result) throws IllegalArgumentException{
        result.stream().forEach(this::validate);
        this.result = result;
    }

    public String getResult(LadderPosition position){
        return result.get(position.getHorizontalPosition());
    }

    private void validate(String result){
        if (result == null || result.isBlank()){
            throw new IllegalArgumentException("게임 결과가 비어있습니다.");
        }
    }


}

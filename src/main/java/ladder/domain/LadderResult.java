package ladder.domain;

import java.util.List;
import java.util.stream.Collectors;

public class LadderResult {

    private final List<String> result;

    public LadderResult(List<String> result, int userNumber) throws IllegalArgumentException {
        if (result.size() != userNumber) {
            throw new IllegalArgumentException("게임 결과 개수가 사용자 개수와 다릅니다.");
        }
        result.stream().forEach(this::validate);
        this.result = result;
    }

    public List<String> result(List<LadderPosition> positions) {
        return positions.stream()
                .map((position) -> this.result.get(position.getHorizontalPosition()))
                .collect(Collectors.toList());
    }

    private void validate(String result) {
        if (result == null || result.isBlank()) {
            throw new IllegalArgumentException("게임 결과가 비어있습니다.");
        }
    }

    public List<String> getResult() {
        return result;
    }

    public int size() {
        return this.result.size();
    }

}

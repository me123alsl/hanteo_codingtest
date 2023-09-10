package sam.song.hanteo_codingtest.struct;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class CategoryIdManager {
    private Long nextIdx = 1L;

    public Long getNextIdx() {
        return nextIdx++;
    }

    public static CategoryIdManager getInstance() {
        return CategoryIdManagerHolder.INSTANCE;
    }

    private static class CategoryIdManagerHolder {
        private static final CategoryIdManager INSTANCE = new CategoryIdManager();
    }

}

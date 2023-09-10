package sam.song.hanteo_codingtest.struct;

import sam.song.hanteo_codingtest.constant.CategoryType;

public class BoardIdManager {
    private Long nextIdx = 1L;
    private Long AnonymousIdx = -1L;

    public Long getNextIdx(CategoryType categoryType) {
        if(categoryType == CategoryType.ANONYMOUS && AnonymousIdx == -1L)
            AnonymousIdx = nextIdx++;
        if(categoryType == CategoryType.ANONYMOUS)
            return AnonymousIdx;
        else
            return nextIdx++;
    }

    public static BoardIdManager getInstance() {
        return BoardIdManagerHolder.INSTANCE;
    }

    private static class BoardIdManagerHolder {
        private static final BoardIdManager INSTANCE = new BoardIdManager();
    }
}

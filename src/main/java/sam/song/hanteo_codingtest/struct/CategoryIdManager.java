package sam.song.hanteo_codingtest.struct;

public class CategoryIdManager {
    private Long nextIdx = 0L;

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

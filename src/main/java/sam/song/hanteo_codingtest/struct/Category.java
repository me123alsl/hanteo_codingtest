package sam.song.hanteo_codingtest.struct;


import lombok.Builder;
import lombok.Getter;
import sam.song.hanteo_codingtest.constant.CategoryType;

@Getter
public class Category {

    private Long categoryId = -1L;
    private String categoryName;
    private CategoryType categoryType;

    private Long boardId;
    private Long parentId;

    @Builder
    public Category(String categoryName, CategoryType categoryType, Long parentId) {
        this.categoryId = CategoryIdManager.getInstance().getNextIdx();
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        if(categoryType != CategoryType.CATEGORY)
            this.boardId = BoardIdManager.getInstance().getNextIdx(categoryType);
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                ", boardId=" + boardId +
                ", parentId=" + parentId +
                '}';
    }
}

package sam.song.hanteo_codingtest.struct;


import lombok.Getter;
import sam.song.hanteo_codingtest.constant.CategoryType;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class CategoryManager {

    private static class CategoryManagerHolder {
        private static final CategoryManager INSTANCE = new CategoryManager();
    }

    public static CategoryManager getInstance() {
        return CategoryManagerHolder.INSTANCE;
    }

    public CategoryManager() {
        Category category = Category.builder()
                .categoryName("root")
                .categoryType(CategoryType.CATEGORY)
                .parentId(null)
                .build();
        addCategory(category);
    }


    private Map<Long, Category> categoryMap = new HashMap<>();
    private Map<Long, List<Long>> categoryRelationMap = new HashMap<>();

    public void addCategory(Category category) {
        categoryMap.put(category.getCategoryId(), category);
        if (categoryRelationMap.containsKey(category.getParentId())) {
            categoryRelationMap.get(category.getParentId()).add(category.getCategoryId());
        } else {
            List<Long> list = new ArrayList<>();
            list.add(category.getCategoryId());
            categoryRelationMap.put(category.getParentId(), list);
        }
    }

    public List<Category> findByName(String categoryName) {
        List<Category> result = new ArrayList<>();
        List<Category> findName = categoryMap.values().stream()
                .filter(category -> category.getCategoryName().equals(categoryName))
                .collect(Collectors.toList());

        Queue<Long> child = new LinkedList<>();
        child.addAll(findName.stream().map(Category::getCategoryId).collect(Collectors.toList()));
        findNextChild(child, result);

        return result;
    }

    public List<Category> findById(Long categoryId) {
        List<Category> result = new ArrayList<>();
        Queue<Long> child = new LinkedList<>();
        child.addAll(categoryRelationMap.get(categoryId));
        findNextChild(child, result);

        return result;
    }

    private void findNextChild(Queue<Long> child, List<Category> result) {

        while (!child.isEmpty()) {
            Long categoryId = child.poll();
            result.add(categoryMap.get(categoryId));
            if (categoryRelationMap.containsKey(categoryId))
                child.addAll(categoryRelationMap.get(categoryId));
        }
    }
}

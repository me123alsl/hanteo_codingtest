package sam.song.hanteo_codingtest.struct;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import sam.song.hanteo_codingtest.constant.CategoryType;
import sam.song.hanteo_codingtest.exception.CategoryNotFoundException;

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
        if(category.getParentId() != null && !categoryMap.containsKey(category.getParentId()))
            throw new CategoryNotFoundException("상위 카테고리가 존재하지 않습니다.");
        categoryMap.put(category.getCategoryId(), category);
        if (categoryRelationMap.containsKey(category.getParentId())) {
            categoryRelationMap.get(category.getParentId()).add(category.getCategoryId());
        } else {
            List<Long> list = new ArrayList<>();
            list.add(category.getCategoryId());
            categoryRelationMap.put(category.getParentId(), list);
        }
    }

    public List<Category> findByName(String categoryName) throws JsonProcessingException {
        List<Category> result = new ArrayList<>();
        List<Long> findCategoryIds = categoryMap.entrySet().stream()
                .filter(entry -> entry.getValue().getCategoryName().equals(categoryName))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Queue<Long> child = new LinkedList<>();
        child.addAll(findCategoryIds);
        findNextChild(child, result);

        return result;
    }

    public List<Category> findById(Long categoryId) throws JsonProcessingException {
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

    public static String ofJson(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json 변환에 실패하였습니다.");
        }
    }
}

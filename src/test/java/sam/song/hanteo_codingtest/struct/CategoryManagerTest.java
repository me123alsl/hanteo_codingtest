package sam.song.hanteo_codingtest.struct;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sam.song.hanteo_codingtest.constant.CategoryType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryManagerTest {

    CategoryManager categoryManager = CategoryManager.getInstance();

    @BeforeEach
    @DisplayName("카테고리 초기화")
    void setup() {
        categoryManager.getCategoryMap().forEach((k, v) -> {
            if (k != 0L) System.out.println(v);
        });
        categoryManager.addCategory(new Category("남자", CategoryType.CATEGORY, 0L));
        categoryManager.addCategory(new Category("여자", CategoryType.CATEGORY, 0L));
        categoryManager.addCategory(new Category("엑소", CategoryType.CATEGORY, 1L));
        categoryManager.addCategory(new Category("방탄소년단", CategoryType.CATEGORY, 1L));
        categoryManager.addCategory(new Category("블랙핑크", CategoryType.CATEGORY, 2L));
        categoryManager.addCategory(new Category("공지사항", CategoryType.NOTICE, 3L));
        categoryManager.addCategory(new Category("첸", CategoryType.BOARD, 3L));
        categoryManager.addCategory(new Category("백현", CategoryType.BOARD, 3L));
        categoryManager.addCategory(new Category("시우민", CategoryType.BOARD, 3L));
        categoryManager.addCategory(new Category("공지사항", CategoryType.NOTICE, 4L));
        categoryManager.addCategory(new Category("익명게시판", CategoryType.ANONYMOUS, 4L));
        categoryManager.addCategory(new Category("뷔", CategoryType.BOARD, 4L));
        categoryManager.addCategory(new Category("공지사항", CategoryType.NOTICE, 5L));
        categoryManager.addCategory(new Category("익명게시판", CategoryType.ANONYMOUS, 5L));
    }

    @Test
    @DisplayName("카테고리 추가 테스트")
    void addCategory() {
        categoryManager.getCategoryMap().forEach((k, v) -> {
            if (k != 0L) System.out.println(v);
        });
    }

    @Test
    @DisplayName("카테고리 ID로 검색")
    void getCategoryById() {
        List<Category> result = new ArrayList<>();
        try {
            result = categoryManager.findById(1L);
            result.forEach(System.out::println);
        } catch (JsonProcessingException e){
            System.out.println("e = " + e);
        }
        Assertions.assertEquals(10, result.size());
    }

    @Test
    @DisplayName("카테고리 이름으로 검색")
    void getCategoryByName()  {
        List<Category> result = new ArrayList<>();
        try {
            result = categoryManager.findByName("남자");
            result.forEach(System.out::println);
        } catch (JsonProcessingException e) {
            System.out.println("e = " + e);
        }

        Assertions.assertEquals(10, result.size());
    }

    @Test
    @DisplayName("카테고리 ID로 검색 - JSON")
    void CategoryByIdWithJson() {
        String resultString = "";
        try {
            List<Category> result = categoryManager.findById(1L);
            resultString = CategoryManager.ofJson(result);
            System.out.println("resultString = " + resultString);
        } catch (JsonProcessingException e){
            System.out.println("e = " + e);
        }
        //
    }

    @Test
    @DisplayName("카테고리 이름으로 검색 - JSON")
    void CategoryByNameWithJson() {
        String resultString = "";
        try {
            List<Category> result = categoryManager.findByName("남자");
            resultString = CategoryManager.ofJson(result);
            System.out.println("resultString = " + resultString);
        } catch (JsonProcessingException e) {
            System.out.println("e = " + e);
        }

    }

}
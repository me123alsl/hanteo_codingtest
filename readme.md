# 한터 글로벌 코딩과제

---

## 1번 문제

### 1.1. 문제 설명


#### 게시판 조건
1. 게시판은 여러 형태로 카테고리 구분되야함
2. 카테고리들간 관계 데이터를 parent_id, child_id로 관리

#### 응답 조건
1. 카테고리 명  또는 식별자로 검색 시, 하위 카테고리를 모두 담고 있어야함.
2. Json Text로 응답될 수 있어야함. (Json 구조로 변환이 가능해야함)

| 카테고리 | 카테고리 | 카테고리  | 게시판 번호 |
|:----:|:----:|:-----:|:------:|
|  남자  |  엑소  | `공지사항`  |   `1`    |
| | |   첸   |   2    |
| | |  백현   |   3    |
| | |  시우민  |   4    |
| | 방탄소년단 | `공지사항`  |   `5`    |
| | | `익명게시판` |  `6`   |
| | |   뷔   |   7    |
| 여자 | 블랙핑크 | `공지사항`  |   `8`    |
| | | `익명게시판` |   `6`    |
| | |  로제   |   9    |

### 1.2. 문제 해결 방법

* 카테고리 분류를 정의 한다. (번호 부여시 `ANONYMOUS` 여부에 따른 부여)
  * `CATEGORY` - 분류
  * `BOARD` - 게시판 
  * `ANONYMOUS` - 익명게시판 
  * `NOTICE` - 공지사항

* 카테고리 분류에 따라 게시판 번호를 정의 한다.
  * ANNOYMOUS 가 생성될 경우, ANONYMOUS 게시판 번호 고정

* 결과 리턴
  * 기본 findBy[Id/Name] 사용시, ArrayList로 반환
  * Json 형태로 반환하는 함수 구현

### 1.3 클래스 설명

|       클래스명       |      설명      |
|:----------------:|:------------:|
|    `Category`    | 카테고리 분류를 정의  |
| `BoardIdManager` | 게시판 번호 관리 객체 |
| `CategoryIdManager` | 카테고리 번호 관리 객체 |
| `CategoryManager` | 카테고리 관리 객체 |

### 1.4. 실행 결과

* ID 검색 결과 ("1" 입력)
```jsonp
[{"categoryId":1,"categoryName":"남자","categoryType":"CATEGORY","boardId":null,"parentId":0},{"categoryId":3,"categoryName":"엑소","categoryType":"CATEGORY","boardId":null,"parentId":1},{"categoryId":4,"categoryName":"방탄소년단","categoryType":"CATEGORY","boardId":null,"parentId":1},{"categoryId":6,"categoryName":"공지사항","categoryType":"NOTICE","boardId":1,"parentId":3},{"categoryId":7,"categoryName":"첸","categoryType":"BOARD","boardId":2,"parentId":3},{"categoryId":8,"categoryName":"백현","categoryType":"BOARD","boardId":3,"parentId":3},{"categoryId":9,"categoryName":"시우민","categoryType":"BOARD","boardId":4,"parentId":3},{"categoryId":10,"categoryName":"공지사항","categoryType":"NOTICE","boardId":5,"parentId":4},{"categoryId":11,"categoryName":"익명게시판","categoryType":"ANONYMOUS","boardId":6,"parentId":4},{"categoryId":12,"categoryName":"뷔","categoryType":"BOARD","boardId":7,"parentId":4}]
```

---

## 2번 문제

### 2.1. 문제 설명
각 각 다른 종류의 통화를 나타내는 N 크기의 coin[ ],  
조합으로 만들어야 할 값 sum 일 때,  
coin[]의 다양한 조합을 사용하여 합계를 만드는 방법의 수

### 2.2 풀이 과정

`동적 계획법`을 통한 목표 별 방법 수 구하기

1. sum 만큼의 배열을 생성한다. `kindOfSum` (0 ~ sum)
2. coin[]의 값을 하나씩 더해가며, 해당 값의 배열에 방법 수를 더한다.
3. kindOfSum의 sum번째 값이 sum을 만드는 방법의 수가 된다.
``` text
coins[i]=1, j=1 kindOfSum=[1 0 0 0 0 ]
coins[i]=1, j=2 kindOfSum=[1 1 0 0 0 ]
coins[i]=1, j=3 kindOfSum=[1 1 1 0 0 ]
coins[i]=1, j=4 kindOfSum=[1 1 1 1 0 ]
coins[i]=2, j=2 kindOfSum=[1 1 1 1 1 ]
coins[i]=2, j=3 kindOfSum=[1 1 2 1 1 ]
coins[i]=2, j=4 kindOfSum=[1 1 2 2 1 ]
coins[i]=3, j=3 kindOfSum=[1 1 2 2 3 ]
coins[i]=3, j=4 kindOfSum=[1 1 2 3 3 ] 
```

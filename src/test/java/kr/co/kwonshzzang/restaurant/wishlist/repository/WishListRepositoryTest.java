package kr.co.kwonshzzang.restaurant.wishlist.repository;

import kr.co.kwonshzzang.restaurant.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WishListRepositoryTest {
    @Autowired
    private WishListRepository wishListRepository;

    private WishListEntity create() {
        return WishListEntity.builder()
                .title("title")
                .category("category")
                .address("address")
                .roadAddress("road address")
                .homePageLink("")
                .imageLink("")
                .isVisit(false)
                .visitCount(0)
                .lastVisitDateTime(null)
                .build();
    }

    @Test
    void saveTest() {
        var wishListEntity = create();
        var expected= wishListRepository.save(wishListEntity);

        assertNotNull(expected);
        assertEquals(1L, expected.getId());

    }

    void findByIdTest() {

    }

    void deleteTest() {

    }

    void listAllTest() {

    }

}
package kr.co.kwonshzzang.restaurant.wishlist.repository;

import kr.co.kwonshzzang.restaurant.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WishListRepositoryTests {
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

    @Test
    void updateTest() {
        var wishListEntity = create();
        var expected= wishListRepository.save(wishListEntity);

        expected.setTitle("Update Test");
        var savedEntity = wishListRepository.save(expected);

        assertEquals("Update Test", savedEntity.getTitle());
        assertEquals(1L, wishListRepository.findAll().size());
    }

    @Test
    void findByIdTest() {
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        var expected = wishListRepository.findById(1L);
        assertTrue(expected.isPresent());
        assertEquals(1L, expected.get().getId());
    }

    @Test
    void deleteTest() {
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        wishListRepository.deleteById(1L);
        int count = wishListRepository.findAll().size();

        assertEquals(0, count);
    }

    @Test
    void findAllTest() {
        var wishListEntity1 = create();
        wishListRepository.save(wishListEntity1);

        var wishListEntity2 = create();
        wishListRepository.save(wishListEntity2);

        assertEquals(2, wishListRepository.findAll().size());
    }

}
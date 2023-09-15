package kr.co.kwonshzzang.restaurant.wishlist.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WishListServiceTests {
    @Autowired
    private WishListService wishListService;

    @Test
    public void searchTest() {
        var result =  wishListService.search("장수갈비");
        System.out.println(result);
        assertNotNull(result);
    }
}
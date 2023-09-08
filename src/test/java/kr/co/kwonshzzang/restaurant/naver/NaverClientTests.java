package kr.co.kwonshzzang.restaurant.naver;

import kr.co.kwonshzzang.restaurant.naver.dto.SearchImageReq;
import kr.co.kwonshzzang.restaurant.naver.dto.SearchImageRes;
import kr.co.kwonshzzang.restaurant.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NaverClientTests {
    @Autowired
    private NaverClient naverClient;


    @Test
    void seachLocalTest() {
        var search = new SearchLocalReq();
        search.setQuery("갈비집");

        var result = naverClient.searchLocal(search);

        System.out.println(result);
    }

    @Test
    void searchImageTest() {
        var search = new SearchImageReq();
        search.setQuery("클리앙");

        var result = naverClient.searchImage(search);

        System.out.println(result);
    }

}
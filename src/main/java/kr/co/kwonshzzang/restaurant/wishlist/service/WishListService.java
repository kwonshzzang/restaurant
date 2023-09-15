package kr.co.kwonshzzang.restaurant.wishlist.service;

import kr.co.kwonshzzang.restaurant.naver.NaverClient;
import kr.co.kwonshzzang.restaurant.naver.dto.SearchImageReq;
import kr.co.kwonshzzang.restaurant.naver.dto.SearchLocalReq;
import kr.co.kwonshzzang.restaurant.wishlist.dto.WishListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final NaverClient naverClient;

    public WishListDto search(String query) {

        //1. 지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if(!searchLocalRes.getItems().isEmpty()) {
            //2. 이미지 검색
            var localItem = searchLocalRes.getItems().stream().findFirst().get();

//            var imageQuery = localItem.getTitle().replaceAll("<[^]*]", "");
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(query);

            var searchImageRes = naverClient.searchImage(searchImageReq);
            if(!searchImageRes.getItems().isEmpty()) {
                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                //3. 결과물 리턴
             return  new WishListDto(0L, localItem.getTitle(), localItem.getCategory(),
                                localItem.getAddress(), localItem.getRoadAddress(), imageItem.getLink(), localItem.getLink());
            }
        }

        return new WishListDto();
    }


}

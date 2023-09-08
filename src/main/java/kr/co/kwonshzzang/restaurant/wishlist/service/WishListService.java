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
        //지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes = naverClient.searchLocal(searchLocalReq);
        if(searchLocalRes.getTotal() > 0) {
            var localItem = searchLocalRes.getItems().stream().findFirst().get();
            System.out.println(localItem);

            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>", "");
            System.out.println(imageQuery);
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);
            //이미지검색
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal() > 0) {
                //결과리턴
                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                var result = new WishListDto(1L,
                        localItem.getTitle(), localItem.getCategory(),
                        localItem.getAddress(), localItem.getRoadAddress(),
                        localItem.getLink(), imageItem.getLink());

                return result;
            }

        }
        return null;
    }


}

package kr.co.kwonshzzang.restaurant.wishlist.service;

import kr.co.kwonshzzang.restaurant.naver.NaverClient;
import kr.co.kwonshzzang.restaurant.naver.dto.SearchImageReq;
import kr.co.kwonshzzang.restaurant.naver.dto.SearchLocalReq;
import kr.co.kwonshzzang.restaurant.wishlist.dto.WishListDto;
import kr.co.kwonshzzang.restaurant.wishlist.entity.WishListEntity;
import kr.co.kwonshzzang.restaurant.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

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
                return WishListDto.builder()
                        .title(localItem.getTitle())
                        .category(localItem.getCategory())
                        .address(localItem.getAddress())
                        .roadAddress(localItem.getRoadAddress())
                        .homePageLink(imageItem.getLink())
                        .imageLink(localItem.getLink())
                        .build();

            }
        }

        return new WishListDto();
    }

    public WishListDto add(WishListDto wishListDto) {
        var wishListEntity = wishListRepository.save(toWishListEntity(wishListDto));
        return toWishListDto(wishListEntity);
    }

    public List<WishListDto> findAll() {
        return wishListRepository.findAll().stream().map(this::toWishListDto).toList();
    }

    public void delete(Long id) {
        wishListRepository.deleteById(id);
    }

    public WishListDto addVisit(Long id) {
        var wishListEntity = wishListRepository.findById(id);
        if(wishListEntity.isPresent()) {
            var item = wishListEntity.get();

            item.setVisit(true);
            item.setVisitCount(item.getVisitCount()+1);
            item.setLastVisitDatetime(LocalDateTime.now());
            return toWishListDto(wishListRepository.save(item));
        }
        throw  new UnsupportedOperationException("방문할 아이템이 없습니다.");
    }

    private WishListDto toWishListDto(WishListEntity wishListEntity)  {
        return WishListDto.builder()
                .id(wishListEntity.getId())
                .title(wishListEntity.getTitle())
                .category(wishListEntity.getCategory())
                .address(wishListEntity.getAddress())
                .roadAddress(wishListEntity.getRoadAddress())
                .homePageLink(wishListEntity.getHomePageLink())
                .imageLink(wishListEntity.getImageLink())
                .isVisit(wishListEntity.isVisit())
                .visitCount(wishListEntity.getVisitCount())
                .lastVisitDatetime(wishListEntity.getLastVisitDatetime())
                .build();
    }

    private WishListEntity toWishListEntity(WishListDto wishListDto)  {
        return WishListEntity.builder()
                .title(wishListDto.getTitle())
                .category(wishListDto.getCategory())
                .address(wishListDto.getAddress())
                .roadAddress(wishListDto.getRoadAddress())
                .homePageLink(wishListDto.getHomePageLink())
                .imageLink(wishListDto.getImageLink())
                .isVisit(wishListDto.isVisit())
                .visitCount(wishListDto.getVisitCount())
                .lastVisitDatetime(wishListDto.getLastVisitDatetime())
                .build();
    }




}

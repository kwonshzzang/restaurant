package kr.co.kwonshzzang.restaurant.wishlist.entity;

import kr.co.kwonshzzang.restaurant.db.MemoryDbEntity;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WishListEntity extends MemoryDbEntity {
    private String title;               //음식명, 장소명
    private String category;            //카테고리
    private String address;             //주소
    private String roadAddress;         //도로명 주소
    private String homePageLink;        //홈페이지 주소
    private String imageLink;           //음식, 가게 이미지 주소
    private boolean isVisit;            //방문여부
    private int visitCount;             //방문 카운트
    private LocalDateTime lastVisitDatetime; //마지막 방문시간
}

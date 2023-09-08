package kr.co.kwonshzzang.restaurant.wishlist.dto;

public record WishListDto(
        Long index,
        String title,
        String category,
        String address,
        String roadAddress,
        String homePageLink,
        String imageLink

) {
}

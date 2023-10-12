package kr.co.kwonshzzang.restaurant.controller;

import kr.co.kwonshzzang.restaurant.wishlist.dto.WishListDto;
import kr.co.kwonshzzang.restaurant.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class ApiController {
    private final WishListService wishListService;

    @GetMapping("/search")
    public WishListDto search(@RequestParam(value = "query") String query) {
        return wishListService.search(query);
    }

    @PostMapping
    public WishListDto add(@RequestBody WishListDto wishListDto) {
        log.info("{}", wishListDto);
        return wishListService.add(wishListDto);
    }

    @GetMapping("/all")
    public List<WishListDto> findAll() {
        return wishListService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        wishListService.delete(id);
    }
    @PutMapping("/{id}")

    public WishListDto addVisit(@PathVariable(value = "id") Long id) {
        return wishListService.addVisit(id);
    }

}

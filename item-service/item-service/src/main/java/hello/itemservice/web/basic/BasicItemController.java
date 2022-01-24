package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // 롬복 제공 어노테이션 중 하나. 생성자 주입 방식으로 DI 구현
public class BasicItemController {

    private final ItemRepository itemRepository;


    // 상품 조회
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    // 상품 상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    // 상품 등록 창 나오는거
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    // 상품 등록 버튼(Post)를 누른 다음의 동작 - @RequestParam 사용
    // @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    // 상품 등록 버튼(Post)를 누른 다음의 동작 - @ModelAttribute 사용
    // @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model){

        itemRepository.save(item);

        // model.addAttribute("item", item); @ModelAttribute("item")라고 하면 이 부분 주석처리해도 됨

        return "basic/item";
    }

    // 상품 등록 버튼(Post)를 누른 다음의 동작 - @ModelAttribute 이름 생략 사용
    // @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model){

        itemRepository.save(item);

        // model.addAttribute("item", item); @ModelAttribute("item")라고 하면 이 부분 주석처리해도 됨

        return "basic/item";
    }

    // 상품 등록 버튼(Post)를 누른 다음의 동작 - @ModelAttribute 자체 생략
    // @PostMapping("/add")
    public String addItemV4(Item item, Model model){

        itemRepository.save(item);

        // model.addAttribute("item", item); @ModelAttribute("item")라고 하면 이 부분 주석처리해도 됨

        return "basic/item";
    }

    // 상품 등록 버튼(Post)를 누른 다음의 동작 - 리다이렉트 버전
    //@PostMapping("/add")
    public String addItemV5(Item item, Model model){

        itemRepository.save(item);

        // model.addAttribute("item", item); @ModelAttribute("item")라고 하면 이 부분 주석처리해도 됨

        return "redirect:/basic/items/" + item.getId();  // 앞에 슬래쉬 없으면 맨 위 @RequestMapping("/basic/items") 여기에 바로 이어져서 붙음
    }

    // 상품 등록 버튼(Post)를 누른 다음의 동작 - 리다이렉트 버전 + Attribute까지 추가
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){

        Item savedItem = itemRepository.save(item);
        // redirectAttributes에 원하는 데이터 담아서 리다이렉트
        redirectAttributes.addAttribute("itemId", savedItem.getId()); // URI에 들어감
        redirectAttributes.addAttribute("status", true); // 쿼리 파라미터로 넘어감

        // 치환이 되는건 치환이 되고 안되면 쿼리 파라미터로 넘어감
        return "redirect:/basic/items/{itemId}";  // 앞에 슬래쉬 없으면 맨 위 @RequestMapping("/basic/items") 여기에 바로 이어져서 붙음
    }

    // 상품 수정 창
    @GetMapping("{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    // 상품 수정 버튼 눌렀을 때
    @PostMapping("{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){

        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}"; // 리다이렉트
    }




    // 테스트용 데이터 추가
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));

    }

}

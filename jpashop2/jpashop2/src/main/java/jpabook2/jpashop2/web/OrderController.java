package jpabook2.jpashop2.web;

import jpabook2.jpashop2.domain.Item;
import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.domain.Order;
import jpabook2.jpashop2.repository.OrderSearch;
import jpabook2.jpashop2.service.ItemService;
import jpabook2.jpashop2.service.MemberService;
import jpabook2.jpashop2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;


    @GetMapping(value = "/order")
    public String createForm(Model model){
        List<Member> members = memberService.findAll();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";

    }

    @PostMapping(value = "/order")
    public String order(@RequestParam Long memberId, @RequestParam Long itemId, @RequestParam int count){

        orderService.order(memberId, itemId, count);

        return "redirect:/orders";

    }

    @GetMapping(value="/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){

        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancel(@PathVariable("orderId") Long orderId){

        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }




}

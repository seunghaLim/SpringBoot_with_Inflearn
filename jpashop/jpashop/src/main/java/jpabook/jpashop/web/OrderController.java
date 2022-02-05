package jpabook.jpashop.web;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping(value = "/order")
    public String createForm(Model model){

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }

    @PostMapping(value = "/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {

        orderService.order(memberId, itemId, count);
        return "redirect:/orders";

    }

    // @GetMapping(value = "/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model){

        log.info("order = {}, status = {}", orderSearch.getMemberName(), orderSearch.getOrderStatus());

        List<Order> orders = orderService.findOrders(orderSearch);
        for (Order order : orders) {
            log.info("order = {}, status = {}", order.getId(), order.getStatus());
        }
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @GetMapping(value = "/orders")
    public String orderList2(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {

        log.info("member = {}, status = {}", orderSearch.getMemberName(), orderSearch.getOrderStatus());

        List<Order> orders = orderService.findOrders(orderSearch);
        for (Order order : orders) {
            log.info("order = {}, status = {}", order.getId(), order.getStatus());
        }
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {

        orderService.cancelOrder(orderId);

        return "redirect:/orders";

    }
}

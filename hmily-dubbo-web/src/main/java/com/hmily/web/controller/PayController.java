package com.hmily.web.controller;

import com.hmily.common.utils.JSONResult;
import com.hmily.item.pojo.Items;
import com.hmily.item.service.ItemsService;
import com.hmily.order.pojo.Orders;
import com.hmily.order.service.OrdersService;
import com.hmily.web.curator.utils.ZKCurator;
import com.hmily.web.service.CulsterService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 订购商品controller
 */
@Controller
public class PayController {

    final static Logger log = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CulsterService culsterService;

    @Autowired
    private ZKCurator zkCurator;

    @RequestMapping("/test")
    @ResponseBody
    public JSONResult test() {

        return JSONResult.ok("test");
    }

    @RequestMapping("/item")
    @ResponseBody
    public JSONResult getItemById(String id) {

        Items item = itemsService.getItem(id);

        return JSONResult.ok(item);
    }

    @RequestMapping("/order")
    @ResponseBody
    public JSONResult getOrderById(String id) {

        Orders order = ordersService.getOrder(id);

        return JSONResult.ok(order);
    }

    @RequestMapping("/buy")
    @ResponseBody
    public JSONResult buy(String itemId) {
        boolean result = culsterService.displayBuy(itemId);
        return JSONResult.ok(result ? "订单创建成功..." : "订单创建失败...");
    }

    /**
     * @Description: 模拟集群下的数据不一致
     */
    @RequestMapping("/buy2")
    @ResponseBody
    public JSONResult buy2(String itemId) {
        boolean result = culsterService.displayBuy(itemId);
        return JSONResult.ok(result ? "订单创建成功..." : "订单创建失败...");
    }

    /**
     * @Description: 判断zk是否连接
     */
    @RequestMapping("/isZKAlive")
    @ResponseBody
    public JSONResult isZKAlive() {
        boolean isAlive = zkCurator.isZKAlive();
        String result = isAlive ? "连接" : "断开";
        return JSONResult.ok(result);
    }
	
}

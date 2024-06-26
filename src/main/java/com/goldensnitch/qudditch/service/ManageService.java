package com.goldensnitch.qudditch.service;

import com.goldensnitch.qudditch.dto.PaginationParam;
import com.goldensnitch.qudditch.dto.Product;
import com.goldensnitch.qudditch.dto.UserStoreExt;
import com.goldensnitch.qudditch.dto.manage.InputOrder;
import com.goldensnitch.qudditch.dto.manage.InputReq;
import com.goldensnitch.qudditch.dto.manage.OrderDetailRes;
import com.goldensnitch.qudditch.dto.manage.OrderRes;
import com.goldensnitch.qudditch.mapper.ManageMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManageService {
    private final ManageMapper manageMapper;

    public ManageService(ManageMapper manageMapper) {
        this.manageMapper = manageMapper;
    }

    public List<OrderRes> getOrderList(PaginationParam paginationParam) {
        return manageMapper.getOrderList(paginationParam.getRecordSize(), paginationParam.getOffset());
    }


    public List<OrderDetailRes> getOrderDetail(int orderStoreId) {
        return manageMapper.getOrderDetail(orderStoreId);
    }

    public void confirmOrder(int orderStoreId, List<InputReq> list) {
        Map<String, Object> map = new HashMap<>();
        LocalDate NOW = LocalDate.now();

        int userStoreId = manageMapper.getUserStoreIdByOrderId(orderStoreId);
        map.put("orderStoreId", orderStoreId);
        map.put("userStoreId", userStoreId);
        manageMapper.updateOrderState(orderStoreId);
        manageMapper.insertStoreInput(map);

        for (InputReq inputReq : list) {
            InputOrder inputOrder = new InputOrder();
            inputOrder.setStoreInputId((Integer) map.get("no"));
            inputOrder.setProductId(inputReq.getProductId());
            inputOrder.setQty(inputReq.getQty());

            LocalDate expirationDate = NOW.plusDays(inputReq.getExpirationDate());
            inputOrder.setExpirationDate(expirationDate.toString());

            manageMapper.insertStoreInputProduct(inputOrder);
        }

    }

    public int getOrderCount() {
        return manageMapper.getOrderCount();
    }


    public int getStoreCount() {
        return manageMapper.getStoreCount();
    }

    public List<UserStoreExt> getStoreList(PaginationParam paginationParam) {
        return manageMapper.getStoreList(paginationParam.getRecordSize(), paginationParam.getOffset());
    }

    public int getProductCountByCategoryId(Integer categoryId) {
        return manageMapper.getProductCountByCategoryId(categoryId);
    }

    public List<Product> getProductListByCategoryId(PaginationParam paginationParam, Integer categoryId) {
        return manageMapper.getProductListByCategoryId(paginationParam.getRecordSize(), paginationParam.getOffset(), categoryId);
    }

    public List<Product> getProductList(PaginationParam paginationParam) {
        return manageMapper.getProductList(paginationParam.getRecordSize(), paginationParam.getOffset());
    }

    public int getProductCount() {
        return manageMapper.getProductCount();
    }

    public void addProduct(Product product) {
        manageMapper.addProduct(product);
    }

    public void updateProduct(int productId, Product product) {
        manageMapper.updateProduct(productId, product);
    }
}

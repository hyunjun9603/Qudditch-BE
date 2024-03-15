package com.goldensnitch.qudditch.service;

import com.goldensnitch.qudditch.dto.PaginationParam;
import com.goldensnitch.qudditch.dto.Store;
import com.goldensnitch.qudditch.dto.StoreStockRes;
import com.goldensnitch.qudditch.mapper.StoreLocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StoreLocationService {

    private final StoreLocationMapper storeLocationMapper;

    @Autowired
    public StoreLocationService(StoreLocationMapper storeLocationMapper) {
        this.storeLocationMapper = storeLocationMapper;
    }

    @GetMapping("")
    public List<Store> getLocation(double currentWgs84X, double currentWgs84Y) {

        Map<String, Object> params = new HashMap<>();

        params.put("currentWgs84X", currentWgs84X);
        params.put("currentWgs84Y", currentWgs84Y);

        return storeLocationMapper.getLocation(params);
    }

    public int getUserstoreIdBystoreId(int storeId) {
        return storeLocationMapper.getUserstoreIdBystoreId(storeId);
    }

    public List<StoreStockRes> storeStockList(int userStoreId, PaginationParam paginationParam) {
        return storeLocationMapper.storeStockList(userStoreId, paginationParam.getRecordSize(), paginationParam.getOffset());
    }

    public int cntStoreStockList(int userStoreId) {
        return storeLocationMapper.cntStoreStockList(userStoreId);
    }


}

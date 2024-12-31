package com.shift.support.service;

import com.shift.support.entity.Store;
import com.shift.support.mapper.StoreMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StoreService {
	private final StoreMapper storeMapper;
	
	//店舗の存在チェック
	public boolean isExist(String storeCode) throws Exception {
		
		Store store = storeMapper.getByStoreCode(storeCode);
		if(store == null) {
			throw new Exception();
		}
		
		return true;
	}
}

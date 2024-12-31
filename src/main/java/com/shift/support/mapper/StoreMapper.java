package com.shift.support.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.shift.support.entity.Store;

@Mapper
public interface StoreMapper {
	//全権取得
	@Select("SELECT * FROM m_stores")
	@Result(property = "storeCode", column = "store_code")
	List<Store> getAll();
	
	//storeCodeから単体取得
	@Select("SELECT * FROM m_stores WHERE store_code = #{storeCode}")
	@Result(property = "storeCode", column = "store_code")
	Store getByStoreCode(String storeCode);
}

package com.shift.support.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Store {
	private String storeCode;
	private String name;
	private Date open;
	private Date close;
}

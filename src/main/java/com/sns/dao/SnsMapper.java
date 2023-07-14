package com.sns.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface SnsMapper {

	public List<Map<String,Object>> selectSnsList();
}

package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.AddressXz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressXzDao {

    List<AddressXz> findAddress();
}

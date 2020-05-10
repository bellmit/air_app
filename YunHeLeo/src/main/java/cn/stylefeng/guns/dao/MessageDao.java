package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageDao extends BaseMapper<Message> {

    @Select("select count(*) from tb_message")
    int count();
}

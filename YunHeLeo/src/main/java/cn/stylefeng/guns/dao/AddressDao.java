package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Address;
import cn.stylefeng.guns.pojos.AddressDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AddressDao extends BaseMapper<Address> {

    @Select("SELECT a.id,a.create_date,a.t_province_dic,a.t_city_dic,a.t_area_dic,a.t_address,a.t_mobile,a.t_username, sd.StateName, cd.CityName, aad.CityName AreaName\n" +
            "FROM tb_address a,tb_state_dic sd, tb_city_dic cd, tb_admin_area_dic aad\n" +
            "WHERE sd.Xzqdm=a.t_province_dic AND cd.AdminAreaNum=a.t_city_dic AND aad.AdminAreaNum=a.t_area_dic\n" +
            "AND a.t_user_id=#{userId}\n" +
            "AND a.create_date<=NOW() LIMIT 1")
    AddressDetails findUserAddress(@Param("userId") String userId);

    @Select("SELECT a.id,a.create_date,a.t_address,a.t_mobile,a.t_username, sd.StateName, cd.CityName, aad.CityName AreaName\n" +
            "FROM tb_address a,tb_state_dic sd, tb_city_dic cd, tb_admin_area_dic aad\n" +
            "WHERE sd.Xzqdm=a.t_province_dic AND cd.AdminAreaNum=a.t_city_dic AND aad.AdminAreaNum=a.t_area_dic\n" +
            "AND a.id=#{addressId} ")
    AddressDetails findByIdAddress(@Param("addressId") String addressId);
}

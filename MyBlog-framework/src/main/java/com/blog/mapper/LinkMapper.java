package com.blog.mapper;

import com.blog.annotation.AutoFill;
import com.blog.pojo.common.OperationType;
import com.blog.pojo.entity.Link;
import com.blog.pojo.vo.AllLinkVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LinkMapper {
    @Select("select address,description,id,logo,name from link where status = 0")
    List<AllLinkVo> getAllLink();

    @Select("select * from link where del_flag = 0")
    Page<Link> list();

    @AutoFill(OperationType.INSERT)
    @Insert("insert into link(name, logo, description, address, status, create_by, create_time, update_by, update_time, del_flag) " +
            "VALUES (#{name},#{logo},#{description},#{address},#{status},#{createBy},#{createTime},#{updateBy},#{updateTime},#{delFlag})")
    void addLink(Link link);

    @Select("select * from link where id = #{id}")
    Link getLinkById(Long id);

    @AutoFill(OperationType.UPDATE)
    void update(Link link);

    @Update("update link set del_flag = 1 where id = #{id}")
    void delete(Long id);

    @Update("update link set status = #{status},update_time = #{updateTime},update_by = #{updateBy} where id = #{id}")
    void changeLinkStatus(Link link);
}

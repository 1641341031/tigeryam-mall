package shop.tigeryam.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import shop.tigeryam.entity.UmsRole;

import java.util.List;

public interface UmsAdminRoleRelationDao {
    /**
     * 获取用于所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);
}

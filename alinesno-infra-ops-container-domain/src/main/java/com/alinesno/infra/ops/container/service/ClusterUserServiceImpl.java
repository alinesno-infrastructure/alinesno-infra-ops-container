package com.alinesno.infra.ops.container.service;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.container.api.ClusterDto;
import com.alinesno.infra.ops.container.entity.ClusterEntity;
import com.alinesno.infra.ops.container.entity.ClusterUserEntity;
import com.alinesno.infra.ops.container.mapper.ClusterMapper;
import com.alinesno.infra.ops.container.mapper.ClusterUserMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 事件 Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Service
public class ClusterUserServiceImpl extends IBaseServiceImpl<ClusterUserEntity, ClusterUserMapper> implements IClusterUserService {

    @Autowired
    private ClusterMapper clusterMapper ;

    @Override
    public ClusterDto getByUserId(long userId) {

        ClusterUserEntity entity = this.getOne(new LambdaUpdateWrapper<ClusterUserEntity>().eq(ClusterUserEntity::getUserId, userId));

        // 如果entity为空则返回
        if (entity == null) {
            return null;
        }

        ClusterEntity cluster = clusterMapper.selectById(entity.getClusterId());
        ClusterDto dto = new ClusterDto() ;

        BeanUtils.copyProperties(cluster, dto);
        dto.setNamespace(entity.getNamespace());

        return dto ;
    }

    @Override
    public void setUserCluster(long userId, long clusterId , String namespace) {

        if(StringUtils.isBlank(namespace)){
            namespace = "default" ;
        }

        // 先删除所有用户集群，然后再添加新的集群
        LambdaUpdateWrapper<ClusterUserEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ClusterUserEntity::getUserId, userId);
        this.remove(wrapper);

        // 添加新的集群关联
        ClusterUserEntity entity = new ClusterUserEntity();
        entity.setUserId(userId);
        entity.setClusterId(clusterId);
        entity.setNamespace(namespace);

        this.save(entity);
    }
}

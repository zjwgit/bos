package cn.zjw.bos.dao;

import cn.zjw.bos.dao.base.BaseDao;
import cn.zjw.bos.domain.Region;

import java.util.List;

public interface RegionDao extends BaseDao<Region>{
  public List<Region> findListByQ(String q);
}

package cn.zjw.bos.dao.impl;

import cn.zjw.bos.dao.RegionDao;
import cn.zjw.bos.dao.base.impl.BaseDaoImpl;
import cn.zjw.bos.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl  extends BaseDaoImpl<Region> implements RegionDao {
	@Override
	public List<Region> findListByQ(String q) {
		String hql = "FROM Region r WHERE r.shortcode LIKE ? "
				+ "	OR r.citycode LIKE ? OR r.province LIKE ? "
				+ "OR r.city LIKE ? OR r.district LIKE ?";
		List<Region> list = (List<Region>) this.getHibernateTemplate().
				find(hql, "%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
		return list;
	}
}

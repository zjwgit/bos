package cn.zjw.bos.service;

import cn.zjw.bos.domain.Decidedzone;
import cn.zjw.bos.utils.PageBean;

public interface DecidedzoneService {
	public void save(Decidedzone model, String[] subareaid);

	public void pageQuery(PageBean pageBean);
}

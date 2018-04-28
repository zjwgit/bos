package cn.zjw.bos.web.action;

import cn.zjw.bos.domain.Region;
import cn.zjw.bos.service.RegionService;
import cn.zjw.bos.utils.PageBean;
import cn.zjw.bos.utils.PinYin4jUtils;
import cn.zjw.bos.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

	private File regionFile;
	@Autowired
	private RegionService regionService;

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}


	public String importXls() throws Exception {
		//把文件转为poi,遍历，从cell中取出各个字段，写入region中，放入list，saveorupdate
		List<Region> regionList = new ArrayList<Region>();
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
		HSSFSheet hssfSheet = workbook.getSheet("Sheet1");
		for (Row row : hssfSheet) {
			//如果是第一行表头，跳过
			if(row.getRowNum()==0){
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			//包装一个区域对象
			Region region = new Region(id, province, city, district, postcode, null, null, null);

			province = province.substring(0, province.length() - 1);
			city = city.substring(0, city.length() - 1);
			district = district.substring(0, district.length() - 1);
			String info = province + city + district;
			String[] headByString = PinYin4jUtils.getHeadByString(info);
			String shortcode = StringUtils.join(headByString);
			//城市编码---->>shijiazhuang
			String citycode = PinYin4jUtils.hanziToPinyin(city, "");

			region.setShortcode(shortcode);
			region.setCitycode(citycode);
			regionList.add(region);
		}
		regionService.saveBatch(regionList);
		return NONE;
	}
	public String pageQuery() throws Exception {
		regionService.pageQuery(pageBean);
		java2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
		return NONE;
	}

	private String q;

	/**
	 * 查询所有区域，写回json数据
	 * @return
	 */
	public String listajax(){
		List<Region> list = null;
		if(StringUtils.isNotBlank(q)){
			list = regionService.findListByQ(q);
		}else{
			list = regionService.findAll();
		}
		this.java2Json(list, new String[]{"subareas"});
		return NONE;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

}

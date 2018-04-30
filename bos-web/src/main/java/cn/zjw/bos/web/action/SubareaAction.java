package cn.zjw.bos.web.action;

import cn.zjw.bos.domain.Region;
import cn.zjw.bos.domain.Subarea;
import cn.zjw.bos.service.SubareaService;
import cn.zjw.bos.utils.FileUtils;
import cn.zjw.bos.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	@Autowired
	private SubareaService subareaService;
	/**
	 * 添加分区
	 */
	public String add(){
		subareaService.save(model);
		return LIST;
	}
	public String pageQuery(){
		DetachedCriteria dc = pageBean.getDetachedCriteria();
		String addresskey = model.getAddresskey();
		if (StringUtils.isNotBlank(addresskey)){
			dc.add(Restrictions.like("addresskey","%"+addresskey+"%"));
		}
		Region region = model.getRegion();
		if(region != null){
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			dc.createAlias("region", "r");
			if(StringUtils.isNotBlank(province)){
				//添加过滤条件，根据省份模糊查询-----多表关联查询，使用别名方式实现
				//参数一：分区对象中关联的区域对象属性名称
				//参数二：别名，可以任意
				dc.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			if(StringUtils.isNotBlank(city)){
				//添加过滤条件，根据市模糊查询-----多表关联查询，使用别名方式实现
				//参数一：分区对象中关联的区域对象属性名称
				//参数二：别名，可以任意
				dc.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			if(StringUtils.isNotBlank(district)){
				//添加过滤条件，根据区模糊查询-----多表关联查询，使用别名方式实现
				//参数一：分区对象中关联的区域对象属性名称
				//参数二：别名，可以任意
				dc.add(Restrictions.like("r.district", "%"+district+"%"));
			}
		}

		subareaService.pageQuery(pageBean);
		java2Json(pageBean,new String[]{"currentPage","pageSize",
				"detachedCriteria","decidedzone","subareas"});
		return NONE;
	}

	public String exportXls() throws IOException {
		List<Subarea> list = subareaService.findAll();
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个标签页
		HSSFSheet sheet = workbook.createSheet("分区数据");
		//创建标题行
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("开始编号");
		headRow.createCell(2).setCellValue("结束编号");
		headRow.createCell(3).setCellValue("位置信息");
		headRow.createCell(4).setCellValue("省市区");

		for (Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(3).setCellValue(subarea.getPosition());
			dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}

		//第三步：使用输出流进行文件下载（一个流、两个头）

		String filename = "分区数据.xls";
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setContentType(contentType);

		//获取客户端浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		workbook.write(out);

		return NONE;
	}
	public String listajax(){
	  List<Subarea> list = subareaService.findListNotAssocation();
	  java2Json(list,new String[]{"decidedzone","region"});
	  return NONE;
	}
}

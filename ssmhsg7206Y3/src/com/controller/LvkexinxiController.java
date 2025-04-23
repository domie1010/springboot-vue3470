﻿package com.controller;

import java.io.File;
import java.io.IOException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Lvkexinxi;
import com.server.LvkexinxiServer;
import com.util.PageBean;
import net.sf.json.JSONObject;
import com.util.db;
import java.sql.SQLException;
import java.sql.*;
@Controller
public class LvkexinxiController {
	@Resource
	private LvkexinxiServer lvkexinxiService;


   
	@RequestMapping("addLvkexinxi.do")
	public String addLvkexinxi(HttpServletRequest request,Lvkexinxi lvkexinxi,HttpSession session) throws SQLException{
		Timestamp time=new Timestamp(System.currentTimeMillis());
		
		lvkexinxi.setAddtime(time.toString().substring(0, 19));
		lvkexinxiService.add(lvkexinxi);
		session.setAttribute("backxx", "添加成功");
		session.setAttribute("backurl", request.getHeader("Referer"));
		
		//session.setAttribute("backurl", "lvkexinxiList.do");
		
		return "redirect:postback.jsp";
		//return "redirect:lvkexinxiList.do";
		
		
		
	}
 
//	处理编辑
	@RequestMapping("doUpdateLvkexinxi.do")
	public String doUpdateLvkexinxi(int id,ModelMap map,Lvkexinxi lvkexinxi){
		lvkexinxi=lvkexinxiService.getById(id);
		map.put("lvkexinxi", lvkexinxi);
		return "lvkexinxi_updt";
	}
	
	
	
	
	
//	后台详细
	@RequestMapping("lvkexinxiDetail.do")
	public String lvkexinxiDetail(int id,ModelMap map,Lvkexinxi lvkexinxi){
		lvkexinxi=lvkexinxiService.getById(id);
		map.put("lvkexinxi", lvkexinxi);
		return "lvkexinxi_detail";
	}
//	前台详细
	@RequestMapping("lkxxDetail.do")
	public String lkxxDetail(int id,ModelMap map,Lvkexinxi lvkexinxi){
		lvkexinxi=lvkexinxiService.getById(id);
		map.put("lvkexinxi", lvkexinxi);
		return "lvkexinxidetail";
	}
//	
	@RequestMapping("updateLvkexinxi.do")
	public String updateLvkexinxi(int id,ModelMap map,Lvkexinxi lvkexinxi,HttpServletRequest request,HttpSession session){
		lvkexinxiService.update(lvkexinxi);
		session.setAttribute("backxx", "修改成功");
		session.setAttribute("backurl", request.getHeader("Referer"));
		return "redirect:postback.jsp";
		//String url = request.getHeader("Referer");
		//return "redirect:"+url;
		//return "redirect:lvkexinxiList.do";
	}

//	分页查询
	@RequestMapping("lvkexinxiList.do")
	public String lvkexinxiList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Lvkexinxi lvkexinxi, String lvkebianhao, String lvkexingming, String xingbie, String dianhua, String dizhi){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(lvkebianhao==null||lvkebianhao.equals("")){pmap.put("lvkebianhao", null);}else{pmap.put("lvkebianhao", lvkebianhao);}
		if(lvkexingming==null||lvkexingming.equals("")){pmap.put("lvkexingming", null);}else{pmap.put("lvkexingming", lvkexingming);}
		if(xingbie==null||xingbie.equals("")){pmap.put("xingbie", null);}else{pmap.put("xingbie", xingbie);}
		if(dianhua==null||dianhua.equals("")){pmap.put("dianhua", null);}else{pmap.put("dianhua", dianhua);}
		if(dizhi==null||dizhi.equals("")){pmap.put("dizhi", null);}else{pmap.put("dizhi", dizhi);}
		
		int total=lvkexinxiService.getCount(pmap);
		pageBean.setTotal(total);
		List<Lvkexinxi> list=lvkexinxiService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "lvkexinxi_list";
	}
	
	
	
	@RequestMapping("lkxxList.do")
	public String lkxxList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Lvkexinxi lvkexinxi, String lvkebianhao, String lvkexingming, String xingbie, String dianhua, String dizhi){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(lvkebianhao==null||lvkebianhao.equals("")){pmap.put("lvkebianhao", null);}else{pmap.put("lvkebianhao", lvkebianhao);}
		if(lvkexingming==null||lvkexingming.equals("")){pmap.put("lvkexingming", null);}else{pmap.put("lvkexingming", lvkexingming);}
		if(xingbie==null||xingbie.equals("")){pmap.put("xingbie", null);}else{pmap.put("xingbie", xingbie);}
		if(dianhua==null||dianhua.equals("")){pmap.put("dianhua", null);}else{pmap.put("dianhua", dianhua);}
		if(dizhi==null||dizhi.equals("")){pmap.put("dizhi", null);}else{pmap.put("dizhi", dizhi);}
		
		int total=lvkexinxiService.getCount(pmap);
		pageBean.setTotal(total);
		List<Lvkexinxi> list=lvkexinxiService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "lvkexinxilist";
	}
	
	@RequestMapping("deleteLvkexinxi.do")
	public String deleteLvkexinxi(int id,HttpServletRequest request){
		lvkexinxiService.delete(id);
		String url = request.getHeader("Referer");
		return "redirect:"+url;
		//return "redirect:lvkexinxiList.do";
	}
	
	@RequestMapping("quchongLvkexinxi.do")
	public void quchongLvkexinxi(Lvkexinxi lvkexinxi,HttpServletResponse response){
		   Map<String,Object> map=new HashMap<String,Object>();
		   map.put("lvkebianhao", lvkexinxi.getLvkebianhao());
		   System.out.println("lvkebianhao==="+lvkexinxi.getLvkebianhao());
		   System.out.println("lvkebianhao222==="+lvkexinxiService.quchongLvkexinxi(map));
		   JSONObject obj=new JSONObject();
		   if(lvkexinxiService.quchongLvkexinxi(map)!=null){
				 obj.put("info", "ng");
			   }else{
				   obj.put("info", "客户编号可以用！");
				  
			   }
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter out=null;
		   try {
			out=response.getWriter();
			out.print(obj);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}

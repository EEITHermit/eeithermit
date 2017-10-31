package com.hermit.iii.favorite.model;

import java.util.*;

import org.json.*;

import com.hermit.iii.house.model.*;
import com.hermit.iii.member.model.*;

public class FavoriteService {
	private FavoriteDAO_interface_hibernate dao;

	public FavoriteService() {
		dao = new FavoriteDAO_hibernate();
	}

	// 新增service
	public FavoriteVO addFavorite(MemberVO memberVO, HouseVO houseVO) {
		FavoriteVO favoriteVO = new FavoriteVO();

		favoriteVO.setMemberVO(memberVO);
		favoriteVO.setHouseVO(houseVO);

		dao.insert(favoriteVO);

		return favoriteVO;
	}

	// 新增service Struts 2可 用(預留)
	public void addFavorite(FavoriteVO favoriteVO) {
		dao.insert(favoriteVO);
	}

	// 修改service
	public FavoriteVO updateFavorite(Integer favNO, MemberVO memberVO, HouseVO houseVO, java.sql.Date favDate) {
		FavoriteVO favoriteVO = new FavoriteVO();

		favoriteVO.setFavNO(favNO);
		favoriteVO.setMemberVO(memberVO);
		favoriteVO.setHouseVO(houseVO);
		favoriteVO.setFavDate(favDate);

		dao.update(favoriteVO);

		return favoriteVO;
	}

	// 修改service Struts 2可 用(預留)
	public void updateFavorite(FavoriteVO favoriteVO) {
		dao.update(favoriteVO);
	}

	// 刪除service
	public void deleteFavorite(Integer favNO) {
		dao.delete(favNO);
	}

	// 查詢一筆service
	public FavoriteVO getOneFavorite(Integer favNO) {
		return dao.findByPrimaryKey(favNO);
	}

	// 查詢全部service
	public Set<FavoriteVO> getAll() {
		return dao.getAll();
	}
	
	// AJAX 會員編號查詢 (JSON) only add on hibernate interface
	public String accountFavoriteAJAX(Integer memNO){
		Set<FavoriteVO> set = dao.find_MemNO_AJAX(memNO);
		List<Object> jsonList = new ArrayList<Object>();

		for (FavoriteVO favorite : set) {

			Map<String, Object> jm = new HashMap<String, Object>();

			jm.put("favNO", favorite.getFavNO());
			jm.put("houseTitle", favorite.getHouseVO().getHouseTitle());
			jm.put("previewPic", favorite.getHouseVO().getPreviewPic());
			jm.put("houseStatus", favorite.getHouseVO().getHouseStatus());
			jm.put("houseRent", favorite.getHouseVO().getHouseRent());
			jm.put("houseAddr", favorite.getHouseVO().getHouseAddr());
			jm.put("houseSize", favorite.getHouseVO().getHouseSize());
			jm.put("favDate", favorite.getFavDate());

			jsonList.add(jm);
		}
		String jsonString = new JSONArray(jsonList).toString();
		return jsonString;
	}
}
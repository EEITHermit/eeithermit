package com.hermit.iii.qanda.model;

import java.util.ArrayList;
import java.util.Set;

public class QandAService {

	private QandADAO_interface_hibernate dao;

	public QandAService() {
		dao = new QandAJNDIDAO_hibernate();
	}

	// 新增service
	public QandAVO addQandA(Integer memNO, Integer empNO, Integer houseNO, java.sql.Date qTime, java.sql.Date aTime,
			Byte qaType, String qDetail, String aDetail) {

		QandAVO qandaVO = new QandAVO();

		qandaVO.getMemberVO().setMemNO(memNO);
		if(empNO != null){
			qandaVO.setEmpNO(empNO);
		}
		qandaVO.getHouseVO().setHouseNO(houseNO);
		qandaVO.setqTime(qTime);
		if(aTime != null){
			qandaVO.setaTime(aTime);
		}
		qandaVO.setQaType(qaType);
		qandaVO.setqDetail(qDetail);
		if(aDetail != null){
			qandaVO.setaDetail(aDetail);
		}
		dao.insert(qandaVO);

		return qandaVO;
	}

	// 修改service
	public QandAVO updateQandA(Integer qaNO, Integer memNO, Integer empNO, Integer houseNO, java.sql.Date qTime,
			java.sql.Date aTime, Byte qaType, String qDetail, String aDetail) {

		QandAVO qandaVO = new QandAVO();

		qandaVO.setQaNO(qaNO);
		qandaVO.getMemberVO().setMemNO(memNO);;
		qandaVO.setEmpNO(empNO);
		qandaVO.getHouseVO().setHouseNO(houseNO);
		qandaVO.setqTime(qTime);
		qandaVO.setaTime(aTime);
		qandaVO.setQaType(qaType);
		qandaVO.setqDetail(qDetail);
		qandaVO.setaDetail(aDetail);

		dao.update(qandaVO);

		return qandaVO;
	}

	// 刪除service
	public void deleteQandA(Integer qaNO) {
		dao.delete(qaNO);
	}

	// 查詢一筆service
	public QandAVO getOneQandA(Integer qaNO) {
		return dao.findByPrimaryKey(qaNO);
	}

	// 查詢全部service
	public Set<QandAVO> getAll() {
		return dao.getAll();
	}
	// 會員查詢Q&A
	public ArrayList<QandAVO> getAllByMemberNO(Integer memNO){
		return dao.getAllByMemberNO(memNO);
	}
	// 員工(客服)查詢Q&A
	public ArrayList<QandAVO> getAllByBoroughNO0(Integer boroughNO){
		return dao.getAllByBoroughNO0(boroughNO);
	}
	// 員工(業務)查詢Q&A
	public ArrayList<QandAVO> getAllByBoroughNO1(Integer boroughNO){
		return dao.getAllByBoroughNO1(boroughNO);
	}
	//查詢已處理
	public ArrayList<QandAVO> getAllNotDeal(){
		return dao.getAllNotDeal();
	}
	//查詢未處理
	public ArrayList<QandAVO> getAllDealed(){
		return dao.getAllDealed();
	}
}
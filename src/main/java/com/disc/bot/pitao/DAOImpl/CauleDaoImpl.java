package com.disc.bot.pitao.DAOImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.disc.bot.pitao.DAO.SqlUtils;
import com.disc.bot.pitao.domain.CauleDomain;

public class CauleDaoImpl {
	
	public List<CauleDomain> returnListOfCaules (){
		
		
		List<CauleDomain> listOfCaules = new ArrayList<CauleDomain>();
		
        SqlUtils sqlUtils;
		try {
			sqlUtils = new SqlUtils();
			ResultSet rs = sqlUtils.executeQuery("select * from Caule");
			while (rs.next()) {
				CauleDomain caule = new CauleDomain();
				caule.setUid(rs.getString("uid"));
				caule.setCauleName(rs.getString("cauleName"));
				caule.setCauleStr(rs.getString("cauleStr"));
				listOfCaules.add(caule);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listOfCaules;
		
	}
}

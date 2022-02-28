package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_orderBuy {
	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;
//		Integer memID, Integer orderPrice, Date orderDate, Integer orderPaying,
//		Integer orderSend, Integer orderSurvive
		if ("po_no".equals(columnName) || "memID".equals(columnName) || "po_total".equals(columnName) || "po_payment".equals(columnName)|| "po_delivery".equals(columnName)|| "po_status".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("po_time".equals(columnName))                          // 用於date
			aCondition = columnName + " like '%" + value + "%'";                          //for 其它DB  的 date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

//				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
}

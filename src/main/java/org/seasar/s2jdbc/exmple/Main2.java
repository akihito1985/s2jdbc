package org.seasar.s2jdbc.exmple;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * s2jdbc（S2コンテナに含まれるフレームワーク）にてDBデータをコンソール出力する処理</br>
 * →JdbcManager（JDBCによるSQLの実行を管理するインターフェース）
 * @author apollo</br>
 */
public class Main2 {
	// JdbcManagerをDIで使用する場合
	@Resource
	protected JdbcManager jdbcManager;

	/**
	 * メイン処理
	 * @param args
	 */
	public static void main(String[] args) {
		// S2コンテナ初期化
		SingletonS2ContainerFactory.init();

		// S2コンテナからJdbcManagerを取得する。
		JdbcManager jdbcManager =
				SingletonS2Container.getComponent(JdbcManager.class);

		SearchEmployeeParam param = new SearchEmployeeParam();
		param.salMin = new BigDecimal("1500.00");
		param.salMax = new BigDecimal("3000.00");

		List<SearchEmployeeDto> results = jdbcManager
				.selectBySqlFile(
						SearchEmployeeDto.class,
						"org.seasar.s2jdbc.exmple/service/EmployeeService_searchEmployeeBySalary.sql",
						param)
		.getResultList();

		// 検索結果表示
		for(SearchEmployeeDto empSel: results){
			System.out.println(empSel.id + ": " + empSel.empName + ": " + empSel.sal);
		}

		// S2コンテナ破棄
		SingletonS2ContainerFactory.destroy();
	}
}
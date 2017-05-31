package org.seasar.s2jdbc.exmple;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.s2jdbc.exmple.entity.Emp;

/**
 * s2jdbc（S2コンテナに含まれるフレームワーク）にてDBデータをコンソール出力する処理</br>
 * →JdbcManager（JDBCによるSQLの実行を管理するインターフェース）
 * @author apollo</br>
 */
public class Main {
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

		// JdbcManagerでDBのデータ作成をする。
		Emp empIns = new Emp();
		empIns.empNo = 9999;
		empIns.deptId = 1;
		empIns.empName = "APOLLO";
		//int count = jdbcManager.insert(empIns).execute();

		// JdbcManagerでDBのデータ検索をする。
		List<Emp> results = jdbcManager.from(Emp.class)
				.where("deptId = 1")
				.orderBy("empNo asc")
				.getResultList();

		// 検索結果表示
		for(Emp empSel: results){
			System.out.println(empSel.empNo + ": " + empSel.empName + ": " + empSel.sal);
		}

		// JdbcManagerでDBのデータ削除をする。
		//Emp empDel = new Emp();
		//empDel.empNo = 9999;
		//empDel.id = Long.parseLong("15");
		//empDel.versionNo = 1;
		//int countDel = jdbcManager.delete(empDel).execute();

		// S2コンテナ破棄
		SingletonS2ContainerFactory.destroy();
	}
}
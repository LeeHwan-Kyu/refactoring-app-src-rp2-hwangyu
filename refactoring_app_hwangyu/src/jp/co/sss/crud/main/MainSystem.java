package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.util.Constants;

/**
 * 社員情報管理システム開始クラス 社員情報管理システムはこのクラスから始まる。<br/>
 * メニュー画面を表示する。
 *
 * @author System Shared
 *
 */
public class MainSystem {
	/**
	 * 社員管理システムを起動
	 *
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, ParseException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int menuNumber = 0;

		do {
			// メニューの表示
			System.out.println("=== 社員管理システム ===");
			System.out.println(Constants.MENU_SHOW_ALL + ".全件表示");
			System.out.println(Constants.MENU_SEARCH_NAME + ".社員名検索");
			System.out.println(Constants.MENU_SEARCH_DEPT + ".部署ID検索");
			System.out.println(Constants.MENU_INSERT + ".新規登録");
			System.out.println(Constants.MENU_UPDATE + ".更新");
			System.out.println(Constants.MENU_DELETE + ".削除");
			System.out.println(Constants.MENU_EXIT + ".終了");
			System.out.print(Constants.MSG_INPUT_MENU);

			// メニュー番号の入力
			String menuNumberString = reader.readLine();
			menuNumber = Integer.parseInt(menuNumberString);

			// 機能の呼出
			switch (menuNumber) {
			case Constants.MENU_SHOW_ALL:
                // 全件表示機能の呼出
                DBController.findAllEmployees();
                break;
			 case Constants.MENU_SEARCH_NAME:
	                // 社員名検索
	                System.out.print(Constants.MSG_INPUT_EMPLOYEE_NAME);

				// 検索機能の呼出
				DBController.findEmployeesByName();
				break;

			 case Constants.MENU_SEARCH_DEPT:
	                // 検索する部署IDを入力
	                System.out.print(Constants.MSG_INPUT_DEPARTMENT_ID);
				String departmentId = reader.readLine();

				// 検索機能の呼出
				DBController.findEmployeesByDepartmentId(departmentId);
				break;

			 case Constants.MENU_INSERT:
	                // 登録する値を入力
	                System.out.print(Constants.MSG_INPUT_EMPLOYEE_NAME);
	                String employeeName = reader.readLine();

	                System.out.print(Constants.MSG_INPUT_GENDER);
	                String gender = reader.readLine();

	                System.out.print(Constants.MSG_INPUT_BIRTHDAY);
	                String birthday = reader.readLine();

	                System.out.print(Constants.MSG_INPUT_DEPARTMENT_ID);
	                String departmentIdForInsert = reader.readLine();

				// 登録機能の呼出
				DBController.insertEmployee(employeeName, gender, birthday, departmentIdForInsert);
				break;

			  case Constants.MENU_UPDATE:
	                // 更新する社員IDを入力
	                System.out.print(Constants.MSG_INPUT_EMPLOYEE_ID_UPDATE);

				// 更新する値を入力する
				String employeeIdForUpdate = reader.readLine();
				Integer.parseInt(employeeIdForUpdate);

				// 更新機能の呼出
				DBController.updateEmployee(employeeIdForUpdate);
				System.out.println(Constants.MSG_EMPLOYEE_UPDATED);

				break;

			  case Constants.MENU_DELETE:
	                // 削除する社員IDを入力
	                System.out.print(Constants.MSG_INPUT_EMPLOYEE_ID_DELETE);

				// 削除機能の呼出
				DBController.deleteEmployee();
				break;

			}
		} while (menuNumber != 7);
		 System.out.println(Constants.MSG_SYSTEM_END);
	}
}

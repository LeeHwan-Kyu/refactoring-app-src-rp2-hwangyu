package jp.co.sss.crud.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jp.co.sss.crud.util.ConstantSQL;
import jp.co.sss.crud.util.Constants;

/**
 * DB操作処理用のクラス
 *
 * @author System Shared
 */
public class DBController {

	/** インスタンス化を禁止 */
	private DBController() {
	}

	/**
	 * 全ての社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 */
	public static void findAllEmployees() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_ALL_SELECT);

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			//resultSetの結果Setがない場合はfalse
			if (!resultSet.isBeforeFirst()) {
				System.out.println(Constants.MSG_NO_RESULT);
				return;
			}

			// レコードを出力
			System.out.println("社員ID\t社員名\t性別\t生年月日\t部署名");
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id") + "\t");
				System.out.print(resultSet.getString("emp_name") + "\t");

				int gender = Integer.parseInt(resultSet.getString("gender"));
				if (gender == Constants.GENDER_NONE) {
					System.out.print("回答なし" + "\t");
				} else if (gender == Constants.GENDER_MALE) {
					System.out.print("男性" + "\t");

				} else if (gender == Constants.GENDER_FEMALE) {
					System.out.print("女性" + "\t");

				} else if (gender == Constants.GENDER_OTHER) {
					System.out.print("その他" + "\t");

				}

				System.out.print(resultSet.getString("birthday") + "\t");
				System.out.println(resultSet.getString("dept_name"));
			}

			System.out.println("");
		} finally {
			// ResultSetをクローズ
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員名に該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findEmployeesByName() throws ClassNotFoundException, SQLException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		// 検索ワード
		String searchWord = reader.readLine();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_EMP_NAME);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setString(1, "%" + searchWord + "%");

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				System.out.println(Constants.MSG_NO_RESULT);
				return;
			}

			System.out.println(Constants.MSG_NO_RESULT_HEADER);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id"));
				System.out.print("\t");

				System.out.print(resultSet.getString("emp_name"));
				System.out.print("\t");

				String genderString = resultSet.getString("gender");
				int gender = Integer.parseInt(genderString);
				if (gender == Constants.GENDER_NONE) {
					System.out.print("回答なし");
				} else if (gender == Constants.GENDER_MALE) {
					System.out.print("男性");
				} else if (gender == Constants.GENDER_FEMALE) {
					System.out.print("女性");
				} else if (gender == Constants.GENDER_OTHER) {
					System.out.print("その他");
				}

				System.out.print("\t");
				System.out.print(resultSet.getString("birthday"));
				System.out.print("\t");

				System.out.println(resultSet.getString("dept_name"));
			}

			System.out.println("");

		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 部署IDに該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void findEmployeesByDepartmentId(String deptId)
			throws ClassNotFoundException, SQLException, IOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sqlQuery = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sqlQuery.append(ConstantSQL.SQL_SELECT_BY_DEPT_ID);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sqlQuery.toString());

			// 検索条件となる値をバインド
			preparedStatement.setInt(1, Integer.parseInt(deptId));

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println(Constants.MSG_NO_RESULT);
				return;
			}

			System.out.println(Constants.MSG_NO_RESULT_HEADER);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id"));
				System.out.print("\t");

				System.out.print(resultSet.getString("emp_name"));
				System.out.print("\t");

				String genderString = resultSet.getString("gender");
				int gender = Integer.parseInt(genderString);
				if (gender == Constants.GENDER_NONE) {
					System.out.print("回答なし");
				} else if (gender == Constants.GENDER_MALE) {
					System.out.print("男性");
				} else if (gender == Constants.GENDER_FEMALE) {
					System.out.print("女性");
				} else if (gender == Constants.GENDER_OTHER) {
					System.out.print("その他");
				}

				System.out.print("\t");
				System.out.print(resultSet.getString("birthday"));
				System.out.print("\t");

				String deptIdString = resultSet.getString("dept_id");
				int deptId2 = Integer.parseInt(deptIdString);
				if (deptId2 == Constants.DEPT_SALES) {
					System.out.println(Constants.DEPT_NAME_SALES);
				} else if (deptId2 == Constants.DEPT_ACCOUNTING) {
					System.out.println(Constants.DEPT_NAME_ACCOUNTING);
				} else if (deptId2 == Constants.DEPT_GENERAL) {
					System.out.println(Constants.DEPT_NAME_GENERAL);
				}
			}

			System.out.println("");
		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件登録
	 * 
	 * @param empName 社員名
	 * @param gender 性別
	 * @param birthday 生年月日
	 * @param deptId 部署ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 * @throws ParseException 
	 */
	public static void insertEmployee(String empName, String gender, String birthday, String deptId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT);

			// 入力値をバインド
			preparedStatement.setString(1, empName);
			preparedStatement.setInt(2, Integer.parseInt(gender));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			preparedStatement.setObject(3, sdf.parse(birthday), Types.DATE);
			preparedStatement.setInt(4, Integer.parseInt(deptId));

			// SQL文を実行
			preparedStatement.executeUpdate();

			// 登録完了メッセージを出力
			System.out.println(Constants.MSG_EMPLOYEE_REGISTERED);
		} finally {
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件更新
	 * 
	 * @param empId 社員ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 * @throws ParseException 
	 */
	public static void updateEmployee(String empId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE);

			System.out.print(Constants.MSG_INPUT_EMPLOYEE_NAME);
	        String employeeName = reader.readLine();

	        // 性別を入力
	        System.out.print(Constants.MSG_INPUT_GENDER);
	        String gender = reader.readLine();

	        // 誕生日を入力
	        System.out.print(Constants.MSG_INPUT_BIRTHDAY);
	        String birthday = reader.readLine();

	        // 部署IDを入力
	        System.out.print(Constants.MSG_INPUT_DEPARTMENT_ID);
	        String deptId = reader.readLine();

			// 入力値をバインド
			preparedStatement.setString(1, employeeName);
			preparedStatement.setInt(2, Integer.parseInt(gender));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			preparedStatement.setObject(3, sdf.parse(birthday), Types.DATE);
			preparedStatement.setInt(4, Integer.parseInt(deptId));
			preparedStatement.setInt(5, Integer.parseInt(empId));

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

		} finally {
			// クローズ処理
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件削除
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void deleteEmployee() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();
			String employeeId = reader.readLine();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_DELETE);

			// 社員IDをバインド
			preparedStatement.setInt(1, Integer.parseInt(employeeId));

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

			System.out.println(Constants.MSG_EMPLOYEE_DELETED);

		} catch (Exception e) {
			e.printStackTrace();

		}

		finally {
			// Statementをクローズ
			try {
				DBManager.close(preparedStatement);
				DBManager.close(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// DBとの接続を切断
		}
	}
}

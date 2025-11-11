package jp.co.sss.crud.util;

/**
 * メッセージ・固定値をまとめた定数クラス
 */
public class Constants {
	// メッセージ
	public static final String MSG_NO_RESULT = "該当者はいませんでした";
	public static final String MSG_EMPLOYEE_REGISTERED = "社員情報を登録しました";
	public static final String MSG_EMPLOYEE_DELETED = "社員情報を削除しました";
	public static final String MSG_EMPLOYEE_UPDATED = "社員情報を更新しました";
	public static final String MSG_INPUT_MENU = "メニュー番号を入力してください：";
	public static final String MSG_INPUT_EMPLOYEE_NAME = "社員名:";
	public static final String MSG_INPUT_DEPARTMENT_ID = "部署ID(1:営業部、2:経理部、3:総務部)：";
	public static final String MSG_INPUT_EMPLOYEE_ID_UPDATE = "更新する社員の社員IDを入力してください：";
	public static final String MSG_INPUT_EMPLOYEE_ID_DELETE = "削除する社員の社員IDを入力してください：";
	public static final String MSG_SYSTEM_END = "システムを終了します。";
	public static final String MSG_NO_RESULT_HEADER = "社員ID\t社員名\t性別\t生年月日\t部署名";
	public static final String MSG_INPUT_GENDER = "性別(0:回答しない, 1:男性, 2:女性, 9:その他):";
public static final String MSG_INPUT_BIRTHDAY = "生年月日(西暦年/月/日)：";

	//マジックナンバー（性別）
	public static final int GENDER_NONE = 0;
	public static final int GENDER_MALE = 1;
	public static final int GENDER_FEMALE = 2;
	public static final int GENDER_OTHER = 9;

	//マジックナンバー（メニュー）
	public static final int MENU_SHOW_ALL = 1;
	public static final int MENU_SEARCH_NAME = 2;
	public static final int MENU_SEARCH_DEPT = 3;
	public static final int MENU_INSERT = 4;
	public static final int MENU_UPDATE = 5;
	public static final int MENU_DELETE = 6;
	public static final int MENU_EXIT = 7;

	//マジックナンバー（部署ID）
	public static final int DEPT_SALES = 1;
	public static final int DEPT_ACCOUNTING = 2;
	public static final int DEPT_GENERAL = 3;

	//部署名 
	public static final String DEPT_NAME_SALES = "営業部";
	public static final String DEPT_NAME_ACCOUNTING = "経理部";
	public static final String DEPT_NAME_GENERAL = "総務部";
}

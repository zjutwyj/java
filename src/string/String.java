package string;

public class String {

	public static void main(String[] args) {
		String str1 = "abc";
		String str2 = "abc";
		String str3 = new String("abc");
		str1 == str2;   // true  常量池的优化
		str1 == str3;   // false
		str1 == str3.intern();  // true 常量池的引用
	}
}

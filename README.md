### 字符串优化处理
```java
String str1 = "abc";
String str2 = "abc";
String str3 = new String("abc");
str1 == str2;   // true  常量池的优化
str1 == str3;   // false
str1 == str3.intern();  // true 常量池的引用

```

### subString()方法的内存泄漏
```java
public String getSubString(String str, int begin, int end){
	return new String(str.substring(begin, end));// 返回新的字符串，防止溢出
}
```

### 字符串的分割和查找
```java
// 方法1 (用时3500ms)
"a;b,c:d".split("[;|,|:]"); // ["a", "b", "c", "d"]

// 方法2 (用时2500ms)
StringTokenizer st = new StringTokenizer(orgStr, ";");
while (st.hasMoreTokens()){
	st.nextToken();
}

// 方法3 (用时700ms)
String tmp = orgStr;
while(true){
	String splitStr = null;			
	int j = tmp.indexOf(";");		// 找分隔符的位置
	if (j<0) break;					// 没有分隔符存在
	splitStr = tmp.substring(0,j);	// 找到分隔符，截取子字符串
	tmp = tmp.substring(j+1);		// 剩下需要处理的字符串
}
```

### 判断字符串是否以"abc"开始
```java
// 方法1 (用时30ms)
orgStr.startsWith("abc");

// 方法2 (用时15ms)
int len = orgStr.length();
if (orgStr.charAt(0) == "a" && orgStr.charAt(1) == "b" && orgStr.charAt(2) == "c"){
	System.out.println("orgStr是以abc开始");
}
```

### 字符串相加优化
```java
StringBuffer sb = new StringBuffer(5888890);	// 线程安全，指定大小可以提升性能
StringBuilder sb = new StringBuilder(5888890);
```

### List接口
```java
List list = new ArrayList();  	// 线程不安全
List list = new Vector();		// 线程安全
List list = new LinkedList();
list.add("a"); 		// 在尾部插入      ArrayList -> 16ms  LinkedList -> 31ms
list.add(0, "a");	// 在任意位置插入	ArrayList -> 1547ms LinkedList -> 0ms
list.remove(0);		// 删除元素			ArrayList头部 -> 6203ms 中间3125ms 尾部16ms  LinkedList头部15ms 中间8781ms 尾部16ms

```
### 遍历列表
```java
// 方法1 ForEach操作 (用时63ms)
for (String s : list){
	tmp = s;
}
// 方法2 迭代器 (用时47ms)
for(Iterator<String> it = list.iterator(); it.hasNext();){
	tmp = it.next();
}
// 方法3 for循环 (用时31ms, 当为LinkedList时，用时无穷大)
for(int i = 0; i< list.size(); i++){
	tmp = list.get(i);
}
```

### Map接口
```java
Map map = new HashMap();		// 随机排序
Map map = new LinkedHashMap();	// 进入顺序排序
Map map = new TreeMap();		// 固有顺序排序
```

### TreeMap实例
```java
public static class Student implements Comparable<Student>{
	String name;
	int scope;

	public Student(String name, int s){
		this.name = name;
		this.scope = s;
	}

	// 这里是必需实现的，告诉TreeMap如何进行排序
	@Override
	public int compareTo(Student o){
		if (o.scope<this.scope){
			return 1;
		} else if (o.scope>this.scope){
			return -1;
		}else{
			return 0;
		}
	}
}
public static class StudentDetailInfo{
	Student s;
	public StudentDetailInfo(Student s){
		this.s = s;
	}
}
Map map = new TreeMap();
Student s1 = new Student("a", 70);
Student s2 = new Student("b", 50);
map.put(s1, new StudentDetailInfo(s1));
map.put(s2, new StudentDetailInfo(s2));
Map map1 = map.subMap(s1,s2); 		// 筛选出成绩介于s1与s2之间的学生
Map map2 = map.headMap(s1);			// 筛选出成绩低于s1的学生
Map map2 = map.tailMap(s2);			// 筛选出成绩高于s2的学生
```

### Set接口
```java
Set set = new HashSet();
Set set = new LinkedHashSet();
Set set = new TreeSet();

```

### 优化集合访问代码
```java
int count = 0;
String s = null;
int colsize = this.elementCount;	// 代替size()方法
for(int i= 0; i < colsize; i++){
	if ((s=(String) elementData[i]).indexOf("north") != -1 
	|| (s.indexOf("west") != -1) 
	|| (s.indexOf("south") != -1)){
		count++;
	}
}
```
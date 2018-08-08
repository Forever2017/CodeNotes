public boolean equals(String anObject) //区分大小写的相等判断
public boolean equalsIgnoreCase(String anotherString) //不区分大小写比较是否相等
public int compareTo(String anotherString) //比较两个字符串的大小

public boolean contains(String s) //查找指定的子字符串是否存在，JDK 1.5之后有
public int indexOf(String str) //从头查找指定字符串的位置，找不到返回-1
public int indexOf(String str, int fromIndex) //由指定位置向后查找字符串的位置，找不到返回-1
public int lastIndexOf(String str) //由后向前查找字符串的位置，找不到返回-1
public int l0astIndexOf(String str, int fromIndex) //从指定位置由后向前查找
public boolean startsWith(String prefix) //判断是否以指定的字符串开头
public boolean startsWith(String prefix, int toffset) //从指定位置判断是否以指定字符串开头，JDK 1.7
public boolean endsWith(String suffix) //判断是否以指定的字符串结尾

public String replaceAll(String regex, String replacement) //全部替换
public String replaceFirst(String regex, String replacement) //替换首个

public String substring(int beginIndex) //从指定位置截取到结尾
public String substring(int beginIndex, int endIndex) //截取指定范围的内容

public String[] split(String regex) //按照指定的字符串全拆分
public String[] split(String regex, int limit) //拆分为指定的长度

public boolean isEmpty() //判断是否为空字符串（""）
public int length() //取得字符串长度
public String trim() //去掉左右空格
public String toLowerCase()	 //将全部字符串转小写
public String toUpperCase() //将全部字符串转大写
public String intern() //入池
public String concat(String str) //字符串连接



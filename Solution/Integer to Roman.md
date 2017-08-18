Description：

Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.

Solution:

测试出来所有的结果都是正确的，提交时抛出 Time Limit Exceeded 异常，也就是超时了，代码如下：
```java
class SolutionIntegerToRoman{
	public String intToRoman(int num) {
		StringBuilder str = new StringBuilder();
		int res = 0;
		int power = 0;
		while(num != 0){
			res = num % 10;
			str.insert(0, iTOc((int)(res * Math.pow(10, power))) );
			num = num / 10;
			power += 1;
		}
        return str.toString();
    }
	
	public String iTOc(int x){
		String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C","CC","CCC","CD","D","DC","DCC","DCCC","CM","M","MM","MMM"};
		char[] romanInt = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,20,30,40,50,60,70,80,90,100,200,300,400,500,600,700,800,900,1000,2000,3000};
		for(int i = 0; i < roman.length; i++){
			if(x == romanInt[i]){
				return roman[i];
			}
		}
		
		return "";
	}
}
```

使用HashMap,大大提高了数字对应罗马数字的检索速度
```java
public class Solution {
    public String intToRoman(int num) {
		Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(0, "");
		map.put(1, "I");
		map.put(2, "II");
		map.put(3, "III");
		map.put(4, "IV");
		map.put(5, "V");
		map.put(6, "VI");
		map.put(7, "VII");
		map.put(8, "VIII");
		map.put(9, "IX");
		map.put(10, "X");
		map.put(20, "XX");
		map.put(30, "XXX");
		map.put(40, "XL");
		map.put(50, "L");
		map.put(60, "LX");
		map.put(70, "LXX");
		map.put(80, "LXXX");
		map.put(90, "XC");
		map.put(100, "C");
		map.put(200, "CC");
		map.put(300, "CCC");
		map.put(400, "CD");
		map.put(500, "D");
		map.put(600, "DC");
		map.put(700, "DCC");
		map.put(800, "DCCC");
		map.put(900, "CM");
		map.put(1000, "M");
		map.put(2000, "MM");
		map.put(3000, "MMM");
		
		
		StringBuilder str = new StringBuilder();
		int res = 0;
		int power = 0;
		while(num != 0){
			res = num % 10;
			str.insert(0, map.get( (int)(res * Math.pow(10, power)) ));
			num = num / 10;
			power += 1;
		}
        return str.toString();
    }
}
```
别人的答案，思路清晰
```java
public String intToRoman(int num) {
    String M[] = {"", "M", "MM", "MMM"};
    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    return M[num/1000] + C[(num%1000)/100]+ X[(num%100)/10] + I[num%10];
}
```
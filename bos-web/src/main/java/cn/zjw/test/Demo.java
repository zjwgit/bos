package cn.zjw.test;

import net.sf.ehcache.search.aggregator.Sum;

import java.io.*;

public class Demo {
	public static void main(String[] args) {
		/*File file = new File("E:\\te");
		Long sum = sum(file);
		System.out.println(sum);*/
		/*int sum1 = sum1(1, 0);
		System.out.println(sum1);*/

		int i = 0;
		if(i==0){
			System.out.println(1);
		}else if(i<1){
			System.out.println(2);
		}
	}
	public static Long sum(File file){
		Long sum=0l;
		File[] files = file.listFiles();
		for (File f: files){
			if (f.isFile()){
				  sum += f.length();
			}else {
				sum+=sum(f);
			}
		}
		return  sum;

	}
	public static int sum1(int start,int sum){
		if (start==10){
			return 10+sum;
		}
		System.out.println(start+sum);
		return start+sum1((start+1),(start+sum));
	}
	public void t(){
		try {
			BufferedReader reader =
					new BufferedReader(new InputStreamReader
							(new FileInputStream("filePath"),"UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

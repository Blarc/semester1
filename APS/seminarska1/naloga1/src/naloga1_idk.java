import java.io.*;
import java.util.Arrays;

public class naloga1_idk {
	
	static int vsota(int a) {
		if (a == 0) {
			return 0;
		}
		System.out.println(a);
		return vsota(a-1);
	}
	
	public static void main(String[] args) {
		System.out.println(vsota(5));
	}
}

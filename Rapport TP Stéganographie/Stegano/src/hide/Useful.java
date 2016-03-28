package hide;

public class Useful {
	
	// copier le contenu d'une chaine dans un tableau de char []
	public static void copyTo(String t1, char t2[]) {

		for (int i = 0; i < t1.length(); i++) {
			t2[i] = t1.charAt(i);
		}
	}

	// copier les pixels dans un tableau par 3.
	public static void copyToTab3(char t1[], char t2[], int pos) {

		for (int i = 0; i < 3; i++) {
			if (i + pos == t2.length) {
				if (i == 0) {
					t1[i] = 'X';
					t1[i + 1] = 'X';
					t1[i + 2] = 'X';
				}
				if (i == 1) {
					t1[i] = 'X';
					t1[i + 1] = 'X';
				}
				if (i == 2) {
					t1[i] = 'X';
				}
				break;
			} else {
				t1[i] = t2[i + pos];
			}
		}
	}

}

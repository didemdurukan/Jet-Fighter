import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {

	static String path;

	public FileOperation() {

		try {
			path = new java.io.File(".").getCanonicalPath(); // gets the path
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeScore(String player, int score) {

		FileWriter writer; // writes the score and current player to a file
		try {
			writer = new FileWriter("score.txt", true);
			writer.write(String.format("%s %d \n", player, score));
			writer.write("\r\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writeFile(String string1) {

		FileWriter writer;
		try { // writes user's info to a file
			writer = new FileWriter("username_password.txt", true);
			writer.write(String.format("%s\n", string1));
			writer.write("\r\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String readFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("username_password.txt"));
		try {
			StringBuilder sb = new StringBuilder(); // reads user's info
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}

	}

	public static String readScore() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("score.txt"));
		try {
			StringBuilder sb = new StringBuilder(); // reads the scores
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}

	}

}

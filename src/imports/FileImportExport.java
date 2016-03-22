package imports;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import swing_components.PopUpsAssistent;

public class FileImportExport {

	public static ArrayList<String> txtImport(String path){
		ArrayList<String> fileLines = new ArrayList<String>();

		try {
			FileReader reader = new FileReader(path);
			BufferedReader buffer = new BufferedReader(reader);

			String line = "";

			while((line = buffer.readLine())!= null){
				fileLines.add(line);
			}

			reader.close();
		} catch (FileNotFoundException e) {
			PopUpsAssistent.exibirMenssagem("Erro ao encontrar o arquivo", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			PopUpsAssistent.exibirMenssagem("Erro na Importação", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}


		return fileLines;
	}

	public static boolean txtExport(String path, ArrayList<String> textLines){
		FileWriter writer = null;
		boolean retorno = true;
		try {
			writer = new FileWriter(path);
		} catch (IOException e) {
			PopUpsAssistent.exibirMenssagem("Erro na criação do arquivo", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			retorno = !retorno;
		}

		PrintWriter printer = new PrintWriter(writer);

		for(String s : textLines){
			printer.println(s);
		}

		printer.close();
		try {
			writer.close();
		} catch (IOException e) {
			PopUpsAssistent.exibirMenssagem("Erro na Exportação", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			retorno = !retorno;
		}

		return retorno;
	}
}

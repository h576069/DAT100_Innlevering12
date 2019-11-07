package no.hvl.dat100.jplab12.oppgave5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.Integer.*;

import no.hvl.dat100.jplab12.common.TODO;
import no.hvl.dat100.jplab12.oppgave1.*;
import no.hvl.dat100.jplab12.oppgave2.*;
import no.hvl.dat100.jplab12.oppgave3.*;

import javax.swing.JOptionPane;

public class LesBlogg {

	private static String MAPPE = System.getProperty("user.dir") + "/src/no/hvl/dat100/jplab12/tests/";

	private static String TEKST = "TEKST";
	private static String BILDE = "BILDE";

	public static Blogg les(String filnavn) {
		try {
			File file = new File(MAPPE + filnavn);
			System.out.println(MAPPE);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			// Read the first line = the length of Blogg:
			String line = reader.readLine();
			int n       = Integer.parseInt(line);
			
			// Create samling:
			Blogg samling = new Blogg(n);
			
			while ((line = reader.readLine()) != null) {
				if (line.equals(TEKST)) {
					samling.leggTil(new Tekst(Integer.parseInt(reader.readLine()), reader.readLine(), reader.readLine(), Integer.parseInt(reader.readLine()), reader.readLine()));
				} else if (line.equals(BILDE)) {
					samling.leggTil(new Bilde(Integer.parseInt(reader.readLine()), reader.readLine(), reader.readLine(), Integer.parseInt(reader.readLine()), reader.readLine(), reader.readLine()));
				}
			}
			reader.close();
			
			return samling;
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the file: " + MAPPE + filnavn);
			return new Blogg(0);
		} catch (IOException e) {
			System.out.println("Could not read the file: " + MAPPE + filnavn);
			return new Blogg(0);
		}

	}
}

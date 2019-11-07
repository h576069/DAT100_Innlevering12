package no.hvl.dat100.jplab12.oppgave3;

import no.hvl.dat100.jplab12.common.TODO;
import no.hvl.dat100.jplab12.oppgave1.*;
import no.hvl.dat100.jplab12.oppgave2.*;
import no.hvl.dat100.jplab12.oppgave3.*;

public class Blogg {

	// TODO: objektvariable
	private Innlegg[] innleggtabell;
	private int nesteledig;

	public Blogg() {
		this.innleggtabell = new Innlegg[20];
		this.nesteledig = 0;
	}

	public Blogg(int lengde) {
		this.innleggtabell = new Innlegg[lengde];
		this.nesteledig = 0;
	}

	public int getAntall() {
		return this.nesteledig;
	}

	public Innlegg[] getSamling() {
		return this.innleggtabell;
	}

	public int finnInnlegg(Innlegg innlegg) {
		for (int i = 0; i < this.nesteledig; i++) {
			if (this.innleggtabell[i].erLik(innlegg)) {
				return i;
			}
		}
		return -1;
	}

	public boolean finnes(Innlegg innlegg) {
		if (finnInnlegg(innlegg) != -1) {
			return true;
		}
		return false;
	}

	public boolean ledigPlass() {
		if (this.nesteledig < this.innleggtabell.length) {
			return true;
		}
		return false;
	}

	public boolean leggTil(Innlegg innlegg) {
		if (ledigPlass() && !finnes(innlegg)) {
			this.innleggtabell[this.nesteledig] = innlegg;
			this.nesteledig++;
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		String txt = this.innleggtabell.length + "\n";
		for (int i = 0; i < this.nesteledig; i++) {
			txt += this.innleggtabell[i].toString();
		}
		return txt;
	}

	// valgfrie oppgaver nedenfor

	public void utvid() {
		Innlegg[] tabell2 = new Innlegg[2 * this.innleggtabell.length];

		for (int i = 0; i < this.innleggtabell.length; i++) {
			tabell2[i] = this.innleggtabell[i];
		}

		this.innleggtabell = tabell2;
	}

	public boolean leggTilUtvid(Innlegg innlegg) {
		if (this.nesteledig >= this.innleggtabell.length) {
			utvid();
		}
		return leggTil(innlegg);

	}

	public boolean slett(Innlegg innlegg) {
		// Finner posisjonen til innlegget:
		int posisjon = finnInnlegg(innlegg);
		search("ord");
		if (posisjon < 0) {
			// Innlegget finnes ikke:
			return false;
		} else {
			// Minker neste ledige med en siden vi skal kutte en fra tabellen:
			this.nesteledig--;

			// Erstatter innlegget i posisjonen med det siste innlegget:
			this.innleggtabell[posisjon] = this.innleggtabell[this.nesteledig];
			// Skriver siste innlegget til null:
			this.innleggtabell[this.nesteledig] = null;
			return true;

		}
	}

	public int[] search(String keyword) {
		// returnere en tabell av id'er på alle innlegg i bloggen der teksten inneholder strengen angitt med parameteren ord.
		int[] allIds = new int[this.nesteledig];
		int   count  = 0;
		
		for (int i = 0; i < this.nesteledig; i++) {
			if (this.innleggtabell[i] instanceof Tekst) {
				Tekst innlegg = (Tekst) this.innleggtabell[i];
				String tekst  = innlegg.getTekst();
				if (tekst.contains(keyword)) {
					allIds[count] = innlegg.getId();
					count++;
				}
			}
		}
		int[] newIds = new int[count];
		if (count != this.nesteledig) {
			for (int j = 0; j < count; j++) {
				newIds[j] = allIds[j];
			}
			return newIds;
		}
		
		return allIds;
	}
	
}
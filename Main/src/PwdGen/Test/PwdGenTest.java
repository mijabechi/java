package PwdGen.Test;

import static org.junit.Assert.*;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

import PwdGen.PwdGen;


public class PwdGenTest {

	@Test
	public void shouldCreateLowerLetters() {
		PwdGen generator = new PwdGen();
		ArrayList<String> options =  new ArrayList<String> (Arrays.asList("-L","-Size:100"));
		List<String> chain = generator.createPassword(options);
		boolean validation = Pattern.matches("[a-z]+", chain.get(0));
        assertTrue(validation);
	}
	@Test
	public void shouldCreateLowerLettersWithExclude() {
		PwdGen generator = new PwdGen();
		ArrayList<String> options =  new ArrayList<String> (Arrays.asList("-L","-A","-Size:100"));
		List<String> chain = generator.createPassword(options);
		boolean validation = Pattern.matches("[a-z&&[^il]]+", chain.get(0));
        assertTrue(validation);
	}
	
	@Test
	public void shouldCreateOnlyUpperLetters() {
		PwdGen generator = new PwdGen();
		ArrayList<String> options =  new ArrayList<String> (Arrays.asList("-U","-Size:100"));
		List<String> chain = generator.createPassword(options);
		boolean validation = chain.get(0).matches("[A-Z]+");
        assertTrue(validation);
	}
	
	@Test
	public void shouldCreateOnlychain() {
		PwdGen generator = new PwdGen();
		ArrayList<String> options = new ArrayList<String> (Arrays.asList("-N","-Size:100"));
		List<String> chain = generator.createPassword(options);
		boolean validation = chain.get(0).matches("[0-9]+");
        assertTrue(validation);
	}
	
	@Test
	public void shouldCreateOnlySymbols() {
		PwdGen generator = new PwdGen();
		ArrayList<String> options =  new ArrayList<String> (Arrays.asList("-S","-Size:100"));
		List<String> chain = generator.createPassword(options);
		boolean validation = chain.get(0).matches("[$%&()=#]+");
        assertTrue(validation);
	}
	
	@Test
	public void shouldCreateDefaultLength() {
		PwdGen generator = new PwdGen();
		ArrayList<String> options =  new ArrayList<String> ();
		List<String> chain = generator.createPassword(options);
        assertEquals(16,chain.get(0).length());
	}
	
	@Test
	public void shouldCreatePassWithSpecificSize() {
		int size = 20;
		PwdGen generator = new PwdGen();
		ArrayList<String> options =  new ArrayList<String> (Arrays.asList("-Size:20"));
		List<String> chain = generator.createPassword(options);
        assertEquals(size,chain.get(0).length());
	}
	
	@Test
	public void shouldCreateDefaultPass() {
		PwdGen generator = new PwdGen();
		ArrayList<String> options = new ArrayList<String>();
		List<String> chain = generator.createPassword(options);
        assertEquals(16,chain.get(0).length());
        boolean validation = chain.get(0).matches("[a-zA-Z0-9]+");
        assertTrue(validation);
	}
	
	@Test
	public void shouldCreatePassWithchainAndLowerCase() {
		PwdGen generator = new PwdGen();
		ArrayList<String> options =  new ArrayList<String> (Arrays.asList("-N", "-L"));
		List<String> chain = generator.createPassword(options);
        assertEquals(16,chain.get(0).length());
        boolean validation = chain.get(0).matches("[a-z0-9]+");
        assertTrue(validation);
	}
	
	@Test
	public void shouldCreatePassWithInvalidSize() {
		PwdGen generator = new PwdGen();
		ArrayList<String>options =  new ArrayList<String> (Arrays.asList("-Size:-1"));
		List<String> chain = generator.createPassword(options);
       
        StringBuilder help = new StringBuilder();
		help.append("dir \\?");
        assertEquals(help.toString().length(),chain.get(0).length());
	}
	
	@Test
	public void shouldCreatePassWith_Copy() {
		PwdGen generator = new PwdGen();
		ArrayList<String>options =  new ArrayList<String> (Arrays.asList("-Copy"));
		List<String> chain = generator.createPassword(options);
		assertEquals(chain.get(0),paste());
        assertEquals(16,chain.get(0).length());
        boolean validation = chain.get(0).matches("[a-zA-Z0-9]+");
        assertTrue(validation);
	}
	private String paste() {
		String paste = "";
		try {
			  Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	        Transferable t = cb.getContents(null);
	        if (t.isDataFlavorSupported(DataFlavor.stringFlavor))
	           paste = t.getTransferData(DataFlavor
	              .stringFlavor).toString();
	     } catch (UnsupportedFlavorException | IOException ex) {
	         System.out.println("");
	     }
		return paste;
	}
	
	@Test
	public void shouldCreate3Pass() {
		PwdGen generator = new PwdGen();
		ArrayList<String>options =  new ArrayList<String> (Arrays.asList("-T:3"));
		List<String> chain = generator.createPassword(options);	
		for(String pass: chain) {
			boolean validation = pass.matches("[a-zA-Z0-9]+");
	        assertTrue(validation);
		}
        assertEquals(3,chain.size());
	}
	
	@Test
	public void shouldCreateSize8_S_Copy() {
		PwdGen generator = new PwdGen();
		ArrayList<String> options =  new ArrayList<String> (Arrays.asList("-Size:8","-S","-Copy"));
		List<String> chain = generator.createPassword(options);	
		for(String pass: chain) {
			boolean validation = pass.matches("[$%&()=#]+");
	        assertTrue(validation);
	        assertEquals(pass,paste());
	        assertEquals(8,pass.length());
		}
        assertEquals(1,chain.size());
	}
	
	@Test
	public void shouldCreatePassWith_UpperCase_chain_Ambigous() {
		PwdGen generator = new PwdGen();
		ArrayList<String> options =  new ArrayList<String> ( Arrays.asList("-U", "-N", "-A"));
		List<String> chain = generator.createPassword(options);
        assertEquals(16,chain.get(0).length());
        boolean validation = chain.get(0).matches("[A-Z0-9&&[^iIlL1oO0a-z]]+");
        assertTrue(validation);
	}
	
	@Test
	public void shouldCreateT5_A_Size10() {
		PwdGen generator = new PwdGen();
		ArrayList<String> options =  new ArrayList<String> (Arrays.asList("-T:5","-A","-Size:10"));
		List<String> chain = generator.createPassword(options);	
		for(String pass: chain) {
			boolean validation = pass.matches("[A-Za-z0-9&&[^iIlL1oO0]]+");
	        assertTrue(validation);
	        assertFalse(pass == paste());
	        assertEquals(10,pass.length());
		}
        assertEquals(5,chain.size());
	}
	
	@Test
	public void shouldCreateS_E_N_Copy() {
		PwdGen generator = new PwdGen();
		ArrayList<String> options =  new ArrayList<String> (Arrays.asList("-S","-E:=()$%","-N","-Copy"));
		List<String> chain = generator.createPassword(options);	
		for(String pass: chain) {
			boolean validation = pass.matches("[$%&()=#0-9&&[^=()$%]]+");
	        assertTrue(validation);
	      
	        assertEquals(16,pass.length());
		}
		assertEquals(chain.get(0),paste());
        assertEquals(1,chain.size());
	}
	
	@Test
	public void shouldCreatePassWith_Exclude() {
		PwdGen generator = new PwdGen();
		ArrayList<String>options =  new ArrayList<String> ( Arrays.asList("-E:abc123DEF"));
		List<String> chain = generator.createPassword(options);
        assertEquals(16,chain.get(0).length());
        boolean validation = chain.get(0).matches("[a-zA-Z0-9&&[^abc123DEF]]+");
        assertTrue(validation);
	}
	
	@Test
	public void shouldUseHelp() {
		PwdGen generator = new PwdGen();
		ArrayList<String>options =  new ArrayList<String> ( Arrays.asList("-?"));
		List<String> chain = generator.createPassword(options);
		StringBuilder help = new StringBuilder();
		help.append("usage: KeyGen [-Size:Value] [-U upperCase] [-L lowerCase] [-N numbers]");
		help.append("\n");
		help.append("		[-S symbols] [-A exclude Ambigous] [-E:value exclude value] [-T:value total times]");
		help.append("\n");
		help.append("		[-C or -Copy copy the last pass generated]");
		help.append("\n");
		help.append("examples: PwdGen –S –E:=()$% -N –Copy, PwdGen –Size:8 –S –Copy, PwdGen –U –N –A");
		help.append("\n");
		help.append("		PwdGen –T:5 –A –Size:10, PwdGen –E:abc123DEF2");
        assertEquals(help.toString().length(),chain.get(0).length());
	}
			
	@Test
	public void shouldShowMessageWhenIsAnInvalidOption() {
		PwdGen generator = new PwdGen();
		ArrayList<String>options =  new ArrayList<String> ( Arrays.asList("-M"));
		List<String> chain = generator.createPassword(options);
		StringBuilder help = new StringBuilder();
		help.append("dir \\?");
        assertEquals(help.toString().length(),chain.get(0).length());
	}

}

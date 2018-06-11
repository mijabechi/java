package KeyGen;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PwdGen {

	public static void main(String[] args) {
		
		ArrayList<String> options = new ArrayList<String>();
		for(int i = 0; i < args.length; i++) {
			options.add(args[i]);
        }
		PwdGen program = new PwdGen();
		List<String> result = program.createPassword(options);
		
		for (String string : result) {
			System.out.println(string);
		}
	}
	
	public List<String> createPassword(ArrayList<String> options) {
		
		List<String> list = new ArrayList<String>();
		String plainOptions = options.toString();
		if(isValidOption(options)) {
			if(needHelp(plainOptions))
				list.add(callHelp());
			else {
				int times = getNumberOfTimes(options);
				callNTimesGeneratePassword(list,times,options);
			}
		}else
			list.add("dir /?");
		
		return list;
	}
	
	private void callNTimesGeneratePassword(List<String> list, int times, ArrayList<String> options) {
		if(list.size() != times)
		{
			String pass = generatePassword(options);
			list.add(pass);
			if(list.size() == times)
				copy(pass, options);
			else
				callNTimesGeneratePassword(list, times, options);
		}	
	}
	
	private String generatePassword(ArrayList<String> options) {
		StringBuilder sb = new StringBuilder();
		int size = buildSize(options);
		HashMap<String, String> patterns = buildPatterns(options);
		autogeneratePass(sb, 1, size, patterns, options);
		return sb.toString();
	}

	private void autogeneratePass(StringBuilder sb, int count, int size, HashMap<String,String> patterns, List<String> options) {
		if(validateSize(count, size))
		{			
			if(!options.contains("-U") && !options.contains("-L") && !options.contains("-N") && !options.contains("-S") ) {
				count = loadDefaultValues(sb, count, size, patterns);
			}else{
				count = eveluateOptions(sb, count, size, patterns, options);
			}
			autogeneratePass(sb, count, size, patterns, options);
		}
	}
	
	private int getNumberOfTimes(ArrayList<String> options) {
		int times = 1;
		String plainOptions = options.toString();
		
		String exclude = new String("-T");
		if (plainOptions.contains(exclude)) {
			int value = Integer.parseInt(getValueOption(options, exclude));
			if(value > 1)
				times = value;
		}
		return times;
	}

	private String generateData(String pattern, int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(pattern.charAt(random.nextInt(pattern.length())));
		}
		return sb.toString();
	}
	
	private String callHelp() {
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
        return help.toString();
	}

	private boolean needHelp(String plainOptions) {
		return plainOptions.contains("-?") ;
	}

	private boolean isValidOption(List<String> list) {
		
		if(list.isEmpty())
			return true;
		for (String string : list) {
			if (string.matches("-\\?") || string.matches("-A") || string.matches("-L") || string.matches("-N") || string.matches("-S") || string.matches("-A") || string.matches("-E:.+") || string.matches("-?") || string.matches("-T:\\d+") || string.matches("-Copy") || string.matches("-C")|| string.matches("-Size:\\d+")) {
				return true;
			}	
		}
		
		return false;
	}

	private int buildSize(ArrayList<String> options) {
		int size = 16;
		String plainOptions = options.toString();
		String exclude = new String("-Size");
		if (plainOptions.contains(exclude)) {
			int value = Integer.parseInt(getValueOption(options, exclude));
			if(value > 1)
				size = value;
		}
		return size;
	}

	private int eveluateOptions(StringBuilder sb, int count, int size, HashMap<String, String> patterns,
			List<String> options) {
		if(options.contains("-U") ) {
			callGeneratePattern(sb,count++, size, patterns.get("upperCase"));
		}
		if(options.contains("-L") ) {
			callGeneratePattern(sb,count++, size, patterns.get("lowerCase"));
		}
		if(options.contains("-N")) {
			callGeneratePattern(sb,count++, size, patterns.get("numbers"));
		}
		if(options.contains("-S")) {
			callGeneratePattern(sb,count++, size, patterns.get("symbols"));
		}
		return count;
	}

	private int loadDefaultValues(StringBuilder sb, int count, int size, HashMap<String, String> patterns) {
		callGeneratePattern(sb,count++, size, patterns.get("upperCase"));
		callGeneratePattern(sb,count++, size, patterns.get("lowerCase"));
		callGeneratePattern(sb,count++, size, patterns.get("numbers"));
		return count;
	}

	private void callGeneratePattern(StringBuilder sb, int count, int size, String pattern) {
		if (validateSize(count, size)) {
			sb.append(generateData(pattern,1));
		}
	}

	private boolean validateSize(int count, int size) {
		return count <= size;
	}

	private HashMap<String, String> buildPatterns(ArrayList<String> options) {

		HashMap<String, String> patterns = new HashMap<String, String>();
		
		String excludePattern = buildExcludePattern(options);

		String lowerCase = getPattern(excludePattern, "abcdefghijklmnopqrstuvwxyz");
		String upperCase = getPattern(excludePattern, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String numbers = getPattern(excludePattern, "0123456789");
		String symbols = getPattern(excludePattern, "$%&()=#");

		patterns.put("lowerCase", lowerCase);
		patterns.put("upperCase", upperCase);
		patterns.put("numbers", numbers);
		patterns.put("symbols", symbols);
		return patterns;
	}

	private String buildExcludePattern(ArrayList<String> options) {

		String result = " ";
		String plainOptions = options.toString();
		if (plainOptions.contains("-A")) {
			result = "iILl1oO0";
		}
		String exclude = new String("-E");
		if (plainOptions.contains(exclude)) {
			result += getValueOption(options, exclude);
		}
		return result;
	}

	private String getValueOption(ArrayList<String>options, String option) {
		List<String> result = options.stream().filter(mapper -> mapper.contains(option)).collect(Collectors.toList());
		String[] pattern = result.get(0).split(":");
		return pattern[1];
	}

	private String getPattern(String excludePattern, String alphabet) {
		alphabet = alphabet.replaceAll("[" + excludePattern + "]", "");
		return alphabet;
	}
	
	private void copy(String pass, ArrayList<String> options) {
		if(validateCopy(options)){
			StringSelection selection = new StringSelection(pass);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);
		}
	}

	private boolean validateCopy(ArrayList<String> options) {
		return options.contains("-Copy") || options.contains("-C");
	}

}

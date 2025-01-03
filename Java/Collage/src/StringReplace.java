public class StringReplace {
    private static String replace(String inputString, String pattern, String replace){
        String replacedSting = "";
        int patternLength = pattern.length();
        int inputStringLength = inputString.length();


        for (int i = 0; i < inputString.length(); i++){
            if (i + patternLength <= inputStringLength && pattern.equals(inputString.substring(i, i + patternLength))){
                replacedSting += replace;
                i += pattern.length() - 1;
            }else
                replacedSting += inputString.charAt(i);
        }

        return replacedSting;
    }

    public static void main(String[] args) {
        System.out.println(replace("This this is a this", "this", "test,"));
    }
}

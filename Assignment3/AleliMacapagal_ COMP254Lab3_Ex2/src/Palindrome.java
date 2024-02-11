public class Palindrome {
    public static boolean isPalindrome(String s) {
        // Base cases
        if (s.length() <= 1) {
            return true; // An empty string or a string with one character is a palindrome
        } else {
            // Check if the first and last characters are equal
            if (s.charAt(0) == s.charAt(s.length() - 1)) {
                // If the first and last characters are equal, check the substring excluding these characters
                return isPalindrome(s.substring(1, s.length() - 1));
            } else {
                return false; // If the first and last characters are not equal, it's not a palindrome
            }
        }
    }

    public static void main(String[] args) {
        // Test the isPalindrome method true
        String str = "radar";
        if (isPalindrome(str))
        {
            System.out.println(str + " is a palindrome: ");
        }else
        {
            System.out.println(str + " is NOT a palindrome: ");
        }

        // Test the isPalindrome method false
        str = "word";
        if (isPalindrome(str))
        {
            System.out.println(str + " is a palindrome: ");
        }else
        {
            System.out.println(str + " is NOT a palindrome: ");
        }

    }
}

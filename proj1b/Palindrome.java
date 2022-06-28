public class Palindrome {
    /** given a String, wordToDeque should return a Deque where the characters
     *  appear in the same order as in the String. */
    public Deque<Character> wordToDeque(String word){
        Deque<Character> chdq = new ArrayDeque<>();
        for(int i = 0; i < word.length(); i++){
            char item = word.charAt(i);
            chdq.addLast(item);
        }
        return chdq;
    }

    /** return true if the given word is a palindrome, and false otherwise. */
    public boolean isPalindrome(String word){
        Deque<Character> dq = wordToDeque(word);
        if(dq.size() == 0 || dq.size() == 1){
            return true;
        }
        while(!dq.isEmpty()){
            if(dq.removeFirst() != dq.removeLast()){
                return false;
            }
        }
        return true;
    }

    /** return true if the word is a palindrome according to the character comparison test
     *  @Overload
     */
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> dq = wordToDeque(word);
        if(dq.size() == 0 || dq.size() == 1){
            return true;
        }
        while((!dq.isEmpty()) && dq.size() > 1){
            if(!cc.equalChars(dq.removeFirst(), dq.removeLast())){
                return false;
            }
        }
        return true;
    }
}

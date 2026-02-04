public class foreach {
    public static void main(String[] args) {
        String[] tab = {"un","deux","caca","pikachu"};

        for (String el : tab) {
            if (el.length()<4) el = "aaaaa";
            System.out.println(el);
        }
    }
}

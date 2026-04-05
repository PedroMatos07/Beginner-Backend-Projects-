package App;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("------Welcome to github user activity------");
        Scanner sc = new Scanner(System.in);

        String nameUser = sc.nextLine();

        GithubActivity user = new GithubActivity(nameUser);
        user.fetchUser();

    }
}

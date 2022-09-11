import java.util.Scanner;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();
        int hours;
        int minutes;
        int seconds_;
        hours = seconds / 3600;
        if (hours > 23){
            hours %= 24;
        }
        int tmp1 = seconds % 3600;
        minutes = tmp1 / 60;
        seconds_ = tmp1 % 60;
        String h = String.valueOf(hours);
        String m = String.valueOf(minutes);
        String s = String.valueOf(seconds_);
//        if (h.length() == 1){
//            String tmp = h;
//            h = "0";
//            h += tmp;
//        }
        if (m.length() == 1){
            String tmp = m;
            m = "0";
            m += tmp;
        }
        if (s.length() == 1){
            String tmp = s;
            s = "0";
            s += tmp;
        }
        System.out.println(h+ ":" + m + ":" + s);




    }
}

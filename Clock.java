import java.util.*;

class Clock {
    static Scanner sc =  new Scanner(System.in);
    public static void main(String args[]) {
        Date d = new Date();
        
        int hours   = d.getHours();
        int minutes = d.getMinutes();
        int seconds = d.getSeconds();
        System.out.println("Hours : " + hours + "\tMinutes : " + minutes + "\tSeconds : " + seconds );
        
        System.out.print("Enter the no of Clients : ");
        int n = sc.nextInt();
        
        int hrs[] = new int[n];
        int mns[] = new int[n];
        int scs[] = new int[n];
        int Cli[] = new int[n];
        
        System.out.println("Enter the Time of Clients's machine in Hours:minutes:Seconds Format");
        for(int i = 0;  i < hrs.length; i++) {
            hrs[i] = sc.nextInt();
            mns[i] = sc.nextInt();
            scs[i]= sc.nextInt();
        }
        
        System.out.println(n + " Client's machine Time are Recorded...\n\nWait for Syncronisation...");
        
        //Logic
        
        int ServerSec = (hours*3600) + (minutes*60) + (seconds*1), ClientSec = 0;
        
        for(int i=0; i < hrs.length; i++) {
            Cli[i] = (hrs[i]*3600) + (mns[i]*60) + (scs[i]*1);
            ClientSec += Cli[i];
        }
        
        //Server
        int TotalTime = (ServerSec + ClientSec)/(n +1);
        
        int SyncHrs = TotalTime/3600;
        int SyncMns = (TotalTime%3600)/60;
        int SyncScs = (TotalTime%3600)%60;
        
        int Diff = TotalTime - ServerSec;
        hours += Diff/3600;
        minutes += (Diff%3600)/60;
        seconds += (Diff%3600)%60;

        System.out.println("Add/Red time is : Hours - " + Diff/3600 + "\tMinutes - " + (Diff%3600)/60 + "\tSeconds - " + (Diff%3600)%60 + "\n");
        System.out.println("Syncronised Time is : Hours - " + SyncHrs + "\tMinutes - " + SyncMns + "\tSeconds - " + SyncScs );

        //Client
        for(int i = 0; i < hrs.length; i++) {
            int CDiff = TotalTime - Cli[i];
            SyncHrs = CDiff/3600;
            SyncMns = (CDiff%3600)/60;
            SyncScs = (CDiff%3600)%60;
            
            System.out.println("Client-" + (i +1) + " Needs to Add / Reduce time ->\t" + SyncHrs + " : " + SyncMns + " : " + SyncScs );
        }
    }
}

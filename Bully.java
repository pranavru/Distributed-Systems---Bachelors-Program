import java.util.*;

class Bully {
    static Scanner sc = new Scanner(System.in);
    public static void main(String args[]) {
        int n = 0, j = 1, i = 0, crash = 0, recover = 0, cordinator = 0, ch = 0, initiator = 0;
        System.out.println("Enter the no of Processes");
        n = sc.nextInt();
        do {
            if(n < 2) {
                System.out.println("Enter processes greater than 1");
                n = sc.nextInt();
            }
        }while(n <=2);
        
        int pro[] = new int[n];
        String status[] = new String[n];
        String statusInit[] = new String[n];
        
        for(i = 0; i < n; i++, j++) {
            pro[i] = j;
            status[i] = statusInit[i] = "Alive";
        }
        
        System.out.println("1. To Crash a Process\n2. To Recover a Process\n3. Display Coordinator\n4. To Exit");
        cordinator = n;
        System.out.println(cordinator);
        do {
            System.out.println("Enter your Choice");
            ch = sc.nextInt();
            
            switch(ch) {
                case 1:
                    
                    System.out.println("Process\tStatus");
                    for(i = 0; i < n; i++)
                        System.out.println(pro[i] + "\t" + status[i]);
                    System.out.println("\nEnter the Process you want to Crash");
                    crash = sc.nextInt();
                    if(crash-1 < n) {
                        if((crash) == cordinator){
                            statusInit[crash -1] = "Out";
                            status[crash -1] = "Dead";
                            System.out.print("----------Cordinator " + cordinator +" is Crashed---------- \nEnter the Initiator to start the Election: ");
                            initiator = sc.nextInt();
                            for(i = (initiator -1); i < status.length; i++) {
                                if(status[i] == "Alive") {
                                    statusInit[i] = "In";
                                    System.out.println("Process " + pro[i] + " having Status:  " + status[i] + " is " + statusInit[i]);
                                    cordinator = pro[i];
                                }
                            }
                            System.out.println("New Cordinator: " + cordinator);
                        }
                        else {
                            statusInit[crash -1] = "Out";
                            status[crash -1] = "Dead";
                            System.out.println("The Process " + (crash) + " has crashed" );
                            
                            System.out.println();
                            System.out.println("Process\tStatus");
                            for(i = 0; i < n; i++)
                                System.out.println(pro[i] + "\t" + status[i]);
                        }
                    }
                    else {
                        System.out.println("The Process you want to Crash is unavailable...");
                    }
                    break;
                case 2:
                    System.out.println("Process\tStatus");
                    for(i = 0; i < n; i++) {
                        if(status[i] == "Dead")
                            System.out.println(pro[i] + "\t" + status[i]);
                    }
                    
                    System.out.println("\nEnter the Process you want to Recover");
                    recover = sc.nextInt();
                    if(recover-1 < n) {
                        statusInit[recover -1] = "In";
                        status[recover -1] = "Alive";
                        System.out.println("The Process " + (recover) + " has recovered" );
                        
                        System.out.println();
                        System.out.println("Process\tStatus");
                        for(i = 0; i < n; i++)
                            System.out.println(pro[i] + "\t" + status[i]);
                        if(cordinator < pro[recover-1]) {
                            cordinator = pro[recover-1];
                            System.out.println("New Cordinator is: " + cordinator);
                            break;
                        }
                    }
                    else {
                        System.out.println("The Process you want to Crash is unavailable...");
                    }
                    break;
                case 3:
                    System.out.println("The Cordinator is: " + cordinator);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Enter a vaid choice");
                    break;
            }
        }while(ch != 4);
        
    }
}

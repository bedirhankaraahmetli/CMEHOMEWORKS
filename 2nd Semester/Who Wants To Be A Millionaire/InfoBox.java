import enigma.console.Console;

public class InfoBox implements Runnable {
    private Console cn;
    private int money = 0;
    private boolean is50used = false;
    private boolean isdoubledipused = false;
    private boolean thread_interrupt = false;
    private boolean is_time_run_out = false;

    

    public InfoBox(Console cn){
        this.cn = cn;
    }

    public void run(){
        int seconds = 20;
        String lifeline1 = "";
        String lifeline2 = "";
        int cursor_x = 0;
        int cursor_y = 7;

        while(true){ //Infinte loop to keep thread alive
            seconds = 20;
            is_time_run_out = false;
            try{
                Thread.sleep(100); //CPU overrides other proccess if this thread doesn't sleep. Don't Remove!
            }
            catch(InterruptedException e){
            }
            if(thread_interrupt){
                cn.getTextWindow().setCursorPosition(0, 7);
                while(thread_interrupt && !is_time_run_out){
                    if (!is50used) {
                        lifeline1 = "%50";
                    }else {
						lifeline1 = "";
					}
                    if (!isdoubledipused) {
                        lifeline2 = "Double Dip";
                    }else {
						lifeline2 = "";
					}
                    cursor_x = cn.getTextWindow().getCursorX();
                    cursor_y = cn.getTextWindow().getCursorY();
                    cn.getTextWindow().setCursorPosition(30,30);
                    System.out.print("------------------------------");
                    cn.getTextWindow().setCursorPosition(30,31);
                    System.out.print("-                            -");                    
                    cn.getTextWindow().setCursorPosition(30,32);
                    System.out.print("-     Money:"+money+",000             -");                   
                    cn.getTextWindow().setCursorPosition(30,33);
                    System.out.print("-     Remaining Time:"+seconds+"      -");
                    cn.getTextWindow().setCursorPosition(30,34);
                    System.out.print("-     "+ lifeline1 +"                    -");
                    cn.getTextWindow().setCursorPosition(30,35);
                    System.out.print("-     "+ lifeline2 +"             -");
                    cn.getTextWindow().setCursorPosition(30,36);
                    System.out.print("-                            -");
                    cn.getTextWindow().setCursorPosition(30,37);
                    System.out.print("------------------------------");
                    cn.getTextWindow().setCursorPosition(30, 39);
                    System.out.print("To use %50 type '%50' ");
                    cn.getTextWindow().setCursorPosition(30, 40);
                    System.out.print("To use double dip type 'double (first choice) ' ex. 'double a' ");
                    cn.getTextWindow().setCursorPosition(cursor_x, cursor_y); 

                    
                    try{
                        Thread.sleep(1000);
                        seconds--;
                    }
                    catch(InterruptedException e){

                    }
                    if (seconds == -1){
                        FileOperations.writeToFile("temp.txt", "is_time_run_out#1");
                        is_time_run_out = true; //Send the run out info to the main func
                        thread_interrupt = false;
                        System.out.print("Time has run out, please type 'c' to continue");
                        break;
                    }                
                }
            }
            
            
        }
        
    }

    public void setMoney(int money){
        this.money = money;
    }

    public int getMoney(){
        return money;
    }

    public void setIs50Used(boolean is50used){
        this.is50used = is50used;
    }

    public boolean getIs50Used(){
        return is50used;
    }

    public void setIsDoubleDipUsed(boolean isdoubledipused){
        this.isdoubledipused = isdoubledipused;
    }

    public boolean getIsDoubleDipUsed(){
        return isdoubledipused;
    }

    public void setThreadInterrupt(boolean thread_interrupt){
        this.thread_interrupt = thread_interrupt;
    }

    public boolean getThreadInterrupt(){
        return thread_interrupt;
    }

    public void setIsTimeRunOut(boolean is_time_run_out){
        this.is_time_run_out = is_time_run_out;
    }

    public boolean getIsTimeRunOut(){
        return is_time_run_out;
    }
}
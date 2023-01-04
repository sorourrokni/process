import java.util.ArrayList;

public class Process {

    private final String processID;
    private State state;
    private int pc;
    private String ir;
    private int acc;
    private int temp;

    private final ArrayList<String> instructions;

    public Process(String processID, ArrayList<String> instructions) {
        this.processID = processID;
        this.pc = 0;
        this.state = State.ready;
        this.instructions = instructions;
    }

    public void resetPc() {
        this.pc = 0;
    }

    public String getProcessID() {
        return processID;
    }

    public State getState() {
        return state;
    }

    public void run() {

        String[] arr = instructions.get(pc).split(" ");

        String val = arr[0];
        int num = Integer.parseInt(arr[1]);

        switch (val) {

            case "load" -> {
                ir = val + " " + num;
                load(num);
            }
            case "add" -> {
                ir = val + " " + num;
                add(num);
            }
            case "sub" -> {
                ir = val + " " + num;
                sub(num);
            }
            case "mul" -> {
                ir = val + " " + num;
                mul(num);
            }
        }
    }

    public void load(int num) {
        this.state = State.running;
        this.temp = num;
        this.acc = num;
        ++pc;
        this.state = State.ready;
    }

    public void add(int num) {
        this.state = State.running;
        this.temp = num;
        this.acc += temp;
        ++pc;
        this.state = State.ready;
    }

    public void sub(int num) {
        this.state = State.running;
        this.temp = num;
        this.acc -= temp;
        ++pc;
        this.state = State.ready;
    }

    public void mul(int num) {
        this.state = State.running;
        this.temp = num;
        this.acc *= temp;
        ++pc;
        this.state = State.ready;
    }

    public void showContext() {
        System.out.println("-------------------------------------");
        System.out.println("Process ID : " + this.processID);
        System.out.println("Instruction Register : " + ir + "\n");
        System.out.println("Accumulator : " + acc + "\t\t" + "Temp : " + temp);
        System.out.println("Program Counter : " + pc + "\t\t" + "State : " + state);
        System.out.println("-------------------------------------");
    }

    public void block() {
        this.state = State.blocked;
    }

    public void unBlock() {
        this.state = State.ready;
    }
}

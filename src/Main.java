import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<String> testCase = fileReader(new File("src/testcase.txt"));
        Map<String, Process> processes = new HashMap<>();

        Process process;
        int counter = 0;

        for (String x : testCase) {

            System.out.println(++counter +")"+ x);

            String[] instructions = x.split(" ");
            String ID = instructions[1];

            switch (instructions[0]) {
                case "create_process" -> {
                    processes.put(ID, create_process(ID, instructions[2]));
                }
                case "run_process" -> {
                    if (processes.containsKey(ID)) {
                        process = processes.get(ID);
                        if (process.getState().equals(State.ready)) {
                            process.run();
                        } else System.out.println("This process is blocked!\n");
                    } else System.out.println("This process is killed!\n");
                }
                case "show_context" -> {
                    if (processes.containsKey(ID)) {
                        process = processes.get(ID);
                        if (process.getState().equals(State.ready)) {
                            process.showContext();
                        } else System.out.println("This process is blocked!\n");
                    } else System.out.println("This process is killed!\n");
                }
                case "block_process" -> {
                    if (processes.containsKey(ID)) {
                        process = processes.get(ID);
                        if (process.getState().equals(State.ready)) {
                            process.block();
                        } else System.out.println("This process is already blocked!\n");
                    } else System.out.println("This process is killed!\n");
                }
                case "unblock_process" -> {
                    if (processes.containsKey(ID)) {
                        process = processes.get(ID);
                        if (process.getState().equals(State.blocked)) {
                            process.unBlock();
                        } else System.out.println("This process is not block!\n");
                    } else System.out.println("This process is killed!\n");
                }
                case "kill_process" -> {
                    if (processes.containsKey(ID)) {
                        process = processes.get(ID);
                        if (process.getState().equals(State.ready)) {
                            kill(ID, processes);
                        } else System.out.println("This process is block!\n");
                    } else System.out.println("This process is killed!\n");
                }

            }
        }
    }

    public static Process create_process(String ID, String fileName) throws IOException {
        File file = new File("src/" + fileName);

        return new Process(ID, fileReader(file));
    }

    public static ArrayList<String> fileReader(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> list = new ArrayList<>();

        String str;
        while ((str = reader.readLine()) != null) {
            list.add(str);
        }
        return list;
    }

    public static void kill(String ID, Map<String, Process> map) {
        map.remove(ID);
    }
}





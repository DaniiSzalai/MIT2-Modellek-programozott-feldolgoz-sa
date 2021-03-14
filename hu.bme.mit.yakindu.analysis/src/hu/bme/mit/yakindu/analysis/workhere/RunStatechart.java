package hu.bme.mit.yakindu.analysis.workhere;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hu.bme.mit.yakindu.analysis.RuntimeService;
import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;



public class RunStatechart {
	
	public static void main(String[] args) throws IOException {
		ExampleStatemachine s = new ExampleStatemachine();
		s.setTimer(new TimerService());
		RuntimeService.getInstance().registerStatemachine(s, 200);
		s.init();
		s.enter();
		s.runCycle();
		System.out.println("Kérem az átmenet nevét:");
		
		boolean exited = false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		while (!exited) {
			line = reader.readLine().toUpperCase();
			switch(line) {
			case "START":
				s.raiseStart();
				s.runCycle();
				print(s);
				break;
			case "WHITE":
				s.raiseWhite();
				s.runCycle();
				print(s);
				break;
			case "BLACK":
				s.raiseBlack();
				s.runCycle();
				print(s);
				break;
			case "EXIT":
				print(s);
				System.exit(0);
				break;
			default:
				System.out.println("Nincs ilyen átmenet!");
			}			
		}
		/*s.init();
		s.enter();
		s.runCycle();
		print(s);
		s.raiseStart();
		s.runCycle();
		System.in.read();
		s.raiseWhite();
		s.runCycle();
		print(s);
		System.exit(0);*/
		
	}

	public static void print(IExampleStatemachine s) {
		System.out.println("W = " + s.getSCInterface().getWhiteTime());
		System.out.println("B = " + s.getSCInterface().getBlackTime());
	}
}

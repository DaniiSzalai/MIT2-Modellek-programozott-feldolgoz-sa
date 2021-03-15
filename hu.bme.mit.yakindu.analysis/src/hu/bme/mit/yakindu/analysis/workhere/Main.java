package hu.bme.mit.yakindu.analysis.workhere;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.yakindu.base.types.Direction;
import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;
import org.yakindu.sct.model.sgraph.Vertex;
import org.yakindu.sct.model.stext.stext.EventDefinition;
import org.yakindu.sct.model.stext.stext.VariableDefinition;

import hu.bme.mit.model2gml.Model2GML;
import hu.bme.mit.yakindu.analysis.modelmanager.ModelManager;

public class Main {
	@Test
	public void test() {
		main(new String[0]);
	}
	
	//Name checking method for exercise 2.5 
	public static boolean isValidAutomaticName(String name, List<String> list) {
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null && list.get(i).equals(name)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		/*ModelManager manager = new ModelManager();
		Model2GML model2gml = new Model2GML();
		
		// Loading model
		EObject root = manager.loadModel("model_input/example.sct");
		
		// Reading model
		Statechart s = (Statechart) root;
		TreeIterator<EObject> iterator = s.eAllContents();
		List<String> names = new ArrayList<String>();
		
		System.out.println("Állapotok:");
		while (iterator.hasNext()) {
			EObject content = iterator.next();
			if(content instanceof State) {
				State state = (State) content;
				System.out.println(state.getName());
				names.add(state.getName());
			}
		}
		System.out.println();
		
		//2.3 feladat
		System.out.println("Átmenetek:");
		iterator = s.eAllContents();
		while (iterator.hasNext()) {
			EObject content = iterator.next();
			if(content instanceof Transition) {
				Vertex source = ((Transition) content).getSource();
				Vertex target = ((Transition) content).getTarget();
				System.out.println(source.getName() + " -> " + target.getName());
			}
		}
		System.out.println();
		
		//2.4 feladat
		System.out.println("Csapda állapotok:");
		iterator = s.eAllContents();
		while (iterator.hasNext()) {
			EObject content = iterator.next();
			if(content instanceof State) {
				State state = (State) content;
				if (state.getOutgoingTransitions().size() == 0) {
					System.out.println(state.getName());
				}
			}
		}
		System.out.println();
		
		//2.5 feladat
		System.out.println("Névtelen állapotok:");
		iterator = s.eAllContents();
		int index = 1;
		while (iterator.hasNext()) {
			EObject content = iterator.next();
			if(content instanceof State) {
				State state = (State) content;
				if (state.getName() == null) {
					String automaticName = "automaticNumberState" + index;
					boolean validated = false;
					while (!validated) {
						if (!isValidAutomaticName(automaticName, names)) {
							index++;
							automaticName = "automaticNumberState" + index;
						} else {
							validated = true;
						}
					}
					System.out.println(automaticName);
					index++;
				}
			}
		}
		System.out.println();
		
		//4.3 feladat
		System.out.println("Belső változók:");
		iterator = s.eAllContents();
		List<String> variables = new ArrayList<String>();
		List<String> inEvents = new ArrayList<String>();
		while (iterator.hasNext()) {
			EObject content = iterator.next();
			if (content instanceof VariableDefinition) {
				VariableDefinition variable = (VariableDefinition) content;
				variables.add(variable.getName());
			}
			if (content instanceof EventDefinition) {
				EventDefinition event = (EventDefinition) content;
				Direction dir = event.getDirection();
				if (dir.getValue() == 1) {
					inEvents.add(event.getName());
				}
			}
		}
		for (int i= 0; i < variables.size(); i++) {
			System.out.println(variables.get(i));
		}
		System.out.println();
		
		System.out.println("Bemenő események:");
		for (int i= 0; i < inEvents.size(); i++) {
			System.out.println(inEvents.get(i));
		}
		System.out.println();
		
		//4.4 feladat
		System.out.println("public static void print(IExampleStatemachine s) {");
		for (int i = 0; i < variables.size(); i++) {
			String name = variables.get(i);
			name = name.substring(0,1).toUpperCase() + name.substring(1);
			char firstChar = name.charAt(0);
			System.out.println("\tSystem.out.println(\"" + firstChar + " = \" + s.getSCInterface().get" + name + "());");
		}
		
		/*for (int i = 0; i < inEvents.size(); i++) {
			String name = inEvents.get(i);
			name = name.substring(0,1).toUpperCase() + name.substring(1);
			char firstChar = name.charAt(0);
			System.out.println("System.out.println(\"" + firstChar + " = \" + s.getSCInterface().get" + name + "());");
		}*/
		
		//System.out.println("}");
		
		//4.5 feladat ------------------------------------------------Kódgenerátor-----------------------------------------------------------------------------
		//A vonal előtti rész és ez a kód független, ha valamelyiket le akarjuk futtatni a másikat ki kell kommentezni.
		
		ModelManager manager = new ModelManager();
		Model2GML model2gml = new Model2GML();
		
		// Loading model
		EObject root = manager.loadModel("model_input/example.sct");
		
		// Reading model
		Statechart s = (Statechart) root;
		TreeIterator<EObject> iterator = s.eAllContents();
		
		List<String> variables = new ArrayList<String>();
		List<String> inEvents = new ArrayList<String>();
		
		while (iterator.hasNext()) {
			EObject content = iterator.next();
			if (content instanceof VariableDefinition) {
				VariableDefinition variable = (VariableDefinition) content;
				String name = variable.getName();
				name = name.substring(0,1).toUpperCase() + name.substring(1);
				variables.add(name);
			}
			if (content instanceof EventDefinition) {
				EventDefinition event = (EventDefinition) content;
				Direction dir = event.getDirection();
				if (dir.getValue() == 1) {
					String name = event.getName();
					name = name.substring(0,1).toUpperCase() + name.substring(1);
					inEvents.add(name);
				}
			}
		}
		
		System.out.println("while (!exited) {");
		System.out.println("\tline = reader.readLine().toUpperCase();");
		System.out.println("\tswitch(line) {");
		
		for (int i = 0; i < inEvents.size(); i++) {
			String upperName = inEvents.get(i).toUpperCase();
			System.out.println("\tcase \"" + upperName + "\":");
			System.out.println("\t\ts.raise" + inEvents.get(i) + "();");
			System.out.println("\t\ts.runCycle();");
			System.out.println("\t\tprint(s);");
			System.out.println("\t\tbreak;");
		}
		
		System.out.println("\tcase \"EXIT\":");
		System.out.println("\t\tprint(s);");
		System.out.println("\t\tSystem.exit(0);");
		System.out.println("\t\tbreak;");
		
		System.out.println("\tdefault:");
		System.out.println("\t\tSystem.out.println(\"Nincs ilyen átmenet!\");");
		System.out.println("\t}");
		System.out.println("}");
		
		// Transforming the model into a graph representation
		String content = model2gml.transform(root);
		// and saving it
		manager.saveFile("model_output/graph.gml", content);
	}
}



package hu.bme.mit.yakindu.analysis.workhere;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;
import org.yakindu.sct.model.sgraph.Vertex;

import hu.bme.mit.model2gml.Model2GML;
import hu.bme.mit.yakindu.analysis.modelmanager.ModelManager;

public class Main {
	@Test
	public void test() {
		main(new String[0]);
	}
	
	public static boolean isValidAutomaticName(String name, List<String> list) {
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null && list.get(i).equals(name)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		ModelManager manager = new ModelManager();
		Model2GML model2gml = new Model2GML();
		
		// Loading model
		EObject root = manager.loadModel("model_input/example.sct");
		
		// Reading model
		Statechart s = (Statechart) root;
		TreeIterator<EObject> iterator = s.eAllContents();
		List<String> names = new ArrayList<String>();
		
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
		
		// Transforming the model into a graph representation
		String content = model2gml.transform(root);
		// and saving it
		manager.saveFile("model_output/graph.gml", content);
	}
}



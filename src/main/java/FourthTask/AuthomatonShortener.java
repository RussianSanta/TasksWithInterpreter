package FourthTask;

import automations.impls.StateImpl;
import org.apache.commons.math3.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class AuthomatonShortener {
    public static ArrayList<ArrayList<StateImpl>> tryCut(ArrayList<StateImpl> states) {
        Set<String> values = states.get(0).getTransitions().keySet();
        ArrayList<StateImpl> accessStates = new ArrayList<>();
        ArrayList<StateImpl> nonAccessStates = new ArrayList<>();
        Queue<Pair<ArrayList<StateImpl>, String>> s = new LinkedList<>();
        ArrayList<ArrayList<StateImpl>> p = new ArrayList<>();

        //Делим все шаги на допускающие и недопускающие
        for (StateImpl state : states) {
            if (state.getDescription().equals("Yes")) accessStates.add(state);
            else if (state.getDescription().equals("No")) nonAccessStates.add(state);
            else {
                System.out.println("Ошибка в определении шага. Проверьте таблицу автомата.");
                return null;
            }
        }
        //Заполняем массив классов
        p.add(accessStates);
        p.add(nonAccessStates);
        //Заполняем массив пар <C,a> где C - класс, а - значение из алфавита
        for (String value : values) {
            Pair<ArrayList<StateImpl>, String> pairOne = Pair.create(accessStates, value);
            Pair<ArrayList<StateImpl>, String> pairTwo = Pair.create(nonAccessStates, value);
            s.offer(pairOne);
            s.offer(pairTwo);
        }
        while (!s.isEmpty()) {
            Pair<ArrayList<StateImpl>, String> pair = s.poll();
            int index = 0;
            while (index < p.size()) {
                ArrayList<StateImpl> r = p.get(index);
                ArrayList<StateImpl>[] splits = split(r, pair);
                ArrayList<StateImpl> r1 = splits[0];
                ArrayList<StateImpl> r2 = splits[1];

                if (!r1.isEmpty() && !r2.isEmpty()) {
                    int i = p.indexOf(r);
                    p.remove(r);
                    p.add(i, r1);
                    p.add(i, r2);
                    for (String value : values) {
                        Pair<ArrayList<StateImpl>, String> pairOne = Pair.create(r1, value);
                        Pair<ArrayList<StateImpl>, String> pairTwo = Pair.create(r2, value);
                        s.offer(pairOne);
                        s.offer(pairTwo);
                    }
                } else index++;
            }
        }
        return p;
    }

    private static ArrayList<StateImpl>[] split(ArrayList<StateImpl> r,
                                     Pair<ArrayList<StateImpl>, String> pair) {
        ArrayList<StateImpl> r1 = new ArrayList<>();
        ArrayList<StateImpl> r2 = new ArrayList<>();
        for (StateImpl state : r) {
            String nextStepNumber = state.executeNext(pair.getValue().toCharArray()[0]);
            ArrayList<StateImpl> pairStates = pair.getFirst();
            boolean isContains = pairStates.stream().map(StateImpl::getStateName)
                    .collect(Collectors.toList()).contains(nextStepNumber + ".0");
            if (isContains) r1.add(state);
            else r2.add(state);
        }
        return (ArrayList<StateImpl>[]) new ArrayList[]{r1,r2};
    }
}
